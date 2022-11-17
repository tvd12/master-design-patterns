package com.tvd12.master_design_patterns.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookModel {
    private long id;
    private String name;
    private AuthorModel author;
    private CategoryModel category;
    private String imageName;
}
