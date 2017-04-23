package com.man.shop.rest.entites;

import java.util.List;
import java.util.Map;

/**
 * Created by smanolache on 1/19/2017.
 *
 * This class is used as the input of the REST service for Creating/Modifying/Deleting products
 */
public class RestProduct {
    public static final String ATTRIBUTES_CATEGORY_LABEL = "ATTRIBUTES_CATEGORY";
    public static final String ATTRIBUTES_LABEL = "ATTRIBUTES";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private Long id;
    private String name;
    private String description;
    private Boolean promo;
    private Integer promoPercentage;
    private Integer unitsSold;

    private String activationDate;
    private String expireDate;
    private String dateFormat = DEFAULT_DATE_FORMAT;

    private Double priceAmount;
    private String priceCurrency;

    private Long categoryOfProduct;
    private String categoryOfProductText;

    private List<Map<String, Object>> attributes;

    private Integer quantity;
    private Long quantityStandard;
    private String quantityStandardText;

    private Long producer;
    private String producerText;

    private List<Long> pictures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getPromoPercentage() {
        return promoPercentage;
    }

    public void setPromoPercentage(Integer promoPercentage) {
        this.promoPercentage = promoPercentage;
    }

    public Integer getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(Integer unitsSold) {
        this.unitsSold = unitsSold;
    }

    public Boolean getPromo() {
        return promo;
    }

    public void setPromo(Boolean promo) {
        this.promo = promo;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Double getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(Double priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public Long getCategoryOfProduct() {
        return categoryOfProduct;
    }

    public void setCategoryOfProduct(Long categoryOfProduct) {
        this.categoryOfProduct = categoryOfProduct;
    }

    public String getCategoryOfProductText() {
        return categoryOfProductText;
    }

    public void setCategoryOfProductText(String categoryOfProductText) {
        this.categoryOfProductText = categoryOfProductText;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getQuantityStandard() {
        return quantityStandard;
    }

    public void setQuantityStandard(Long quantityStandard) {
        this.quantityStandard = quantityStandard;
    }

    public String getQuantityStandardText() {
        return quantityStandardText;
    }

    public void setQuantityStandardText(String quantityStandardText) {
        this.quantityStandardText = quantityStandardText;
    }

    public Long getProducer() {
        return producer;
    }

    public void setProducer(Long producer) {
        this.producer = producer;
    }

    public String getProducerText() {
        return producerText;
    }

    public void setProducerText(String producerText) {
        this.producerText = producerText;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public List<Map<String, Object>> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Map<String, Object>> attributes) {
        this.attributes = attributes;
    }

    public List<Long> getPictures() {
        return pictures;
    }

    public void setPictures(List<Long> pictures) {
        this.pictures = pictures;
    }
}
