package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.factory.ConnectionFactory;
import com.tvd12.master_design_patterns.factory.EntityFactory;
import com.tvd12.master_design_patterns.factory.impl.ConnectionFactoryImpl;
import com.tvd12.master_design_patterns.factory.impl.EntityFactoryImpl;
import com.tvd12.master_design_patterns.repository.DatabaseContext;
import com.tvd12.master_design_patterns.repository.impl.DatabaseContextImpl;
import lombok.Getter;

public final class BookApplication {

    @Getter
    private final EntityFactory entityFactory;
    @Getter
    private final ConnectionFactory connectionFactory;
    @Getter
    private final DatabaseContext databaseContext;
    private static final BookApplication INSTANCE = new BookApplication();

    private BookApplication() {
        entityFactory = new EntityFactoryImpl();
        connectionFactory = new ConnectionFactoryImpl();
        databaseContext = new DatabaseContextImpl();
    }

    public static BookApplication getInstance() {
        return INSTANCE;
    }
}