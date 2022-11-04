package com.tvd12.master_design_patterns.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryModel {
    private long id;
    private String name;
    private List<BookWithoutAuthorModel> books;
}
