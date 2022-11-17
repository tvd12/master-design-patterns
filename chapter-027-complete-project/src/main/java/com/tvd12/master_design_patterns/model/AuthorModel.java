package com.tvd12.master_design_patterns.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorModel {
    private long id;
    private String name;
    private String code;
    private String level;
}
