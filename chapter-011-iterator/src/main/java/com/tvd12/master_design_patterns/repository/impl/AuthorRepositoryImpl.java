package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.command.IterableCommand;
import com.tvd12.master_design_patterns.command.SaveCommand;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.repository.AuthorRepository;

import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class AuthorRepositoryImpl implements AuthorRepository {

    @SuppressWarnings("unchecked")
    @Override
    public void save(Author author) throws Exception {
        final SaveCommand<Author> command = BookApplication
            .getInstance()
            .getCommandProvider()
            .provide(SaveCommand.class);
        command
            .entity(author)
            .execute();
    }

    @Override
    public void forEach(Consumer<Author> consumer) throws Exception {
        final IterableCommand<Author> command = BookApplication
            .getInstance()
            .getCommandProvider()
            .provide(IterableCommand.class);
        final Iterable<Author> iterable = command
            .entityType(Author.class)
            .execute();
        for (Author author : iterable) {
            consumer.accept(author);
        }
    }
}
