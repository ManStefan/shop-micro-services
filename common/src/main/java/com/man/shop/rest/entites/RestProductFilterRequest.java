package com.man.shop.rest.entites;

import java.util.*;

/**
 * Created by smanolache on 1/8/2015.
 *
 * This is a bean that reflects the options checked by the user on the UI
 */
public class RestProductFilterRequest {

    public RestProductFilterRequest(){
        this.priceAmount = new ArrayList<>();
        this.attributes = new HashMap<>();
        this.facetedCategoriesOfAttributes = new HashSet<>();
        this.categoriesOfProduct = new ArrayList<>();
        this.sort = new LinkedHashMap<>();
    }

    private String name;

    private String description;

    private String searchWord;

    //<min, max>
    private List< Map.Entry<Double, Double>> priceAmount;

    private Map<Long, List<Long>> attributes;

    private Set<Long> facetedCategoriesOfAttributes;

    private List<Long> categoriesOfProduct;

    private Boolean inStock;

    private Boolean active =true;

    private Boolean inactive = true;

    private Boolean promo;

    private List<Long> producers;

    private LinkedHashMap<String, String> sort;

    private Integer pageSize;

    private Integer pageNumber;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public void setPriceAmount(List<Map.Entry<Double, Double>> priceAmount) {
        this.priceAmount = priceAmount;
    }

    public void addPriceAmount(Map.Entry<Double, Double> priceAmount){
        this.priceAmount.add(priceAmount);
    }

    public void setAttributes(Map<Long, List<Long>> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Long attrCatId, Long attrId){
        if (attributes.get(attrCatId) != null){
            attributes.get(attrCatId).add(attrId);
        } else {
            List<Long> attrs = new ArrayList<>();
            attrs.add(attrId);
            attributes.put(attrCatId, attrs);
        }
    }

    public void setFacetedCategoriesOfAttributes(Set<Long> facetedCategoriesOfAttributes) {
        this.facetedCategoriesOfAttributes = facetedCategoriesOfAttributes;
    }

    public void AddFacetedCategoryOfAttribute(Long attrCat){
        this.facetedCategoriesOfAttributes.add(attrCat);
    }

    public void setCategoriesOfProduct(List<Long> categoriesOfProduct) {
        this.categoriesOfProduct = categoriesOfProduct;
    }

    public void addCategoriesOfProduct(Long categoryOfProductId){
        this.categoriesOfProduct.add(categoryOfProductId);
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public void setPromo(Boolean promo) {
        this.promo = promo;
    }

    public void setProducers(List<Long> producers) {
        this.producers = producers;
    }

    public void addProducers(Long producerId){
        this.producers.add(producerId);
    }

    public void setSort(LinkedHashMap<String, String> sort) {
        this.sort = sort;
    }

    public void addSort(String sortTerm, String sortOrder){
        this.sort.put(sortTerm, sortOrder);
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public List<Map.Entry<Double, Double>> getPriceAmount() {
        return priceAmount;
    }

    public Map<Long, List<Long>> getAttributes() {
        return attributes;
    }

    public Set<Long> getFacetedCategoriesOfAttributes() {
        return facetedCategoriesOfAttributes;
    }

    public List<Long> getCategoriesOfProduct() {
        return categoriesOfProduct;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public Boolean getPromo() {
        return promo;
    }

    public List<Long> getProducers() {
        return producers;
    }

    public LinkedHashMap<String, String> getSort() {
        return sort;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }
}
