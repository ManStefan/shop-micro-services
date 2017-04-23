package com.man.shop.service;

import com.man.shop.model.QuantityStandard;
import com.man.shop.model.RestToDAOTransformer;
import com.man.shop.repositories.QuantityStandardRepository;
import com.man.shop.rest.entites.RestQuantityStandard;
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
 * Created by smanolache on 4/8/2017.
 */
@Transactional
@RestController
@RequestMapping("/quantity_standard")
public class QuantityStandardService {

    private static final Logger logger = Logger.getLogger(QuantityStandardService.class);

    @Autowired
    private QuantityStandardRepository quantityStandardRepository;

    @Autowired
    private RestToDAOTransformer restToDAOTransformer;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody  RestQuantityStandard restQuantityStandard){
        if (restQuantityStandard.getId() != null){
            String message = "You wanted to add a new Quantity Standard, but you provided an ID!";
            logger.warn(message);
            throw new ResourceNotAddedException(message);
        }

        if (restQuantityStandard.getName() == null || restQuantityStandard.getName().isEmpty()){
            String message = "The name of the quantity standard entity can not be empty!";
            logger.warn(message);
            throw new ResourceNotAddedException(message);
        }

        QuantityStandard quantityStandard = restToDAOTransformer.tranformQuantityStandardToDAO(restQuantityStandard);
        quantityStandardRepository.save(quantityStandard);

        URI location = ResourceUtils.buildProductResource(quantityStandard.getId().toString());

        return ResponseEntity.created(location).body(location);
    }
}
