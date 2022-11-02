package com.tvd12.designparttern.chainofresponsibility;

public class LoggerDemo {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(LoggerDemo.class);
		logger.info("Hello World");
	}
	
}
