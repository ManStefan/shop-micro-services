package com.man.shop.model.solr;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by smanolache on 12/27/2014.
 */
public class SolrProduct {

    @Id
    @Field(Fields.ID)
    private String id;

    @Field(Fields.NAME)
    private String name;

    @Field(Fields.DESCRIPTION)
    private String description;

    @Field(Fields.PRICE_AMOUNT)
    private Double priceAmount;

    @Field(Fields.PRICE_CURRENCY)
    private String priceCurrency;

    @Field(Fields.ATTRIBUTES)
    private List<Long> attributes;

    @Field("*_ls")
    private Map<String, List<Long>> attributes_t;

    @Field(Fields.CATEGORY_OF_PRODUCT)
    private Long categoryOfProduct;

    @Field(Fields.QUANTITY_STANDARD)
    private Long quantityStandard;

    @Field(Fields.QUANTITY)
    private Integer quantity;

    @Field(Fields.PRODUCER)
    private Long producer;

    @Field(Fields.UNITS_SOLD)
    private Integer unitsSold;

    @Field(Fields.PROMO)
    private Boolean promo;

    @Field(Fields.PROMO_PERCENTAGE)
    private Integer promoPercentage;

    @Field(Fields.ACTIVATION_DATE)
    private Date activationDate;

    @Field(Fields.EXPIRE_DATE)
    private Date expireDate;

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Long getQuantityStandard() {
        return quantityStandard;
    }

    public void setQuantityStandard(Long quantityStandard) {
        this.quantityStandard = quantityStandard;
    }

    public Map<String, List<Long>> getAttributes_t() {
        return attributes_t;
    }

    public void setAttributes_t(Map<String, List<Long>> attributes_t) {
        this.attributes_t = attributes_t;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setAttributes(List<Long> attributes) {
        this.attributes = attributes;
    }

    public void setCategoryOfProduct(Long categoryOfProduct) {
        this.categoryOfProduct = categoryOfProduct;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProducer(Long producer) {
        this.producer = producer;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public List<Long> getAttributes() {
        return attributes;
    }

    public Long getCategoryOfProduct() {
        return categoryOfProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getProducer() {
        return producer;
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

    public Integer getPromoPercentage() {
        return promoPercentage;
    }

    public void setPromoPercentage(Integer promoPercentage) {
        this.promoPercentage = promoPercentage;
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

    public static class Fields{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String PRICE_AMOUNT = "priceAmount";
        public static final String PRICE_CURRENCY = "priceCurrency";
        public static final String ATTRIBUTES = "attributes";
        public static final String CATEGORY_OF_PRODUCT = "categoryOfProduct";
        public static final String QUANTITY = "quantity";
        public static final String PRODUCER = "producer";
        public static final String UNITS_SOLD = "unitsSold";
        public static final String PROMO = "promo";
        public static final String PROMO_PERCENTAGE = "promoPercentage";
        public static final String ACTIVATION_DATE = "activationDate";
        public static final String EXPIRE_DATE = "expireDate";
        public static final String QUANTITY_STANDARD = "quantityStandard";

    }
}
