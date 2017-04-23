package com.man.shop.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by smanolache on 4/8/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotUpdatedException extends RuntimeException {
    public ResourceNotUpdatedException(String message){
        super(message);
    }

    public ResourceNotUpdatedException(Exception e){
        super(e);
    }

    public ResourceNotUpdatedException(String message, Exception e){
        super(message, e);
    }
}
