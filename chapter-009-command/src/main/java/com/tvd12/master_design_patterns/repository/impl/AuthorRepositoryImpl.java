package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.command.SaveCommand;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.repository.AuthorRepository;

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
}
