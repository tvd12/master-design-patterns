package com.tvd12.master_design_patterns.adapter;

import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.model.AddCategoryModel;
import com.tvd12.master_design_patterns.repository.Repository;
import com.tvd12.master_design_patterns.service.DataService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryDataServiceToCategoryRepositoryAdapter
    implements DataService<AddCategoryModel> {

    private Repository<Category> repository;

    @Override
    public long saveModel(AddCategoryModel model) throws Exception {
        final Category category = new Category();
        category.setName(model.getCategoryName());
        repository.save(category);
        return category.getId();
    }
}
