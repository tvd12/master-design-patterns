package com.tvd12.master_design_patterns.backup;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlDataBackup implements DataBackup {

    private final Connection toConnection;

    public SqlDataBackup() {
        Connection connection = null;
        try {
            connection = newConnection();
        } catch (Exception e) {
            // do nothing
        }
        this.toConnection = connection;
    }

    @Override
    public void backup(Object data) {
        if (toConnection != null) {
            // backup data
        }
    }

    public Connection newConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/book_market_backup",
            "root",
            "12345678"
        );
    }
}
