package com.tvd12.master_design_patterns.factory;

import com.tvd12.master_design_patterns.builder.BookBuilder;
import com.tvd12.master_design_patterns.entity.Book;

public interface BookFactory {

    Book newBook(String name);

    BookBuilder newBookBuilder();
}
