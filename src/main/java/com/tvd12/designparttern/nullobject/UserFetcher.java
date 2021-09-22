package com.tvd12.designparttern.nullobject;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

public class UserFetcher {

	private final Map<Long, User> userById;
	private static final User DEFAULT_USER = new User(0, "");
	
	public UserFetcher() {
		this.userById = new HashMap<>();
		this.userById.put(1L, new User(1L, "Foo"));
		this.userById.put(2L, new User(2L, "Bar"));
	}
	
	public Optional<User> getUserById(long userId) {
		return Optional.ofNullable(userById.get(userId));
	}
	
	@Getter
	@ToString
	@AllArgsConstructor
	public static class User {
		private long id;
		private String name;
	}
	
	public static void main(String[] args) {
		UserFetcher userFetcher = new UserFetcher();
		User user1st = userFetcher
				.getUserById(1L)
				.orElseGet(() -> DEFAULT_USER);
		System.out.println(user1st);
		
		User user3rd = userFetcher
				.getUserById(3L)
				.get();
		System.out.println(user3rd);
	}
}
