package com.tvd12.master_design_patterns.entity;

import lombok.Data;

@Data
public class Author implements Cloneable {
    private long id;
    private String name;
    private String code;

    @Override
    public Author clone() {
        final Author clone = new Author();
        clone.name = name;
        return clone;
    }
}
