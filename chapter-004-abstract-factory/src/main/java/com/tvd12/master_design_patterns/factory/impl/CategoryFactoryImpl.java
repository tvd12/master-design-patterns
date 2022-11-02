package com.tvd12.master_design_patterns.factory.impl;

import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.factory.CategoryFactory;

public class CategoryFactoryImpl implements CategoryFactory {

    @Override
    public Category newCategory(String name) {
        final Category category = new Category();
        category.setName(name);
        return category;
    }
}
