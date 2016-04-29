package com.tvd12.designparttern.chainofresponsibility;

public class ConsoleLogger extends AbstractLogger {

	public ConsoleLogger(int level) {
		super(level);
	}

	@Override
	public void write(String message) {
		System.out.println("Standard Console::Logger: " + message);
	}

}
