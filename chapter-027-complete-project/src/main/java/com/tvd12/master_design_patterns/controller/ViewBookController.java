package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.View;
import com.tvd12.master_design_patterns.service.AuthorService;
import com.tvd12.master_design_patterns.service.BookService;
import com.tvd12.master_design_patterns.service.CategoryService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ViewBookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @DoGet("/books")
    public View home() {
        return View
            .builder()
            .template("books")
            .addVariable("page", "book")
            .addVariable("pageTitle", "Book List")
            .addVariable("books", bookService.getBooks())
            .addVariable("authors", authorService.getAuthors())
            .addVariable("categories", categoryService.getCategories())
            .build();
    }
}
