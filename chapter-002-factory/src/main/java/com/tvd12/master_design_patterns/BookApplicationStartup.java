package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.factory.AuthorFactory;
import com.tvd12.master_design_patterns.factory.BookFactory;
import com.tvd12.master_design_patterns.factory.CategoryFactory;

public final class BookApplicationStartup {

    public static void main(String[] args) {
        BookApplication application = BookApplication.getInstance();
        AuthorFactory authorFactory = application.getAuthorFactory();
        Author author = authorFactory.newAuthor("Young Monkeys");
        System.out.println(author);

        CategoryFactory categoryFactory = application.getCategoryFactory();
        Category category = categoryFactory.newCategory("Technology");
        System.out.println(category);

        BookFactory bookFactory = application.getBookFactory();
        Book book = bookFactory.newBook("Mastering Design Patterns");
        System.out.println(book);
    }
}

