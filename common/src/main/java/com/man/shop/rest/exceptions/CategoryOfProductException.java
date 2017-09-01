package com.man.shop.rest.exceptions;

/**
 * Created by smanolache on 6/8/2017.
 */
public class CategoryOfProductException extends Exception{
        private String errorMessage;

        public String getErrorMessage() {
            return errorMessage;
        }
        public CategoryOfProductException(String errorMessage) {
            super(errorMessage);
            this.errorMessage = errorMessage;
        }
        public CategoryOfProductException() {
            super();
        }
}
