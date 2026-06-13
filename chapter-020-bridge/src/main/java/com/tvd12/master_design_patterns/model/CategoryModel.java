package com.tvd12.master_design_patterns.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryModel {
    private long id;
    private String name;
}
