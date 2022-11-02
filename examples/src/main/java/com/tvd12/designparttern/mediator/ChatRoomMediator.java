package com.tvd12.designparttern.mediator;

import java.util.HashMap;
import java.util.Map;

public class ChatRoomMediator {
	
	public static void main(String[] args) {
		ChatRoom chatRoom = new ChatRoom();
		User young = new NetworkUser("young", chatRoom);
		User monkey = new NetworkUser("monkey", chatRoom);
		chatRoom.addUser(young);
		chatRoom.addUser(monkey);
		young.sendMessage("Hello", "monkey");
		monkey.sendMessage("World", "young");
	}
	
	// mediator
	public static class ChatRoom {
		
		private final Map<String, User> users = new HashMap<>();
		
		public void addUser(User user) {
			this.users.put(user.getName(), user);
		}
		
		public void sendMessage(String message, String from, String to) {
			User toUser = users.get(to);
			toUser.onMessageReceived(message, from);
		}
	}
	
	// Colleague
	public static interface User {
		
		public String getName();
		
		public void sendMessage(String message, String to);
		
		public void onMessageReceived(String message, String from);
	}
	
	public static class NetworkUser implements User {
		private final String name;
		private final ChatRoom chatRoom;
		
		public NetworkUser(String name, ChatRoom chatRoom) {
			this.name = name;
			this.chatRoom = chatRoom;
		}
		
		public void sendMessage(String message, String to) {
			print(name + " sent to: " + to + " message: " + message);
			chatRoom.sendMessage(message, name, to);
		}
		
		public void onMessageReceived(String message, String from) {
			print(name + " received from: " + from + " message: " + message);
		}
		
		private void print(String str) {
			System.out.println(str);
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
	
}
