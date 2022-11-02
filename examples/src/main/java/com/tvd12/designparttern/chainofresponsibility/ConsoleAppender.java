package com.tvd12.designparttern.chainofresponsibility;

public class ConsoleAppender implements LoggerAppender {
	@Override
	public void append(String message) {
		System.out.println("Console: " + message);
	}
}
