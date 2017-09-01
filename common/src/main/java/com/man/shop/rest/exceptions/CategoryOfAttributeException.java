package com.man.shop.rest.exceptions;

/**
 * Created by smanolache on 6/8/2017.
 */
public class CategoryOfAttributeException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    public CategoryOfAttributeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public CategoryOfAttributeException() {
        super();
    }
}
