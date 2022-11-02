package com.tvd12.master_design_patterns.builder;

import com.tvd12.master_design_patterns.entity.Author;

public class AuthorBuilder implements Builder<Author> {

    private long id;
    private String name;

    public AuthorBuilder id(long id) {
        this.id = id;
        return this;
    }

    public AuthorBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Author build() {
        final Author author = new Author();
        author.setId(id);
        author.setName(name);
        return author;
    }
}
