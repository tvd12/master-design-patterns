package com.tvd12.master_design_patterns;

public final class BookApplication {

    private static final BookApplication INSTANCE = new BookApplication();

    private BookApplication() {}

    public static BookApplication getInstance() {
        return INSTANCE;
    }
}
