package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.pool.ConnectionPool;
import com.tvd12.master_design_patterns.repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public long countBookByAuthorId(long authorId) throws Exception {
        final String query = "SELECT count(*) FROM Book WHERE authorId = ?";
        final ConnectionPool connectionPool = BookApplication
            .getInstance()
            .getConnectionPool();
        try (
            Connection connection = connectionPool.provide();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setLong(1, authorId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return 0L;
        }
    }
}
