package com.man.shop.service.rest;

import com.man.shop.model.Producer;
import com.man.shop.model.RestToDAOTransformer;
import com.man.shop.repositories.ProducerRepository;
import com.man.shop.rest.entites.RestProducer;
import com.man.shop.rest.exceptions.ProducerException;
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
@RequestMapping("/producer")
public class ProducerService {

    private static final Logger logger = Logger.getLogger(ProducerService.class);

    @Autowired
    private RestToDAOTransformer restToDAOTransformer;

    @Autowired
    private ProducerRepository producerRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addProducer(@RequestBody RestProducer restProducer) throws ProducerException {

        if (restProducer.getName() == null || restProducer.getName().isEmpty()){
            String errorMsg = "The name of the producer can not be blank!";
            logger.error(errorMsg);
            throw new ProducerException(errorMsg);
        }

        Producer producer = producerRepository.save(restToDAOTransformer.transformProducerFromRestRoDAO(restProducer));

        URI location = ResourceUtils.buildProductResource(producer.getId().toString());

        return ResponseEntity.created(location).body(location);
    }
}
