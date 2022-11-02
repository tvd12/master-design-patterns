package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.repository.DatabaseContext;
import com.tvd12.master_design_patterns.repository.Repository;

public class DatabaseContextImpl implements DatabaseContext {

    @SuppressWarnings("unchecked")
    @Override
    public <E, R extends Repository<E>> R newRepository(Class<E> entityType) {
        if (entityType == Author.class) {
            return (R) new AuthorRepositoryImpl();
        }
        if (entityType == Category.class) {
            return (R) new CategoryRepositoryImpl();
        }
        if (entityType == Book.class) {
            return (R) new BookRepositoryImpl();
        }
        throw new IllegalArgumentException("there is no repository for " + entityType.getName());
    }
}
