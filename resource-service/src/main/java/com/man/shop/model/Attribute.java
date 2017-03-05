package com.man.shop.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ATTRIBUTE")
public class Attribute extends BasicEntity{

	protected String name;
	protected CategoryOfAttribute categoryOfAttribute;

	protected List<Product> products;
	
	@Column(name="name", nullable=false, unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cat_attr_id")
	@Fetch(FetchMode.SELECT)
	public CategoryOfAttribute getCategoryOfAttribute() {
		return categoryOfAttribute;
	}
	public void setCategoryOfAttribute(CategoryOfAttribute categoryOfAttribute) {
		this.categoryOfAttribute = categoryOfAttribute;
	}
	
	@ManyToMany(mappedBy="attributes", fetch = FetchType.LAZY)
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	protected String createdBy;
	protected String modifiedBy;
	protected Date creationDate;
	protected Date modifiedDate;

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "creation_date")
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
