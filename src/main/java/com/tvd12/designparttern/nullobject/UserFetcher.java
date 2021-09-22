package com.tvd12.designparttern.nullobject;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserFetcher {

	private final Map<Long, User> userById;
	
	public UserFetcher() {
		this.userById = new HashMap<>();
		this.userById.put(1L, new User(1L, "Foo"));
		this.userById.put(2L, new User(2L, "Bar"));
	}
	
	public Optional<User> getUserById(long userId) {
		return Optional.ofNullable(userById.get(userId));
	}
	
	public static class ToJsonOperation implements Function<User, String> {
		@Override
		public String apply(User t) {
			return t.toJson();
		}
	}
	
	public static class ToEmptyStringOperation implements Supplier<String> {
		@Override
		public String get() {
			return "";
		}
	}
	
	public static void main(String[] args) {
		UserFetcher userFetcher = new UserFetcher();
		String user1stJson = userFetcher
				.getUserById(1L)
				.map(new ToJsonOperation())
				.orElseGet(new ToEmptyStringOperation());
		System.out.println(user1stJson);
		
		String user3rdJson = userFetcher
				.getUserById(3L)
				.map(new ToJsonOperation())
				.get();
		System.out.println(user3rdJson);
	}
	
	@Getter
	@AllArgsConstructor
	public static class User {
		private long id;
		private String name;
		
		public String toJson() {
			return "{\"id: \"" + id + ", \"name\": \"" + name + "\"}";
		}
		
		@Override
		public String toString() {
			return toJson();
		}
	}
}
