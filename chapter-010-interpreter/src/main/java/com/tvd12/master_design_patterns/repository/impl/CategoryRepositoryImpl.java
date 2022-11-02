package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.command.SaveCommand;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.repository.CategoryRepository;

public class CategoryRepositoryImpl implements CategoryRepository {

    @SuppressWarnings("unchecked")
    @Override
    public void save(Category category) throws Exception {
        final SaveCommand<Category> command = BookApplication
            .getInstance()
            .getCommandProvider()
            .provide(SaveCommand.class);
        command
            .entity(category)
            .execute();
    }
}
