package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.builder.BookBuilder;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.factory.EntityFactory;
import com.tvd12.master_design_patterns.handler.ChainOfResponsibility;
import com.tvd12.master_design_patterns.model.BookModel;
import com.tvd12.master_design_patterns.repository.AuthorRepository;
import com.tvd12.master_design_patterns.repository.BookRepository;
import com.tvd12.master_design_patterns.request.AddBookRequest;
import com.tvd12.master_design_patterns.response.AddBookResponse;
import com.tvd12.master_design_patterns.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

@Controller("/api/v1")
public class ApiBookController {

    private final BookApplication bookApplication =
        BookApplication.getInstance();

    private final EntityFactory entityFactory =
        bookApplication.getEntityFactory();

    private final BookRepository bookRepository =
        bookApplication
            .getDatabaseContext()
            .getRepository(Book.class);

    private final AuthorRepository authorRepository =
        bookApplication
            .getDatabaseContext()
            .getRepository(Author.class);

    private final BookService bookService =
        bookApplication
            .getServiceProvider()
            .getService(BookService.class);

    @DoGet("/books")
    public List<BookModel> getBooks() throws Exception {
        return bookService.getBooks();
    }

    @DoPost("/books/add")
    public ResponseEntity addBook(
        @RequestBody AddBookRequest request
    ) {
        return new ChainOfResponsibility()
            .addFirstVoidHandler(() -> {
                final Map<String, String> errors = new HashMap<>();
                if (isBlank(request.getBookName())) {
                    errors.put("authorName", "required");
                }
                if (request.getAuthorId() <= 0) {
                    errors.put("authorId", "required");
                }
                if (request.getCategoryId() <= 0) {
                    errors.put("categoryId", "required");
                }
                if (!errors.isEmpty()) {
                    throw new HttpBadRequestException(errors);
                }
            })
            .addFirstHandle(() -> {
                final Book book = entityFactory
                    .newEntityBuilder(
                        BookBuilder.class
                    )
                    .name(request.getBookName())
                    .authorId(request.getAuthorId())
                    .categoryId(request.getCategoryId())
                    .build();
                bookRepository.save(book);
                authorRepository.updateAuthorLevel(book.getAuthorId());
                return new AddBookResponse(book.getId());
            })
            .handle();
    }
}
