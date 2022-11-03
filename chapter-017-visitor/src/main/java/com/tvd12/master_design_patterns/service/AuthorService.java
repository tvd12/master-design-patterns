package com.tvd12.master_design_patterns.service;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.mediator.BookAndAuthorMediator;
import com.tvd12.master_design_patterns.model.AuthorModel;
import com.tvd12.master_design_patterns.model.BookModel;
import com.tvd12.master_design_patterns.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthorService {

    public List<AuthorModel> getAuthors() throws Exception {
        return BookApplication
            .getInstance()
            .getMediatorProvider()
            .getMediator(BookAndAuthorMediator.class)
            .getAuthors();
    }
}
