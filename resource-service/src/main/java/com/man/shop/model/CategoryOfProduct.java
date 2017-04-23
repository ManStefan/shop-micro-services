package com.man.shop.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CATEGORY_OF_PRODUCT")
public class CategoryOfProduct extends BasicEntity{
	protected String name;
	protected Integer level;
	protected String description;

	protected List<Product> products;

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="level", nullable=false)
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryOfProduct")
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
