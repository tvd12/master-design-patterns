package com.tvd12.master_design_patterns.factory.impl;

import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.factory.AuthorFactory;

public class AuthorFactoryImpl implements AuthorFactory {

    @Override
    public Author newAuthor(String name) {
        final Author author = new Author();
        author.setName(name);
        return author;
    }
}
