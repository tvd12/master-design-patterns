package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.factory.AuthorFactory;
import com.tvd12.master_design_patterns.factory.BookFactory;
import com.tvd12.master_design_patterns.factory.CategoryFactory;
import com.tvd12.master_design_patterns.factory.impl.AuthorFactoryImpl;
import com.tvd12.master_design_patterns.factory.impl.BookFactoryImpl;
import com.tvd12.master_design_patterns.factory.impl.CategoryFactoryImpl;
import lombok.Getter;

public final class BookApplication {

    @Getter
    private final AuthorFactory authorFactory;
    @Getter
    private final BookFactory bookFactory;
    @Getter
    private final CategoryFactory categoryFactory;
    private static final BookApplication INSTANCE = new BookApplication();

    private BookApplication() {
        this.authorFactory = new AuthorFactoryImpl();
        this.bookFactory = new BookFactoryImpl();
        this.categoryFactory = new CategoryFactoryImpl();
    }

    public static BookApplication getInstance() {
        return INSTANCE;
    }
}
