package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.repository.DatabaseContext;
import com.tvd12.master_design_patterns.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class DatabaseContextImpl implements DatabaseContext {

    private final Map<Class<?>, Repository<?>> repositoryByEntityType;

    public DatabaseContextImpl() {
        repositoryByEntityType = new HashMap<>();
        repositoryByEntityType.put(Author.class, new AuthorRepositoryImpl());
        repositoryByEntityType.put(Category.class, new CategoryRepositoryImpl());
        repositoryByEntityType.put(Book.class, new BookRepositoryImpl());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E, R extends Repository<E>> R getRepository(Class<E> entityType) {
        return (R) repositoryByEntityType.get(entityType);
    }
}
