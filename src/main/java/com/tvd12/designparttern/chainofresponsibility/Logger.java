package com.tvd12.designparttern.chainofresponsibility;

import java.util.List;

public class Logger {
	
	private final List<LoggerAppender> appenders;
	
	public Logger(List<LoggerAppender> appenders) {
		this.appenders = appenders;
	}
	
	public void info(String message) {
		for(LoggerAppender appender : appenders) {
			appender.append(message);
		}
	}
}
