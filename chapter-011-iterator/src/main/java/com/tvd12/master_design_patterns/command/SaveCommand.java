package com.tvd12.master_design_patterns.command;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.pool.ConnectionPool;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SaveCommand<E> implements NoReturnCommand {

    private E entity;

    public SaveCommand<E> entity(E entity) {
        this.entity = entity;
        return this;
    }

    @Override
    public void voidExecute() throws Exception {
        final Class<?> entityType = entity.getClass();
        final Field[] fields = entityType.getDeclaredFields();
        final String tableName = entityType.getSimpleName();
        final StringBuilder insertFields = new StringBuilder();
        final StringBuilder paramMarks = new StringBuilder();
        for (int i = 1 ; i < fields.length ; ++i) {
            insertFields.append(fields[i].getName());
            paramMarks.append('?');
            if (i < fields.length - 1) {
                insertFields.append(", ");
                paramMarks.append(", ");
            }
        }
        final String query =
            "INSERT INTO " + tableName +
                " (" + insertFields + ") VALUE(" + paramMarks + ')';
        final ConnectionPool connectionPool = BookApplication
            .getInstance()
            .getConnectionPool();
        final Connection connection = connectionPool.provide();
        try {
            try(
                PreparedStatement statement = connection.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS
                )
            ) {
                for (int i = 1 ; i < fields.length ; ++i) {
                    fields[i].setAccessible(true);
                    statement.setObject(i, fields[i].get(entity));
                }
                statement.executeUpdate();
                final Field idField = fields[0];
                idField.setAccessible(true);
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idField.set(entity, generatedKeys.getLong(1));
                    }
                }
            }
        } finally {
            connectionPool.pushBack(connection);
        }
    }
}
