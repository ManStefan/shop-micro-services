package com.man.shop.service.rest;

import com.man.shop.rest.entites.RestProductFilterRequest;
import com.man.shop.rest.entites.RestProductFilterResponse;
import com.man.shop.filters.service.ProductFilterService;
import com.man.shop.model.adaptors.RestToSolrProductAdapter;
import com.man.shop.model.solr.SolrProduct;
import com.man.shop.repositories.ProductSolrRepository;
import com.man.shop.rest.entites.RestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by smanolache on 1/19/2017.
 */
@RestController
@RequestMapping("/product")
public class ProductService {

    @Autowired
    private ProductSolrRepository productSolrRepository;

    @Autowired
    private RestToSolrProductAdapter restToSolrProductAdapter;

    @Autowired
    private ProductFilterService productFilterService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody RestProduct restProduct){
        validateRestProduct(restProduct);

        if (productSolrRepository.findOne(restProduct.getId()) != null){
            throw new ProductAlreadyAddedException(restProduct.getId().toString());
        }

        SolrProduct solrProduct = null;
        try {
            solrProduct = restToSolrProductAdapter.transformRestToToSolr(restProduct);
        } catch (ParseException e) {
            throw new RestParseException(e.getMessage());
        }

        URI location = saveSolrProduct(solrProduct);

        return ResponseEntity.created(location).body(location);
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<?> update(@RequestBody RestProduct restProduct){
        validateRestProduct(restProduct);

        if (productSolrRepository.findOne(restProduct.getId()) == null){
            throw new ProductNotFoundException(restProduct.getId().toString());
        }

        URI location = null;
        try {
            location = saveSolrProduct(restToSolrProductAdapter.transformRestToToSolr(restProduct));
        } catch (ParseException e) {
            throw new RestParseException(e.getMessage());
        }

        return ResponseEntity.created(location).build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RestProduct get(@PathVariable String id){
        SolrProduct solrProduct = productSolrRepository.findOne(id);

        if (solrProduct == null){
            throw new ProductNotFoundException(id.toString());
        }

        return restToSolrProductAdapter.transformSolrToRest(solrProduct);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable String id){
        SolrProduct solrProduct = productSolrRepository.findOne(id);

        if (solrProduct == null){
            throw new ProductNotFoundException(id.toString());
        }

        productSolrRepository.delete(id.toString());

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
    List<Long> getNeweastProducts(@PathVariable Integer n){
        List<SolrProduct> solrProducts = productSolrRepository.getLatestProducts(n).getContent();

        return solrProducts.stream().map(product -> Long.valueOf(product.getId())).collect(Collectors.toList());
    }

    @RequestMapping(value = "topBySales/{n}", method = RequestMethod.GET)
    List<Long> getTopBySales(@PathVariable Integer n){
        List<SolrProduct> solrProducts= productSolrRepository.getTopProductsBySales(n).getContent();

        return solrProducts.stream().map(product -> Long.valueOf(product.getId())).collect(Collectors.toList());
    }

    private URI saveSolrProduct(SolrProduct solrProduct){
        productSolrRepository.save(solrProduct);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(solrProduct.getId()).toUri();

        return location;
    }

    private void validateRestProduct(RestProduct restProduct){
        //TODO
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class ProductNotFoundException extends RuntimeException {

        public ProductNotFoundException(String productId) {
            super("The product with id:  '" + productId + " was not found in Solr Database!'.");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class ProductAlreadyAddedException extends RuntimeException {

        public ProductAlreadyAddedException(String productId) {
            super("The product with id:  '" + productId + " was already added!'.");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class RestParseException extends RuntimeException {
        public RestParseException (String errorMsg) {
            super ("Parsing of the payload failed with the following message: " + errorMsg);
        }
    }
}
