package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.repository.AuthorRepository;
import com.tvd12.master_design_patterns.repository.BookRepository;
import com.tvd12.master_design_patterns.repository.CategoryRepository;
import com.tvd12.master_design_patterns.repository.DatabaseContext;

public final class BookApplicationStartup {

    public static void main(String[] args) throws Exception {
        final BookApplication application = BookApplication.getInstance();
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
        final Author author = new Author();
        author.setName("Dzung");
        authorRepository.save(author);

        final Category category = new Category();
        category.setName("Technology");
        categoryRepository.save(category);

        final Book book = new Book();
        book.setName("Design Patterns");
        book.setAuthorId(author.getId());
        book.setCategoryId(category.getId());
        bookRepository.save(book);
    }
}
