package com.tvd12.master_design_patterns.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookModel {
    private long id;
    private String name;
    private AuthorModel author;
}
