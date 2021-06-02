package com.tvd12.designparttern.chainofresponsibility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LoggerFactory {

	private final static Map<Object, Logger> loggers = new HashMap<>();
	private final static List<LoggerAppender> appenders = new ArrayList<>();
	static {
		appenders.add(new ConsoleAppender());
		appenders.add(new FileAppender());
		appenders.add(new SlackAppender());
		appenders.add(new LogStoreAppender());
	}
	
	public static Logger getLogger(Object name) {
		return loggers.computeIfAbsent(name, k -> new Logger(appenders));
	}
}
