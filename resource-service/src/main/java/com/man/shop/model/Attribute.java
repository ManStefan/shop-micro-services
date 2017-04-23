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
	
}
