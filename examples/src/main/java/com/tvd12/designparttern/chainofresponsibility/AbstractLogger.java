package com.tvd12.designparttern.chainofresponsibility;

public abstract class AbstractLogger {
	
	public AbstractLogger(int level) {
		this.mLevel = level;
	}
	
	public abstract void write(String message);
	
	public void setNextLogger(AbstractLogger nextLogger) {
		this.mNextLogger = nextLogger;
	}
	
	public void logMessage(int level, String message) {
		if(this.mLevel <= level) {
			write(message);
		}
		
		if(mNextLogger != null) {
			mNextLogger.logMessage(level, message);
		}
	}
	
	protected AbstractLogger mNextLogger;
	protected int mLevel;
	
	public static final int INFO	= 1 << 0;
	public static final int DEBUG	= 1 << 1;
	public static final int ERROR	= 1 << 2;
}
