package com.tvd12.designparttern.chainofresponsibility;

public class ErrorLogger extends AbstractLogger {

	public ErrorLogger(int level) {
		super(level);
	}

	@Override
	public void write(String message) {
		System.out.println("Error::Logger: " + message);
	}

}
