package com.man.shop.rest.exceptions;

/**
 * Created by smanolache on 6/8/2017.
 */
public class SolrProductException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    public SolrProductException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public SolrProductException() {
        super();
    }
}
