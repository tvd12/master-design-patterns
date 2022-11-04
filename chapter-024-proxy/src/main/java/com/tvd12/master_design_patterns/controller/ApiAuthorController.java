package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.builder.AuthorBuilder;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.factory.EntityFactory;
import com.tvd12.master_design_patterns.handler.ChainOfResponsibility;
import com.tvd12.master_design_patterns.interpreter.AuthorCodeInterpreter;
import com.tvd12.master_design_patterns.repository.AuthorRepository;
import com.tvd12.master_design_patterns.request.AddAuthorRequest;
import com.tvd12.master_design_patterns.response.AddAuthorResponse;
import com.tvd12.master_design_patterns.validator.AuthorValidator;
import com.tvd12.master_design_patterns.visitor.CollectValidAuthorVisitor;

import java.util.List;

@Controller("/api/v1")
public class ApiAuthorController {

    private final BookApplication bookApplication =
        BookApplication.getInstance();

    private final EntityFactory entityFactory =
        bookApplication.getEntityFactory();

    private final AuthorRepository authorRepository =
        bookApplication
            .getDatabaseContext()
            .getRepository(Author.class);

    private final AuthorValidator authorValidator =
        bookApplication
            .getValidatorProvider()
            .getValidator(AuthorValidator.class);

    @DoGet("/authors")
    public List<Author> getAuthors() throws Exception {
        return authorRepository.findAll();
    }

    @DoGet("/authors/valid")
    public List<Author> getValidAuthors() throws Exception {
        final CollectValidAuthorVisitor visitor = new CollectValidAuthorVisitor();
        authorRepository.forEach(author -> author.accept(visitor));
        return visitor.getValidAuthors();
    }

    @DoPost("/authors/add")
    public ResponseEntity addAuthor(
        @RequestBody AddAuthorRequest request
    ) {
        return new ChainOfResponsibility()
            .addFirstVoidHandler(() ->
                authorValidator.validate(request)
            )
            .addFirstHandle(() -> {
                final String code = BookApplication
                    .getInstance()
                    .getInterpreterProvider()
                    .getInterpreter(AuthorCodeInterpreter.class)
                    .translate(request.getAuthorName());
                final Author author = entityFactory
                    .newEntityBuilder(AuthorBuilder.class)
                    .name(request.getAuthorName())
                    .code(code)
                    .build();
                authorRepository.save(author);
                return new AddAuthorResponse(author.getId());
            })
            .handle();
    }
}
