package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.pool.ConnectionPool;
import com.tvd12.master_design_patterns.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public void save(Category author) throws Exception {
        final String query = "INSERT INTO Category (name) VALUE(?)";
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
                statement.setString(1, author.getName());
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        author.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } finally {
            connectionPool.pushBack(connection);
        }
    }
}
