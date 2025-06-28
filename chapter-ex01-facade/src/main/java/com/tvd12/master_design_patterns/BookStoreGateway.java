package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.service.AuthorService;
import com.tvd12.master_design_patterns.service.BookCategoryService;
import com.tvd12.master_design_patterns.service.BookService;
import com.tvd12.master_design_patterns.service.CategoryService;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class BookStoreGateway {

    private final AuthorService authorService;
    private final BookService bookService;
    private BookCategoryService bookCategoryService;
    private final CategoryService categoryService;

    public void addBook(
        Map<String, Object> bookData
    ) {
        String authorName = (String) bookData.get("authorName");
        long authorId = authorService.addAuthor(authorName);
        long bookId = bookService.addBook(
            authorId,
            (String) bookData.get("bookName")
        );
        String categoryName = (String) bookData.get("categoryName");
        long categoryId = categoryService.addCategory(categoryName);
        bookCategoryService.addBookCategory(
            bookId,
            categoryId
        );
    }
}
