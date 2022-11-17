package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.master_design_patterns.model.AuthorModel;
import com.tvd12.master_design_patterns.request.AddAuthorRequest;
import com.tvd12.master_design_patterns.service.AuthorService;
import com.tvd12.master_design_patterns.validator.AuthorValidator;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("/api/v1")
@AllArgsConstructor
public class ApiAuthorController {

    private final AuthorService authorService;
    private final AuthorValidator authorValidator;

    @DoGet("/authors")
    public List<AuthorModel> getAuthors() {
        return authorService.getAuthors();
    }

    @DoPost("/authors/add")
    public ResponseEntity addAuthor(
        @RequestBody AddAuthorRequest request
    ) {
        authorValidator.validate(request);
        authorService.addAuthor(request.getAuthorName());
        return ResponseEntity.noContent();
    }
}
