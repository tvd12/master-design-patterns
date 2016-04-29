package com.tvd12.designparttern.objectpool1;

import java.sql.Connection;

public class ObjectPoolDemo {
    public static void main(String args[]) {
      // Do something...

      // Create the ConnectionPool:
      JDBCConnectionPool pool = new JDBCConnectionPool(
        "org.hsqldb.jdbcDriver", "jdbc:hsqldb://localhost/mydb",
        "sa", "secret");

      // Get a connection:
      Connection con = pool.checkOut();

      // Use the connection

      // Return the connection:
      pool.checkIn(con);
      
    }
}