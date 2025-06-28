package com.tvd12.master_design_patterns.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Order {
    private long id;
    private String bookName;
}
