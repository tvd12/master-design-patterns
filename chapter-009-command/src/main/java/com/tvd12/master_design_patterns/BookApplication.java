package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.command.CommandProvider;
import com.tvd12.master_design_patterns.factory.ConnectionFactory;
import com.tvd12.master_design_patterns.factory.EntityFactory;
import com.tvd12.master_design_patterns.factory.impl.ConnectionFactoryImpl;
import com.tvd12.master_design_patterns.factory.impl.EntityFactoryImpl;
import com.tvd12.master_design_patterns.pool.ConnectionPool;
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
    @Getter
    private final ConnectionPool connectionPool;
    @Getter
    private final CommandProvider commandProvider;

    private static final BookApplication INSTANCE = new BookApplication();

    private BookApplication() {
        entityFactory = new EntityFactoryImpl();
        connectionFactory = new ConnectionFactoryImpl();
        databaseContext = new DatabaseContextImpl();
        connectionPool = new ConnectionPool();
        commandProvider = new CommandProvider();
    }

    public static BookApplication getInstance() {
        return INSTANCE;
    }
}