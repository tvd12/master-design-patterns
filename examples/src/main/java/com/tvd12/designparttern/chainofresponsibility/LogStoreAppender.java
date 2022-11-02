package com.tvd12.designparttern.chainofresponsibility;

public class LogStoreAppender implements LoggerAppender {
	@Override
	public void append(String message) {
		System.out.println("LogStore: " + message);
	}
}
