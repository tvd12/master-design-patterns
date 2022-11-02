package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.factory.ConnectionFactory;
import com.tvd12.master_design_patterns.factory.impl.ConnectionFactoryImpl;
import com.tvd12.master_design_patterns.repository.DatabaseContext;
import com.tvd12.master_design_patterns.repository.impl.DatabaseContextImpl;
import lombok.Getter;

public final class BookApplication {

    @Getter
    private final ConnectionFactory connectionFactory;
    @Getter
    private final DatabaseContext databaseContext;
    private static final BookApplication INSTANCE = new BookApplication();

    private BookApplication() {
        connectionFactory = new ConnectionFactoryImpl();
        databaseContext = new DatabaseContextImpl();
    }

    public static BookApplication getInstance() {
        return INSTANCE;
    }
}
