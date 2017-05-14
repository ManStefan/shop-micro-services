package com.man.shop.service.rest;

import com.man.shop.model.Attribute;
import com.man.shop.model.CategoryOfAttribute;
import com.man.shop.model.RestToDAOTransformer;
import com.man.shop.repositories.AttributeRepository;
import com.man.shop.repositories.CategoryOfAttributeRepository;
import com.man.shop.rest.entites.RestAttribute;
import com.man.shop.rest.exceptions.ResourceNotAddedException;
import com.man.shop.rest.resource.ResourceUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by smanolache on 4/6/2017.
 */
@Transactional
@RestController
@RequestMapping("/attribute")
public class AttributeService {

    private static final Logger logger = Logger.getLogger(AttributeService.class);

    @Autowired
    private CategoryOfAttributeRepository categoryOfAttributeRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private RestToDAOTransformer restToDAOTransformer;

    @RequestMapping(path = "/category/{categoryId}", method = RequestMethod.POST)
    ResponseEntity<?> addAttribute(@PathVariable Long categoryId, @RequestBody RestAttribute restAttribute){

        if (restAttribute.getId() != null){
            String message = "You want to add a new attribute, but you provided an ID!";
            logger.warn(message);
            throw new ResourceNotAddedException(message);
        }

        CategoryOfAttribute categoryOfAttribute = categoryOfAttributeRepository.findOne(categoryId);
        if (categoryOfAttribute == null){
            String message = String.format("Category with id %d can't be found in the DB!", categoryId);
            logger.warn(message);
            throw new ResourceNotAddedException(message);
        }

        if (restAttribute.getName() == null || restAttribute.getName().isEmpty()){
            String message = "Can't add an attribute with empty name!";
            logger.warn(message);
            throw new ResourceNotAddedException(message);
        }

        Attribute attribute = restToDAOTransformer.transformAttributeFromRestToDAO(restAttribute);
        attribute.setCategoryOfAttribute(categoryOfAttribute);

        attributeRepository.save(attribute);

        URI location = ResourceUtils.buildProductResource(attribute.getId().toString());

        return ResponseEntity.created(location).body(location);
    }
}
