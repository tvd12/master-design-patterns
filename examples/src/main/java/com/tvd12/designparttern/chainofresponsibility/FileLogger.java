package com.tvd12.designparttern.chainofresponsibility;

public class FileLogger extends AbstractLogger {

	public FileLogger(int level) {
		super(level);
	}

	@Override
	public void write(String message) {
		System.out.println("File::Logger: " + message);
	}
	
}
