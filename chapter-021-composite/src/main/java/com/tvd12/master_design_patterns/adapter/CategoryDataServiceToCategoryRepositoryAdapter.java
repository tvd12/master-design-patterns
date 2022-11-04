package com.tvd12.master_design_patterns.adapter;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.model.AddCategoryModel;
import com.tvd12.master_design_patterns.repository.Repository;
import com.tvd12.master_design_patterns.service.DataService;

public class CategoryDataServiceToCategoryRepositoryAdapter
    implements DataService<AddCategoryModel> {

    @Override
    public long saveModel(AddCategoryModel model) throws Exception {
        final Repository<Category> repository = BookApplication
            .getInstance()
            .getDatabaseContext()
            .newRepository(Category.class);
        final Category category = new Category();
        category.setName(model.getCategoryName());
        repository.save(category);
        return category.getId();
    }
}
