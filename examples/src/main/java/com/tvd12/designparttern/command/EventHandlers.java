package com.tvd12.designparttern.command;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class EventHandlers {
	private final Map<EventType, EventHandler> eventHandlers;
	
	public EventHandlers() {
		this.eventHandlers = new HashMap<>();
		this.eventHandlers.put(EventType.USER_LOGIN, new UserLoginHandler());
		this.eventHandlers.put(EventType.USER_UPDATE, new UserUpdateHandler());
		this.eventHandlers.put(EventType.USER_LOGOUT, new UserLogoutHandler());
	}
	
	public void handle(Event event) {
		EventHandler handler = eventHandlers.get(event.getEventType());
		handler.handle(event);
	}
	
	public static void main(String[] args) {
		EventHandlers handlers = new EventHandlers();
		handlers.handle(new UserLoginEvent());
		handlers.handle(new UserUpdateEvent());
		handlers.handle(new UserLogoutEvent());
	}
}

// command
interface EventHandler<E extends Event> {
	void handle(E event);
}

// concrete command
class UserLoginHandler implements EventHandler<UserLoginEvent> {
	@Override
	public void handle(UserLoginEvent event) {
		System.out.println("user login: " + event);
	}
}

//concrete command
class UserUpdateHandler implements EventHandler<UserUpdateEvent> {
	@Override
	public void handle(UserUpdateEvent event) {
		System.out.println("user update: " + event);
	}
}

//concrete command
class UserLogoutHandler implements EventHandler<UserLogoutEvent> {
	@Override
	public void handle(UserLogoutEvent event) {
		System.out.println("user logout: " + event);
	}
}

enum EventType {
	USER_LOGIN,
	USER_UPDATE,
	USER_LOGOUT
}

interface Event {
	EventType getEventType();
}

class UserLoginEvent implements Event {
	@Override
	public EventType getEventType() {
		return EventType.USER_LOGIN;
	}
}

class UserUpdateEvent implements Event {
	@Override
	public EventType getEventType() {
		return EventType.USER_UPDATE;
	}
}

class UserLogoutEvent implements Event {
	@Override
	public EventType getEventType() {
		return EventType.USER_LOGOUT;
	}
}