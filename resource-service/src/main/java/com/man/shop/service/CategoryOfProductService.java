package com.man.shop.service;

import com.man.shop.model.CategoryOfProduct;
import com.man.shop.model.RestToDAOTransformer;
import com.man.shop.repositories.CategoryOfProductRepository;
import com.man.shop.rest.entites.RestCategoryOfProduct;
import com.man.shop.rest.exceptions.ResourceNotAddedException;
import com.man.shop.rest.resource.ResourceUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Created by smanolache on 4/6/2017.
 */
@Transactional
@RestController
@RequestMapping("/category_of_product")
public class CategoryOfProductService {

    private static final Logger logger = Logger.getLogger(CategoryOfProductService.class);

    @Autowired
    private RestToDAOTransformer restToDAOTransformer;

    @Autowired
    private CategoryOfProductRepository categoryOfProductRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addCategoryOfProduct(@RequestBody RestCategoryOfProduct restCategoryOfProduct){

        if (restCategoryOfProduct.getName() == null || restCategoryOfProduct.getName().isEmpty()){
            String errorMsg = "The name of the category of product can not be blank!";
            logger.warn(errorMsg);
            throw new ResourceNotAddedException(errorMsg);
        }

        CategoryOfProduct categoryOfProduct = categoryOfProductRepository.save(restToDAOTransformer.transformCategoryOfProductToDAO(restCategoryOfProduct));

        URI location = ResourceUtils.buildProductResource(categoryOfProduct.getId().toString());

        return ResponseEntity.created(location).body(location);
    }
}
