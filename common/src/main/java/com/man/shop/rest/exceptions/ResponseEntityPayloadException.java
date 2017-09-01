package com.man.shop.rest.exceptions;

/**
 * Created by smanolache on 6/11/2017.
 */
public class ResponseEntityPayloadException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    public ResponseEntityPayloadException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public ResponseEntityPayloadException() {
        super();
    }

}
