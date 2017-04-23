package com.man.shop.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by smanolache on 4/3/2017.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String productId) {
        super("The product with id:  '" + productId + " was not found in Solr Database!'.");
    }
}
