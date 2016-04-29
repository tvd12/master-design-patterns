package com.tvd12.designparttern.mediator;

import java.util.Date;

public class MediatorPatternDemo {
	public static void main(String[] args) {
		User robert = new User("Robert");
		User john = new User("John");

		robert.sendMessage("Hi! John!");
		john.sendMessage("Hello! Robert!");
	}
}

class ChatRoom {
	public static void showMessage(User user, String message) {
		System.out.println(new Date().toString() + " [" + user.getName() + "] : " + message);
	}
}

class User {

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public User(String name) {
		this.mName = name;
	}

	public void sendMessage(String message) {
		ChatRoom.showMessage(this, message);
	}

	private String mName;
}