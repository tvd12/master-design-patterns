package com.tvd12.master_design_patterns.service;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.model.CategoryModel;
import com.tvd12.master_design_patterns.storage.Storage;

public class CategoryService {

    public CategoryModel getCategoryById(long id) throws Exception {
        final BookApplication bookApplication = BookApplication.getInstance();
        final Storage<Category> storage = bookApplication
            .getStorageProvider()
            .getStorage("cacheAndDbCategoryStorage");
        Category category = storage.getEntityById(id);
        return CategoryModel.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }
}
