package com.man.shop.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CATEGORY_OF_ATTRIBUTE")
public class CategoryOfAttribute extends BasicEntity{

	protected String name;

	protected List<Attribute> attributes;
	
	@Column(name="name", nullable=false, unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="categoryOfAttribute")
	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
}
