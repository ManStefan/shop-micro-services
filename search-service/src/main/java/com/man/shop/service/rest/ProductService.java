package com.man.shop.service.rest;

import com.man.shop.filters.service.ProductFilterService;
import com.man.shop.model.adaptors.RestToSolrProductAdapter;
import com.man.shop.model.solr.SolrProduct;
import com.man.shop.repositories.ProductSolrRepository;
import com.man.shop.rest.entites.RestProduct;
import com.man.shop.rest.entites.RestProductFilterRequest;
import com.man.shop.rest.entites.RestProductFilterResponse;
import com.man.shop.rest.exceptions.SolrProductException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static com.man.shop.rest.resource.ResourceUtils.buildProductResource;

/**
 * Created by smanolache on 1/19/2017.
 */
@Transactional
@RestController
@RequestMapping("/product")
public class ProductService {

    private static final Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    private ProductSolrRepository productSolrRepository;

    @Autowired
    private RestToSolrProductAdapter restToSolrProductAdapter;

    @Autowired
    private ProductFilterService productFilterService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody RestProduct restProduct) throws SolrProductException {
        validateRestProduct(restProduct);

        if (productSolrRepository.findOne(restProduct.getId().toString()) != null){
            String errorMsg = "Item not added: you want to add a new product to solr, but there is already an item with the same id: " + restProduct.getId();
            logger.error(errorMsg);
            throw new SolrProductException();
        }

        SolrProduct solrProduct = null;
        try {
            solrProduct = restToSolrProductAdapter.transformRestToToSolr(restProduct);
        } catch (ParseException e) {
            String errorMessage = "Error in transforming the REST product to SOLR product: " + e.getMessage();
            logger.error(errorMessage);
            throw new SolrProductException(errorMessage);
        }

        productSolrRepository.save(solrProduct);
        URI location = buildProductResource(solrProduct.getId().toString());

        return ResponseEntity.created(location).body(location);
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<?> update(@RequestBody RestProduct restProduct) throws SolrProductException {
        validateRestProduct(restProduct);

        if (productSolrRepository.findOne(restProduct.getId().toString()) == null){
            String errorMessage = "Can't find product in SOLR database";
            logger.error(errorMessage);
            throw new SolrProductException(errorMessage);
        }

        URI location;
        try {
            SolrProduct solrProduct = restToSolrProductAdapter.transformRestToToSolr(restProduct);
            productSolrRepository.save(solrProduct);
            location = buildProductResource(solrProduct.getId().toString());
        } catch (ParseException e) {
            String errorMessage = "Error in transforming the REST product to SOLR product: " + e.getMessage();
            logger.error(errorMessage);
            throw new SolrProductException(errorMessage);
        }

        return ResponseEntity.created(location).build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable String id) throws SolrProductException {
        SolrProduct solrProduct = productSolrRepository.findOne(id);

        if (solrProduct == null){
            String errorMessage = "Can't find product in SOLR database for deleting";
            logger.error(errorMessage);
            throw new SolrProductException(errorMessage);
        }
        productSolrRepository.delete(id.toString());

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAll(){
        productSolrRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    RestProductFilterResponse filter(@RequestBody RestProductFilterRequest productFilter){
        return productFilterService.extractResponseFromFacets(
                productSolrRepository.getFacetsForProduct(productFilterService.createFacetQuery(productFilter)),
                productFilter
        );
    }

    @RequestMapping(value = "/newest/{n}", method = RequestMethod.GET)
    List<Long> getNewestProducts(@PathVariable Integer n){
        List<SolrProduct> solrProducts = productSolrRepository.getLatestProducts(n).getContent();
        return solrProducts.stream().map(product -> Long.valueOf(product.getId())).collect(Collectors.toList());
    }

    @RequestMapping(value = "topBySales/{n}", method = RequestMethod.GET)
    List<Long> getTopBySales(@PathVariable Integer n){
        List<SolrProduct> solrProducts= productSolrRepository.getTopProductsBySales(n).getContent();
        return solrProducts.stream().map(product -> Long.valueOf(product.getId())).collect(Collectors.toList());
    }



    private void validateRestProduct(RestProduct restProduct){
        //TODO
    }


}
