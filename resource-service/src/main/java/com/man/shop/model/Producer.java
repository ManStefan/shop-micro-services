package com.man.shop.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="PRODUCER")
public class Producer extends BasicEntity{
	protected String name;

	protected List<Product> products;

	@Column(name="name", nullable=false, unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producer")
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
