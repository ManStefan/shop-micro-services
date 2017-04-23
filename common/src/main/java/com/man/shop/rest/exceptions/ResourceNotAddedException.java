package com.man.shop.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by smanolache on 4/8/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotAddedException extends RuntimeException {
    public ResourceNotAddedException(String message){
        super(message);
    }

    public ResourceNotAddedException(Exception e){
        super(e);
    }

    public ResourceNotAddedException(String message, Exception e){
        super(message, e);
    }
}
