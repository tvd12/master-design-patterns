package com.tvd12.master_design_patterns.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddBookModel {
    private String bookName;
    private long authorId;
    private long categoryId;
    private String imageName;
}
