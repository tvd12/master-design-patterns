package com.tvd12.master_design_patterns.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AuthorModel {
    private long id;
    private String name;
    private String code;
    private List<BookModel> books;
}
