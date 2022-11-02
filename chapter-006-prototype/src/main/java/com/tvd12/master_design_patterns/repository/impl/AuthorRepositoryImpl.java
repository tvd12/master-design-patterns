package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.factory.ConnectionFactory;
import com.tvd12.master_design_patterns.repository.AuthorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuthorRepositoryImpl implements AuthorRepository {

    private final ConnectionFactory connectionFactory =
        BookApplication.getInstance().getConnectionFactory();

    @Override
    public void save(Author author) throws Exception {
        final String query = "INSERT INTO Author (name) VALUE(?)";
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
