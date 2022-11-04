package com.tvd12.master_design_patterns.service;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.builder.AuthorBuilder;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.interpreter.AuthorCodeInterpreter;
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

    public long addAuthor(String authorName) throws Exception {
        final BookApplication bookApplication = BookApplication.getInstance();
        final String code = bookApplication
            .getInterpreterProvider()
            .getInterpreter(AuthorCodeInterpreter.class)
            .translate(authorName);
        final Author author = bookApplication
            .getEntityFactory()
            .newEntityBuilder(AuthorBuilder.class)
            .name(authorName)
            .code(code)
            .build();
        bookApplication
            .getDatabaseContext()
            .newRepository(Author.class)
            .save(author);
        return author.getId();
    };

    public List<AuthorModel> getAuthors() throws Exception {
        return BookApplication
            .getInstance()
            .getMediatorProvider()
            .getMediator(BookAndAuthorMediator.class)
            .getAuthors();
    }
}
