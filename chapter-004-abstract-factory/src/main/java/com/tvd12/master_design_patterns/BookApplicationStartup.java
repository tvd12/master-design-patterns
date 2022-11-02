package com.tvd12.master_design_patterns;

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
        final Author author = entityFactory.newEntity(
            Author.class,
            "Dzung"
        );
        authorRepository.save(author);

        final Category category = entityFactory.newEntity(
            Category.class,
            "Technology"
        );
        categoryRepository.save(category);

        final Book book = entityFactory.newEntity(
            Book.class,
            "Design Patterns"
        );
        book.setAuthorId(author.getId());
        book.setCategoryId(category.getId());
        bookRepository.save(book);
    }
}
