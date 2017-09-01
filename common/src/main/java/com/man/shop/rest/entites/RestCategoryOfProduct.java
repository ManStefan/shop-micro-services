package com.man.shop.rest.entites;

import java.util.List;

/**
 * Created by smanolache on 4/4/2017.
 */
public class RestCategoryOfProduct {
    private Long id;
    private String name;
    private String description;
    private Integer level;
    private Long parentCategory;
    private List<Long> childCategories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Long parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Long> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Long> childCategories) {
        this.childCategories = childCategories;
    }
}
