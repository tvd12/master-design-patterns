package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.factory.ConnectionFactory;
import com.tvd12.master_design_patterns.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final ConnectionFactory connectionFactory =
        BookApplication.getInstance().getConnectionFactory();

    @Override
    public void save(Category author) throws Exception {
        final String query = "INSERT INTO Category (name) VALUE(?)";
        try (Connection connection = connectionFactory.newConnection()) {
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
        }
    }
}
