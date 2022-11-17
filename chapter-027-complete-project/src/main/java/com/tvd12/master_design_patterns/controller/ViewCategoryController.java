package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.View;
import com.tvd12.master_design_patterns.service.CategoryService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ViewCategoryController {

    private final CategoryService categoryService;

    @DoGet("/categories")
    public View home() {
        return View
            .builder()
            .template("categories")
            .addVariable("page", "category")
            .addVariable("pageTitle", "Book Category List")
            .addVariable("categories", categoryService.getCategories())
            .build();
    }
}
