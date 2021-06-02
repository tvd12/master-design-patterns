package com.tvd12.designparttern.chainofresponsibility;

public class SlackAppender implements LoggerAppender {
	@Override
	public void append(String message) {
		System.out.println("Slack: " + message);
	}
}
