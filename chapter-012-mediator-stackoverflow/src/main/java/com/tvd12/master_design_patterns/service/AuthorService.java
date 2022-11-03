package com.tvd12.master_design_patterns.service;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Author;
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
        final BookApplication bookApplication = BookApplication.getInstance();
        final AuthorRepository authorRepository = bookApplication
            .getDatabaseContext()
            .newRepository(Author.class);
        final BookService bookService = bookApplication
            .getServiceProvider()
            .getService(BookService.class);
        final List<Author> authorEntities = authorRepository
            .findAll();
        final List<BookModel> bookModels = bookService.getBooks();
        final Map<Long, List<BookModel>> booksByAuthorId = new HashMap<>();
        for (BookModel book : bookModels) {
            if (book.getAuthor() == null) {
                continue;
            }
            booksByAuthorId
                .computeIfAbsent(book.getAuthor().getId(), k -> new ArrayList<>())
                .add(book);
        }
        return authorEntities
            .stream()
            .map(entity -> {
                final AuthorModel model = new AuthorModel();
                model.setId(entity.getId());
                model.setName(entity.getName());
                model.setCode(entity.getCode());
                model.setBooks(booksByAuthorId.get(entity.getId()));
                return model;
            })
            .collect(Collectors.toList());
    }
}
