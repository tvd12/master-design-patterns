package com.tvd12.designparttern.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

	private final int minConnections;
	private final int maxConnections;
	private final Queue<Connection> connections;

	{
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (Exception e) {
	    	throw new IllegalStateException(e);
	    }
	}
	
	public ConnectionPool(int minConnections, int maxConnections) {
		this.minConnections = minConnections;
		this.maxConnections = maxConnections;
		this.connections = new LinkedList<>();
		for(int i = 0 ; i < this.minConnections ; ++i) {
			connections.offer(createConnection());
		}
	}
	
	public Connection getConnection() {
		synchronized (connections) {
			if(connections.isEmpty())
				return createConnection();
			return connections.poll();
		}
	}
	
	public void returnConnection(Connection connection) {
		synchronized (connections) {
			if(connections.size() >= maxConnections)
				closeConnection(connection);
			connections.offer(connection);
		}
	}
	
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		}
		catch (Exception e) {
		}
	}
	
	private Connection createConnection() {
		try {
			return (DriverManager.getConnection("url", "username", "password"));
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		ConnectionPool connectionPool = new ConnectionPool(10, 300);
		Connection connection = connectionPool.getConnection();
		try {
			connection.createStatement().executeQuery("SELECT * FROM table");
		}
		finally {
			connectionPool.returnConnection(connection);
		}
	}
}
