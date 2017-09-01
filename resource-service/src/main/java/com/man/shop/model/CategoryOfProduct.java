package com.man.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORY_OF_PRODUCT")
public class CategoryOfProduct extends BasicEntity{
	protected String name;
	protected String description;
	protected Integer level;

	protected CategoryOfProduct parentCategory;

    protected List<CategoryOfProduct> childCategories;

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

	@Column(name="level", nullable = false)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    public CategoryOfProduct getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryOfProduct parentCategory) {
        this.parentCategory = parentCategory;
    }

    @OneToMany(mappedBy="parentCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<CategoryOfProduct> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<CategoryOfProduct> childCategories) {
        this.childCategories = childCategories;
    }
}
