package com.tvd12.master_design_patterns.command;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindByIdCommand<E> extends AbstractCommand<E> {

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
    protected PreparedStatement createStatement(
        Connection connection
    ) throws Exception {
        final String tableName = entityType.getSimpleName();
        final String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        final PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, entityId);
        return statement;
    }

    @Override
    protected E executeStatement(
        PreparedStatement statement
    ) throws Exception {
        final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            final E entity = entityType
                .getDeclaredConstructor()
                .newInstance();
            final Field[] fields = entityType.getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                fields[i].setAccessible(true);
                fields[i].set(entity, resultSet.getObject(i + 1));
            }
            return entity;
        }
        return null;
    }
}
