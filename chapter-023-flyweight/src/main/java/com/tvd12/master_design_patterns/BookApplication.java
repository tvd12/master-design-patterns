package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.adapter.AdapterProvider;
import com.tvd12.master_design_patterns.cache.CacheProvider;
import com.tvd12.master_design_patterns.command.CommandProvider;
import com.tvd12.master_design_patterns.decorator.DecoratorProvider;
import com.tvd12.master_design_patterns.factory.ConnectionFactory;
import com.tvd12.master_design_patterns.factory.EntityFactory;
import com.tvd12.master_design_patterns.factory.impl.ConnectionFactoryImpl;
import com.tvd12.master_design_patterns.factory.impl.EntityFactoryImpl;
import com.tvd12.master_design_patterns.interpreter.InterpreterProvider;
import com.tvd12.master_design_patterns.mediator.MediatorProvider;
import com.tvd12.master_design_patterns.pool.ConnectionPool;
import com.tvd12.master_design_patterns.repository.DatabaseContext;
import com.tvd12.master_design_patterns.repository.impl.DatabaseContextImpl;
import com.tvd12.master_design_patterns.service.ServiceProvider;
import com.tvd12.master_design_patterns.strategy.StrategyProvide;
import com.tvd12.master_design_patterns.validator.ValidatorProvider;
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
    @Getter
    private final InterpreterProvider interpreterProvider;
    @Getter
    private final ServiceProvider serviceProvider;
    @Getter
    private final MediatorProvider mediatorProvider;
    @Getter
    private final StrategyProvide strategyProvide;
    @Getter
    private final AdapterProvider adapterProvider;
    @Getter
    private final CacheProvider cacheProvider;
    @Getter
    private final ValidatorProvider validatorProvider;
    @Getter
    private final DecoratorProvider decoratorProvider;

    private static final BookApplication INSTANCE = new BookApplication();

    private BookApplication() {
        entityFactory = new EntityFactoryImpl();
        connectionFactory = new ConnectionFactoryImpl();
        databaseContext = new DatabaseContextImpl();
        connectionPool = new ConnectionPool();
        commandProvider = new CommandProvider();
        interpreterProvider = new InterpreterProvider();
        serviceProvider = new ServiceProvider();
        mediatorProvider = new MediatorProvider();
        strategyProvide = new StrategyProvide();
        adapterProvider = new AdapterProvider();
        cacheProvider = new CacheProvider();
        validatorProvider = new ValidatorProvider();
        decoratorProvider = new DecoratorProvider();
    }

    public static BookApplication getInstance() {
        return INSTANCE;
    }
}