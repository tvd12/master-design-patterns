package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.builder.AuthorBuilder;
import com.tvd12.master_design_patterns.builder.BookBuilder;
import com.tvd12.master_design_patterns.builder.CategoryBuilder;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.factory.EntityFactory;
import com.tvd12.master_design_patterns.repository.AuthorRepository;
import com.tvd12.master_design_patterns.repository.BookRepository;
import com.tvd12.master_design_patterns.repository.CategoryRepository;
import com.tvd12.master_design_patterns.repository.DatabaseContext;

public final class BookApplicationStartup {

    public static void main(String[] args) throws Exception {
        final BookApplication application = BookApplication.getInstance();
        final EntityFactory entityFactory = application.getEntityFactory();
        final DatabaseContext databaseContext = application.getDatabaseContext();
        final AuthorRepository authorRepository = databaseContext.newRepository(
            Author.class
        );
        final CategoryRepository categoryRepository = databaseContext.newRepository(
            Category.class
        );
        final BookRepository bookRepository = databaseContext.newRepository(
            Book.class
        );
        final Author author = entityFactory
            .newEntityBuilder(AuthorBuilder.class)
            .name("Dzung")
            .build();
        authorRepository.save(author);

        final Category category = entityFactory
            .newEntityBuilder(CategoryBuilder.class)
            .name("Technology")
            .build();
        categoryRepository.save(category);

        final Book book = entityFactory
            .newEntityBuilder(BookBuilder.class)
            .name("Design Patterns")
            .authorId(author.getId())
            .categoryId(category.getId())
            .build();
        book.setAuthorId(author.getId());
        book.setCategoryId(category.getId());
        bookRepository.save(book);
    }
}
