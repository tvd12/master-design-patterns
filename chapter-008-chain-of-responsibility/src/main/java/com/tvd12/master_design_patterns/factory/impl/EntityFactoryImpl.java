package com.tvd12.master_design_patterns.factory.impl;

import com.tvd12.master_design_patterns.builder.AuthorBuilder;
import com.tvd12.master_design_patterns.builder.BookBuilder;
import com.tvd12.master_design_patterns.builder.Builder;
import com.tvd12.master_design_patterns.builder.CategoryBuilder;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.factory.AuthorFactory;
import com.tvd12.master_design_patterns.factory.BookFactory;
import com.tvd12.master_design_patterns.factory.CategoryFactory;
import com.tvd12.master_design_patterns.factory.EntityFactory;

public class EntityFactoryImpl implements EntityFactory {

    private final AuthorFactory authorFactory =
        new AuthorFactoryImpl();
    private final CategoryFactory categoryFactory =
        new CategoryFactoryImpl();
    private final BookFactory bookFactory =
        new BookFactoryImpl();

    @SuppressWarnings("unchecked")
    @Override
    public <E> E newEntity(Class<E> entityType, String name) {
        if (entityType == Author.class) {
            return (E) authorFactory.newAuthor(name);
        }
        if (entityType == Category.class) {
            return (E) categoryFactory.newCategory(name);
        }
        if (entityType == Book.class) {
            return (E) bookFactory.newBook(name);
        }
        throw new IllegalArgumentException("there is no factory for: " + entityType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E, B extends Builder<E>> B newEntityBuilder(Class<B> builderType) {
        if (builderType == AuthorBuilder.class) {
            return (B) authorFactory.newAuthorBuilder();
        }
        if (builderType == CategoryBuilder.class) {
            return (B) categoryFactory.newCategoryBuilder();
        }
        if (builderType == BookBuilder.class) {
            return (B) bookFactory.newBookBuilder();
        }
        throw new IllegalArgumentException("there is no factory for: " + builderType);
    }
}
