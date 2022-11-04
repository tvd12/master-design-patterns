package com.tvd12.master_design_patterns.service;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.cache.CategoryCache;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.model.CategoryModel;
import com.tvd12.master_design_patterns.repository.CategoryRepository;

public class CategoryService {

    public CategoryModel getCategoryById(long id) throws Exception {
        final BookApplication bookApplication = BookApplication.getInstance();
        final CategoryRepository categoryRepository = bookApplication
            .getDatabaseContext()
            .getRepository(Category.class);
        final CategoryCache categoryCache = bookApplication
            .getCacheProvider()
            .getCache(CategoryCache.class);
        Category category = categoryCache.findById(id);
        if (category == null) {
            category = categoryRepository.findById(id);
            categoryCache.save(category);
        }
        final CategoryModel model = new CategoryModel();
        model.setId(category.getId());
        model.setName(category.getName());
        return model;
    }
}
