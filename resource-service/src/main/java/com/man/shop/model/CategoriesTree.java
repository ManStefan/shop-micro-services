package com.man.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIES_TREE")
public class CategoriesTree extends BasicEntity{

	protected Long parentCategoryId;
	protected Long childCategoryId;
	
	@Column(name="parent_cat_id", nullable=false)
	public Long getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
	@Column(name="child_cat_id", nullable=false)
	public Long getChildCategoryId() {
		return childCategoryId;
	}
	public void setChildCategoryId(Long childCategoryId) {
		this.childCategoryId = childCategoryId;
	}
}
