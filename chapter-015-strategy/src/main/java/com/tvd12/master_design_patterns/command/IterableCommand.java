package com.tvd12.master_design_patterns.command;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.observer.VoidNoReturnObserver;
import com.tvd12.master_design_patterns.pool.ConnectionPool;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class IterableCommand<E> implements Command<Iterable<E>> {

    private Class<E> entityType;

    public IterableCommand<E> entityType(Class<E> entityType) {
        this.entityType = entityType;
        return this;
    }

    @Override
    public Iterable<E> execute() throws Exception {
        final String tableName = entityType.getSimpleName();
        final String query = "SELECT * FROM " + tableName + " ORDER BY id DESC";
        final ConnectionPool connectionPool = BookApplication
            .getInstance()
            .getConnectionPool();
        final Connection connection = connectionPool.provide();
        final PreparedStatement statement = connection
            .prepareStatement(query);
        final ResultSet resultSet = statement.executeQuery();
        return () -> new EntityIterator<E>(
            entityType,
            resultSet,
            () -> {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // do nothing
                }
                connectionPool.pushBack(connection);
            }
        );
    }

    @AllArgsConstructor
    private static class EntityIterator<E> implements Iterator<E> {

        private final Class<E> entityType;
        private final ResultSet resultSet;
        private final VoidNoReturnObserver finishObserver;

        @Override
        public boolean hasNext() {
            try {
                final boolean hasNext = resultSet.next();
                if (!hasNext) {
                    finishObserver.observe();
                }
                return hasNext;
            } catch (Exception e) {
                finishObserver.observe();
                throw new IllegalStateException(e);
            }
        }

        @SuppressWarnings("IteratorNextCanNotThrowNoSuchElementException")
        @Override
        public E next() {
            try {
                final E entity = entityType.newInstance();
                final Field[] fields = entityType.getDeclaredFields();
                for (int i = 0 ; i < fields.length ; ++i) {
                    fields[i].setAccessible(true);
                    fields[i].set(entity, resultSet.getObject(i + 1));
                }
                return entity;
            } catch (Throwable e) {
                finishObserver.observe();
                throw new IllegalStateException(e);
            }
        }
    }
}
