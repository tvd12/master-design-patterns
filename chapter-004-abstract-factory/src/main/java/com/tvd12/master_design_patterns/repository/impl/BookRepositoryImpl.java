package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.factory.ConnectionFactory;
import com.tvd12.master_design_patterns.repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookRepositoryImpl implements BookRepository {

    private final ConnectionFactory connectionFactory =
        BookApplication.getInstance().getConnectionFactory();

    @Override
    public void save(Book book) throws Exception {
        final String query =
            "INSERT INTO Book (name, authorId, categoryId) VALUE(?, ?, ?)";
        try (Connection connection = connectionFactory.newConnection()) {
            try(
                PreparedStatement statement = connection.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS
                )
            ) {
                statement.setString(1, book.getName());
                statement.setLong(2, book.getAuthorId());
                statement.setLong(3, book.getCategoryId());
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        book.setId(generatedKeys.getLong(1));
                    }
                }
            }
        }
    }
}
