package com.tvd12.master_design_patterns.service;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.model.AuthorModel;
import com.tvd12.master_design_patterns.model.BookModel;
import com.tvd12.master_design_patterns.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookService {

    public List<BookModel> getBooks() throws Exception {
        final BookApplication application = BookApplication.getInstance();
        final BookRepository bookRepository = application
            .getDatabaseContext()
            .newRepository(Book.class);
        final AuthorService authorService = application
            .getServiceProvider()
            .getService(AuthorService.class);
        final List<Book> bookEntities = bookRepository.findAll();
        final List<AuthorModel> authorEntities = authorService.getAuthors();
        final Map<Long, AuthorModel> authorById = authorEntities
            .stream()
            .collect(Collectors.toMap(AuthorModel::getId, it -> it));
        return bookEntities
            .stream()
            .map(entity -> {
                final BookModel model = new BookModel();
                model.setId(entity.getId());
                model.setName(entity.getName());
                model.setAuthor(authorById.get(entity.getAuthorId()));
                return model;
            })
            .collect(Collectors.toList());
    }
}
