package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.View;
import com.tvd12.master_design_patterns.service.BookService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ViewHomeController {

    private final BookService bookService;

    @DoGet("/")
    public View home() {
        return View
            .builder()
            .template("home")
            .addVariable("page", "home")
            .addVariable("pageTitle", "Welcome to book market")
            .addVariable("books", bookService.getBooks())
            .build();
    }
}
