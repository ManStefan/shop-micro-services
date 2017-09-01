package com.man.shop.rest.services;

import com.man.shop.rest.exceptions.ErrorResponse;
import com.man.shop.rest.exceptions.ResponseEntityPayloadException;
import com.man.shop.utils.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Created by smanolache on 6/10/2017.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> httpClientErrorExceptionHandler(HttpClientErrorException ex){
        ErrorResponse errorResponse = JsonUtils.fromJson(ex.getResponseBodyAsString(), ErrorResponse.class);

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorResponse> httpServerErrorExceptionHandler(HttpServerErrorException ex){
        ErrorResponse errorResponse = JsonUtils.fromJson(ex.getResponseBodyAsString(), ErrorResponse.class);

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(ResponseEntityPayloadException.class)
    public ResponseEntity<ErrorResponse> responseEntityExceptionHandler(ResponseEntityPayloadException ex){
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }
}
