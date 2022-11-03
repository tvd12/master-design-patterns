package com.tvd12.master_design_patterns.command;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.pool.ConnectionPool;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindByIdCommand<E> implements Command<E> {

    private Class<E> entityType;
    private long entityId;

    public FindByIdCommand<E> entityType(Class<E> entityType) {
        this.entityType = entityType;
        return this;
    }

    public FindByIdCommand<E> entityId(long entityId) {
        this.entityId = entityId;
        return this;
    }

    @Override
    public E execute() throws Exception {
        final String tableName = entityType.getSimpleName();
        final String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        final ConnectionPool connectionPool = BookApplication
            .getInstance()
            .getConnectionPool();
        final Connection connection = connectionPool.provide();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entityId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                final E entity = entityType.newInstance();
                final Field[] fields = entityType.getDeclaredFields();
                for (int i = 0; i < fields.length; ++i) {
                    fields[i].setAccessible(true);
                    fields[i].set(entity, resultSet.getObject(i + 1));
                }
                return entity;
            }
            return null;
        } finally {
            connectionPool.pushBack(connection);
        }
    }
}
