package com.tvd12.master_design_patterns.decorator;

import com.tvd12.master_design_patterns.model.CategoryModel;
import com.tvd12.master_design_patterns.response.CategoryResponse;

public class CategoryIdNameDecorator
    implements Decorator<
        CategoryModel,
        CategoryResponse
    > {

    public CategoryResponse decorate(
        CategoryModel category
    ) {
        return CategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }
}
