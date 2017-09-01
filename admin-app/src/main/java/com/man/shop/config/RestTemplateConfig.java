package com.man.shop.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by smanolache on 6/5/2017.
 */
public class RestTemplateConfig {

    @Value("${url.baseResourceServerUrl}")
    private String baseResourceServerUrl;

    @Value("${url.searchProductCategoryByName}")
    private String searchProductCategoryByName;

    @Value("${url.searchProductCategoryById}")
    private String searchProductCategoryById;

    @Value("${url.updateProductCategory}")
    private String updateProductCategory;

    @Value("${url.deleteProductCategory}")
    private String deleteProductCategory;

    @Value("${url.addProductCategory}")
    private String addProductCategory;

    @Value("${url.getCategoryOfProduct}")
    private String getCategoryOfProduct;

    public String getSearchProductCategoryByNameUrl(){
        return baseResourceServerUrl + searchProductCategoryByName;
    }

    public String getSearchProductCategoryByIdUrl(String id){
        return baseResourceServerUrl + id;
    }

    public String getUpdateProductCategoryUrl(){
        return baseResourceServerUrl + updateProductCategory;
    }

    public String getDeleteProductCategoryUrl(){
        return baseResourceServerUrl + deleteProductCategory;
    }

    public String getAddProductCategory(){
        return baseResourceServerUrl + addProductCategory;
    }

    public String getCategoryOfPRoductUrl(){
        return baseResourceServerUrl + getCategoryOfProduct;
    }
}
