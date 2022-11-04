package com.tvd12.master_design_patterns.service;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.mediator.BookAndAuthorMediator;
import com.tvd12.master_design_patterns.model.BookModel;
import com.tvd12.master_design_patterns.model.BookWithoutAuthorModel;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    public List<BookModel> getBooks() throws Exception {
        return BookApplication
            .getInstance()
            .getMediatorProvider()
            .getMediator(BookAndAuthorMediator.class)
            .getBooks();
    }

    public List<BookWithoutAuthorModel> getBooksByCategoryId(
        long categoryId
    ) throws Exception {
        return BookApplication
            .getInstance()
            .getDatabaseContext()
            .newRepository(Book.class)
            .findAll()
            .stream()
            .filter(book -> book.getCategoryId() == categoryId)
            .map(book -> {
                BookWithoutAuthorModel model = new BookWithoutAuthorModel();
                model.setId(book.getId());
                model.setName(book.getName());
                return model;
            })
            .collect(Collectors.toList());
    }
}

