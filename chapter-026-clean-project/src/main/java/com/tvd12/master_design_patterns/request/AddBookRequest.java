package com.tvd12.master_design_patterns.request;

import lombok.Data;

@Data
public class AddBookRequest {
    private String bookName;
    private long authorId;
    private long categoryId;
    private String bookImage;
}
