package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.service.AuthorService;
import com.tvd12.master_design_patterns.service.BookCategoryService;
import com.tvd12.master_design_patterns.service.BookService;
import com.tvd12.master_design_patterns.service.CategoryService;

import java.util.HashMap;
import java.util.Map;

public class BookApplicationStartup {

    public static void main(String[] args) {
        BookStoreGateway bookStoreGateway = new BookStoreGateway(
            new AuthorService(),
            new BookService(),
            new BookCategoryService(),
            new CategoryService()
        );
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("authorName", "DungTV");
        bookData.put("bookName", "Mastering Design Patterns");
        bookData.put("categoryName", "Software Engineering");
        bookStoreGateway.addBook(bookData);
    }
}
