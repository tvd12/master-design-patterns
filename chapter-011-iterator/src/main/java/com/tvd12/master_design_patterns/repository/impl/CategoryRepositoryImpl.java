package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.command.IterableCommand;
import com.tvd12.master_design_patterns.command.SaveCommand;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.repository.CategoryRepository;

import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public void save(Category category) throws Exception {
        final SaveCommand<Category> command = BookApplication
            .getInstance()
            .getCommandProvider()
            .provide(SaveCommand.class);
        command
            .entity(category)
            .execute();
    }

    @Override
    public void forEach(Consumer<Category> consumer) throws Exception {
        final IterableCommand<Category> command = BookApplication
            .getInstance()
            .getCommandProvider()
            .provide(IterableCommand.class);
        final Iterable<Category> iterable = command
            .entityType(Category.class)
            .execute();
        for (Category book : iterable) {
            consumer.accept(book);
        }
    }
}
