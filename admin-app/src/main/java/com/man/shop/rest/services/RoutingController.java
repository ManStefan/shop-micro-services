package com.man.shop.rest.services;

import com.man.shop.rest.exceptions.ResponseEntityPayloadException;
import com.man.shop.rest.resource.ResourceUtils;
import com.man.shop.rest.template.AdminRestTemplate;
import com.man.shop.rest.entites.RestCategoryOfProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by smanolache on 4/30/2017.
 */
@RestController
public class RoutingController {

    @Autowired
    private AdminRestTemplate restTemplate;

    @RequestMapping(value = "/category_of_product/name/{name}", method = RequestMethod.GET)
    public List<RestCategoryOfProduct> searchProductCategories(@PathVariable(name="name") String name, Pageable pageable){
        return restTemplate.callSearchProductCategoryByName(name, pageable);
    }

    @PutMapping(value = "/category_of_product")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateProductCategory(@RequestBody RestCategoryOfProduct restCategoryOfProduct){
        restTemplate.callUpdateProductCategory(restCategoryOfProduct);
    }

    @PostMapping(value = "/category_of_product")
    public ResponseEntity<?> addProductCategory(@RequestBody RestCategoryOfProduct restCategoryOfProduct) throws URISyntaxException, ResponseEntityPayloadException {
        ResponseEntity<String> remote = restTemplate.callAddProductCategory(restCategoryOfProduct);
        URI location = ResourceUtils.buildProductResource(remote);

        return ResponseEntity.created(location).body(location);
    }

    @GetMapping("/category_of_product/id/{id}")
    public RestCategoryOfProduct getCategoryOfProductById(@PathVariable Long id){
        return restTemplate.callGetCategoryOfProduct(id.toString());
    }
}
