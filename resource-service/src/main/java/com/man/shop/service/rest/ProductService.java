package com.man.shop.service.rest;

import com.man.shop.clients.SearchClient;
import com.man.shop.model.Product;
import com.man.shop.model.RestToDAOTransformer;
import com.man.shop.repositories.ProductRepository;
import com.man.shop.rest.entites.RestProduct;
import com.man.shop.rest.exceptions.ResourceNotAddedException;
import com.man.shop.rest.resource.ResourceUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by smanolache on 3/31/2017.
 */
@Transactional
@RestController
@RequestMapping("/product")
public class ProductService {

    private static final Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestToDAOTransformer restToDAOTranformer;

    @Autowired
    private SearchClient searchClient;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody RestProduct restProduct){

        if (restProduct.getId() != null){
            String message = "You want to add a new product, but you provided an ID!";
            logger.warn(message);
            throw new ResourceNotAddedException(message);
        }

        final Product product;
        try {
             product = productRepository.save(restToDAOTranformer.transformProductFromRestToDAO(restProduct));
        } catch (ParseException e) {
            logger.error("ERROR in transforming RestProduct to DAO: " + e, e);
            throw new ResourceNotAddedException(e.getMessage());
        }

        restProduct.setId(product.getId());
        ResponseEntity response = searchClient.addProductToSolr(restProduct);
        if (!response.getStatusCode().equals(HttpStatus.CREATED)){
            logger.error("ERROR in adding the product into SOLR database: " + response.toString());
            throw new ResourceNotAddedException(response.getBody().toString());
        }

        URI location = ResourceUtils.buildProductResource(product.getId().toString());

        return ResponseEntity.created(location).body(location);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/newest/{n}")
    List<RestProduct> getNewestProducts(@PathVariable(name = "n") Integer n){
        return searchClient.getNewestProducts(n)
                .stream()
                .map(id -> restToDAOTranformer.transformProductFromDAOToRest(productRepository.findOne(id)))
                .collect(Collectors.toList());
    }

}
