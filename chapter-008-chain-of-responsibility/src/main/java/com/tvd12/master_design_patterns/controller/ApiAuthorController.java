package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.builder.AuthorBuilder;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.factory.EntityFactory;
import com.tvd12.master_design_patterns.handler.ChainOfResponsibility;
import com.tvd12.master_design_patterns.repository.AuthorRepository;
import com.tvd12.master_design_patterns.request.AddAuthorRequest;
import com.tvd12.master_design_patterns.response.AddAuthorResponse;

import java.util.HashMap;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

@Controller("/api/v1")
public class ApiAuthorController {

    private final BookApplication bookApplication =
        BookApplication.getInstance();

    private final EntityFactory entityFactory =
        bookApplication.getEntityFactory();

    private final AuthorRepository authorRepository =
        bookApplication
            .getDatabaseContext()
            .newRepository(Author.class);

    @DoPost("/authors/add")
    public ResponseEntity addAuthor(
        @RequestBody AddAuthorRequest request
    ) {
        return new ChainOfResponsibility()
            .addFirstVoidHandler(() -> {
                final Map<String, String> errors = new HashMap<>();
                if (isBlank(request.getAuthorName())) {
                    errors.put("authorName", "required");
                }
                if (!errors.isEmpty()) {
                    throw new HttpBadRequestException(errors);
                }
            })
            .addFirstHandle(() -> {
                final Author author = entityFactory
                    .newEntityBuilder(AuthorBuilder.class)
                    .name(request.getAuthorName())
                    .build();
                authorRepository.save(author);
                return new AddAuthorResponse(author.getId());
            })
            .handle();
    }
}
