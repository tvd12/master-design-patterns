package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.View;
import com.tvd12.master_design_patterns.service.AuthorService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ViewAuthorController {

    private final AuthorService authorService;

    @DoGet("/authors")
    public View home() {
        return View
            .builder()
            .template("authors")
            .addVariable("page", "author")
            .addVariable("pageTitle", "Book Author List")
            .addVariable("authors", authorService.getAuthors())
            .build();
    }
}
