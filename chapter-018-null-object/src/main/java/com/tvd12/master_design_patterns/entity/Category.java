package com.tvd12.master_design_patterns.entity;

import lombok.Data;

@Data
public class Category implements Cloneable {
    private long id;
    private String name;

    @Override
    public Category clone() {
        final Category clone = new Category();
        clone.name = name;
        return clone;
    }
}
