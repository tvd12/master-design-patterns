package com.tvd12.designparttern.chainofresponsibility;

public class ChainPatternDemo {
	
	public static AbstractLogger loggerChain() {
		AbstractLogger errorLogger 	= new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger 	= new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
		
		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);
		
		return errorLogger;
	}

	public static void main(String[] args) {
		
		AbstractLogger loggerChain = loggerChain();
		
		loggerChain.logMessage(AbstractLogger.INFO, "infor message");
		
		System.out.println("=================");
		
		loggerChain.logMessage(AbstractLogger.DEBUG, "debug message");
		
		System.out.println("=================");
		
		loggerChain.logMessage(AbstractLogger.ERROR, "error message");
		
	}
	
}
