package com.tvd12.master_design_patterns.factory.impl;

import com.tvd12.master_design_patterns.builder.BookBuilder;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.factory.BookFactory;

public class BookFactoryImpl implements BookFactory {

    @Override
    public Book newBook(String name) {
        final Book book = new Book();
        book.setName(name);
        return book;
    }

    @Override
    public BookBuilder newBookBuilder() {
        return new BookBuilder();
    }
}
