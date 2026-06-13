package com.tvd12.master_design_patterns.storage;

import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.repository.Repository;

public class DbCategoryStorage extends Storage<Category> {

    public DbCategoryStorage(
        Repository<Category> repository
    ) {
        super(repository);
    }

    @Override
    public Category getEntityById(long id) throws Exception {
        return repository.findById(id);
    }
}
