package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.command.SaveCommand;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.repository.BookRepository;

public class BookRepositoryImpl implements BookRepository {

    @SuppressWarnings("unchecked")
    @Override
    public void save(Book book) throws Exception {
        final SaveCommand<Book> command = BookApplication
            .getInstance()
            .getCommandProvider()
            .provide(SaveCommand.class);
        command
            .entity(book)
            .execute();
    }
}
