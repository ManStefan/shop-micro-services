package com.man.shop.model;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="PRODUCT")
public class Product extends BasicEntity{

	protected String name;
	protected MonetaryAmount price;
	protected Integer quantity;
	protected String description;
	protected Boolean promo;
	protected Integer promoPercentage;
	protected Integer unitsSold;
	protected Date activationDate;
	protected Date expireDate;

	protected CategoryOfProduct categoryOfProduct;
	protected QuantityStandard quantityStandard;
	protected Producer producer;
	
	protected List<Attribute> attributes;
	protected List<Picture> pictures;
	
	@Column(name="name", nullable=false, unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Type(type = "com.man.shop.hibernate.PersistentMoneyUserType")
	@Columns(columns = {
	        @Column(name="price_currency"),
            @Column(name="price_amount")
    })
	public MonetaryAmount getPrice() {
		return price;
	}
	public void setPrice(MonetaryAmount price) {
		this.price = price;
	}
	
	@Column(name="quantity", nullable=false)
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name="promo_percentage")
	public Integer getPromoPercentage(){
		return promoPercentage;
	}

	@Column(name="units_sold")
	public Integer getUnitsSold() {
		return unitsSold;
	}

	public void setPromoPercentage(Integer promoPercentage) {
		this.promoPercentage = promoPercentage;
	}

	public void setUnitsSold(Integer unitsSold) {
		this.unitsSold = unitsSold;
	}

	@Column(name="activation_date")
	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	@Column(name = "expire_date")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="promo")
	public Boolean getPromo(){
		return promo;
	}

	public void setPromo(Boolean promo){
		this.promo = promo;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_prod_id")
	public CategoryOfProduct getCategoryOfProduct() {
		return categoryOfProduct;
	}
	public void setCategoryOfProduct(CategoryOfProduct category) {
		this.categoryOfProduct = category;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="quantity_standard")
	public QuantityStandard getQuantityStandard() {
		return quantityStandard;
	}
	public void setQuantityStandard(QuantityStandard quantityStandard) {
		this.quantityStandard = quantityStandard;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="producer_id")	
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name="ATTRIBUTE_PRODUCT",
			   	joinColumns=@JoinColumn(name="product_id"),
			   	inverseJoinColumns=@JoinColumn(name="attribute_id"))
	@Fetch(FetchMode.SELECT)
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
	@Fetch(FetchMode.SELECT)
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}


}
