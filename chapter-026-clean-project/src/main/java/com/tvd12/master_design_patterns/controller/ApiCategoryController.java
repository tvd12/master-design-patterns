package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.master_design_patterns.model.CategoryModel;
import com.tvd12.master_design_patterns.request.AddCategoryRequest;
import com.tvd12.master_design_patterns.service.CategoryService;
import com.tvd12.master_design_patterns.validator.CategoryValidator;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("/api/v1")
@AllArgsConstructor
public class ApiCategoryController {

    private final CategoryService categoryService;
    private final CategoryValidator categoryValidator;

    @DoGet("/categories")
    public List<CategoryModel> getCategories() throws Exception {
        return categoryService.getCategories();
    }

    @DoPost("/categories/add")
    public ResponseEntity addCategory(
        @RequestBody AddCategoryRequest request
    ) {
        categoryValidator.validate(request);
        categoryService.addCategory(
            request.getCategoryName()
        );
        return ResponseEntity.noContent();
    }
}
