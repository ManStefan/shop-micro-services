package com.man.shop.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="PICTURE")
public class Picture extends BasicEntity{
	protected String name;
	protected String description;
	protected boolean main;

	protected Product product;

	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name="main")
	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
}
