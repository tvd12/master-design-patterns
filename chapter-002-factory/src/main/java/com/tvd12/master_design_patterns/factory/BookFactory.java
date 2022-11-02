package com.tvd12.master_design_patterns.factory;

import com.tvd12.master_design_patterns.entity.Book;

public interface BookFactory {

    Book newBook(String name);
}
