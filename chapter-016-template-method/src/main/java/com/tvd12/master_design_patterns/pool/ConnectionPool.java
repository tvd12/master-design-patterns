package com.tvd12.master_design_patterns.pool;

import com.tvd12.master_design_patterns.BookApplication;

import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool {

    private final LinkedBlockingQueue<Connection> connectionQueue =
        new LinkedBlockingQueue<>();
    private final AtomicInteger numberOfCreatedConnections =
        new AtomicInteger();

    private static final int MAX_CONNECTION = 16;

    public Connection provide() throws Exception {
        synchronized (this) {
            if (numberOfCreatedConnections.get() < MAX_CONNECTION) {
                final Connection connection = BookApplication
                    .getInstance()
                    .getConnectionFactory()
                    .newConnection();
                numberOfCreatedConnections.incrementAndGet();
                return connection;
            }
        }
        return connectionQueue.take();
    }

    public void pushBack(Connection connection) {
        connectionQueue.offer(connection);
    }
}
