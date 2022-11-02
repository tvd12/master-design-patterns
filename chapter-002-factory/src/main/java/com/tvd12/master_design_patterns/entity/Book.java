package com.tvd12.master_design_patterns.entity;

import lombok.Data;

@Data
public class Book {
    private long id;
    private String name;
    private long authorId;
    private long categoryId;
}
