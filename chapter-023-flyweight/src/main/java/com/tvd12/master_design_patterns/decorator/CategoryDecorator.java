package com.tvd12.master_design_patterns.decorator;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.model.BookWithoutAuthorModel;
import com.tvd12.master_design_patterns.model.CategoryModel;
import com.tvd12.master_design_patterns.service.BookService;

import java.util.List;

public class CategoryDecorator {

    public void decorate(CategoryModel category) throws Exception {
        final BookService bookService = BookApplication
            .getInstance()
            .getServiceProvider()
            .getService(BookService.class);
        final List<BookWithoutAuthorModel> books = bookService
            .getBooksByCategoryId(category.getId());
        category.setBooks(books);
    }
}
