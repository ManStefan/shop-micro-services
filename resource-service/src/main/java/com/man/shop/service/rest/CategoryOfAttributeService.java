package com.man.shop.service.rest;

import com.man.shop.model.CategoryOfAttribute;
import com.man.shop.model.RestToDAOTransformer;
import com.man.shop.repositories.CategoryOfAttributeRepository;
import com.man.shop.rest.entites.RestCategoryOfAttribute;
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
@RequestMapping("/category_of_attribute")
public class CategoryOfAttributeService {

    private static final Logger logger = Logger.getLogger(CategoryOfAttributeService.class);

    @Autowired
    private CategoryOfAttributeRepository categoryOfAttributeRepository;

    @Autowired
    private RestToDAOTransformer restToDAOTransformer;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addCategory(@RequestBody RestCategoryOfAttribute restCategoryOfAttribute){

        if (restCategoryOfAttribute.getName() == null || restCategoryOfAttribute.getName().isEmpty()){
            String errorMsg = "The name of the category can't be empty!";
            logger.warn(errorMsg);
            throw new ResourceNotAddedException(errorMsg);
        }

        CategoryOfAttribute categoryOfAttribute = categoryOfAttributeRepository.save(restToDAOTransformer.transformCategoryOfAttributeFromRestToDAO(restCategoryOfAttribute));

        URI location = ResourceUtils.buildProductResource(categoryOfAttribute.getId().toString());

        return ResponseEntity.created(location).body(location);
    }
}
