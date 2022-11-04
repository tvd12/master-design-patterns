package com.tvd12.master_design_patterns.entity;

import lombok.Data;

@Data
public class Book implements Cloneable {
    private long id;
    private String name;
    private long authorId;
    private long categoryId;

    @Override
    public Book clone() {
        final Book clone = new Book();
        clone.name = name;
        clone.authorId = authorId;
        clone.categoryId = categoryId;
        return clone;
    }
}
