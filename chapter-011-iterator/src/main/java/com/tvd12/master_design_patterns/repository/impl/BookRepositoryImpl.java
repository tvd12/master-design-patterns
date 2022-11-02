package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.command.IterableCommand;
import com.tvd12.master_design_patterns.command.SaveCommand;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.repository.BookRepository;

import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class BookRepositoryImpl implements BookRepository {

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

    @Override
    public void forEach(Consumer<Book> consumer) throws Exception {
        final IterableCommand<Book> command = BookApplication
            .getInstance()
            .getCommandProvider()
            .provide(IterableCommand.class);
        final Iterable<Book> iterable = command
            .entityType(Book.class)
            .execute();
        for (Book book : iterable) {
            consumer.accept(book);
        }
    }
}
