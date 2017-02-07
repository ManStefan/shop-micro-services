package com.man.shop.rest.entites;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smanolache on 1/8/2015.
 *
 * This is a bean that reflects the options checked by the user on the UI
 */
public class RestProductFilterRequest {

    private String name;

    private String description;

    private String searchWord;

    //<min, max>
    private List< Map.Entry<Double, Double>> priceAmount;

    private Map<Long, List<Long>> attributes;

    private List<Long> facetedCategoriesOfAttributes;

    private List<Long> categoriesOfProduct;

    private Boolean inStock;

    private Boolean active =true;

    private Boolean inactive = true;

    private Boolean promo;

    private List<Long> producers;

    private LinkedHashMap<String, String> sort;

    private Integer pageSize;

    private Integer pageNumber;

    public List<Map.Entry<Double, Double>> getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(List<Map.Entry<Double, Double>> priceAmount) {
        this.priceAmount = priceAmount;
    }

    public Map<Long, List<Long>> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Long, List<Long>> attributes) {
        this.attributes = attributes;
    }

    public List<Long> getFacetedCategoriesOfAttributes() {
        return facetedCategoriesOfAttributes;
    }

    public void setFacetedCategoriesOfAttributes(List<Long> facetedCategoriesOfAttributes) {
        this.facetedCategoriesOfAttributes = facetedCategoriesOfAttributes;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public List<Long> getCategoriesOfProduct() {
        return categoriesOfProduct;
    }

    public void setCategoriesOfProduct(List<Long> categoriesOfProduct) {
        this.categoriesOfProduct = categoriesOfProduct;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Boolean getPromo() {
        return promo;
    }

    public void setPromo(Boolean promo) {
        this.promo = promo;
    }

    public List<Long> getProducers() {
        return producers;
    }

    public void setProducers(List<Long> producers) {
        this.producers = producers;
    }


    public LinkedHashMap<String, String> getSort() {
        return sort;
    }

    public void setSort(LinkedHashMap<String, String> sort) {
        this.sort = sort;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
