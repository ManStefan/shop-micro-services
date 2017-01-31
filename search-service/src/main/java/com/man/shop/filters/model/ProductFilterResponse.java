package com.man.shop.filters.model;

import java.util.List;
import java.util.Map;

/**
 * Created by smanolache on 1/30/2017.
 */
public class ProductFilterResponse {
    private ProductFilterRequest filterRequest;

    private List<Long> products;

    // <cat_attr_id, <attr_id, count>>
    private Map<Long, Map<Long, Long>> facetedAttributes;

    // <producer_id, count>
    private Map<Long, Long> facetedProducers;

    //<product_category_id, count>
    private Map<Long, Long> facetedProductCategories;

    public void setFilterRequest(ProductFilterRequest filterRequest) {
        this.filterRequest = filterRequest;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public void setFacetedAttributes(Map<Long, Map<Long, Long>> facetedAttributes) {
        this.facetedAttributes = facetedAttributes;
    }

    public void setFacetedProducers(Map<Long, Long> facetedProducers) {
        this.facetedProducers = facetedProducers;
    }

    public void setFacetedProductCategories(Map<Long, Long> facetedProductCategories) {
        this.facetedProductCategories = facetedProductCategories;
    }

    public ProductFilterRequest getFilterRequest() {
        return filterRequest;
    }

    public List<Long> getProducts() {
        return products;
    }

    public Map<Long, Map<Long, Long>> getFacetedAttributes() {
        return facetedAttributes;
    }

    public Map<Long, Long> getFacetedProducers() {
        return facetedProducers;
    }

    public Map<Long, Long> getFacetedProductCategories() {
        return facetedProductCategories;
    }
}
