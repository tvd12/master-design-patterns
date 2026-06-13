package com.tvd12.master_design_patterns.decorator;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.model.BookWithoutAuthorModel;
import com.tvd12.master_design_patterns.model.CategoryModel;
import com.tvd12.master_design_patterns.response.CategoryResponse;
import com.tvd12.master_design_patterns.service.BookService;

import java.util.List;

public class CategoryDecorator
    extends AbstractDecorator<
        CategoryModel,
        CategoryResponse
    > {

    public CategoryDecorator(
        Decorator<
            CategoryModel,
            CategoryResponse
        > decorator
    ) {
        super(decorator);
    }

    @Override
    public CategoryResponse decorate(
        CategoryModel data
    ) throws Exception {
        final BookService bookService = BookApplication
            .getInstance()
            .getServiceProvider()
            .getService(BookService.class);
        final List<BookWithoutAuthorModel> books = bookService
            .getBooksByCategoryId(data.getId());
        return CategoryResponse.builder()
            .copy(super.decorate(data))
            .books(books)
            .build();
    }
}
