package com.tvd12.designparttern.chainofresponsibility;

public class FileAppender implements LoggerAppender {
	@Override
	public void append(String message) {
		System.out.println("File: " + message);
	}
}
