package com.tvd12.master_design_patterns.repository.impl;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.pool.ConnectionPool;
import com.tvd12.master_design_patterns.repository.AuthorRepository;
import com.tvd12.master_design_patterns.repository.BookRepository;
import com.tvd12.master_design_patterns.strategy.AuthorLevelStrategyContext;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuthorRepositoryImpl implements AuthorRepository {

    @Override
    public void updateAuthorLevel(long authorId) throws Exception {
        final Author author = findById(authorId);
        if (author == null) {
            return;
        }
        final BookApplication bookApplication = BookApplication.getInstance();
        final BookRepository bookRepository = bookApplication
            .getDatabaseContext()
            .newRepository(Book.class);
        final long bookCount = bookRepository.countBookByAuthorId(authorId);

        final AuthorLevelStrategyContext authorLevelStrategyContext = bookApplication
            .getStrategyProvide()
            .getStrategy(AuthorLevelStrategyContext.class);
        final String authorLevel = authorLevelStrategyContext.decideAuthorLevel(bookCount);

        final String query = "UPDATE Author SET level = ? WHERE id = ?";
        final ConnectionPool connectionPool = BookApplication
            .getInstance()
            .getConnectionPool();
        try (
            Connection connection = connectionPool.provide();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, authorLevel);
            statement.setLong(2, authorId);
            statement.executeUpdate();
        }
    }
}
