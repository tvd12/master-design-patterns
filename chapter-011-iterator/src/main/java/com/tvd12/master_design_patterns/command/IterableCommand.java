package com.tvd12.master_design_patterns.command;

import com.tvd12.master_design_patterns.BookApplication;
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
            connection,
            connectionPool,
            statement,
            resultSet
        );
    }

    @AllArgsConstructor
    private static class EntityIterator<E> implements Iterator<E> {

        private final Class<E> entityType;
        private final Connection connection;
        private final ConnectionPool connectionPool;
        private final PreparedStatement statement;
        private final ResultSet resultSet;

        @Override
        public boolean hasNext() {
            try {
                final boolean hasNext = resultSet.next();
                if (!hasNext) {
                    statement.close();
                    connectionPool.pushBack(connection);
                }
                return hasNext;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

        @SuppressWarnings("IteratorNextCanNotThrowNoSuchElementException")
        @Override
        public E next() {
            try {
                final E entity = entityType
                    .getDeclaredConstructor()
                    .newInstance();
                final Field[] fields = entityType.getDeclaredFields();
                for (int i = 0 ; i < fields.length ; ++i) {
                    fields[i].setAccessible(true);
                    fields[i].set(entity, resultSet.getObject(i + 1));
                }
                return entity;
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
