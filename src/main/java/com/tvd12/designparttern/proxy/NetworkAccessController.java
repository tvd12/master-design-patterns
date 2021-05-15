package com.tvd12.designparttern.proxy;

import java.util.HashSet;
import java.util.Set;

public class NetworkAccessController {
	
	private static interface NetworkClient {
		String call(String url);
	}
	
	private static final class FreeNetworkClient implements NetworkClient {
		@Override
		public String call(String url) {
			return "It's response from " + url;
		}
	}
	
	private static class NetworkClientProxy implements NetworkClient {
		private final NetworkClient realClient;
		private final Set<String> blackListURLs = new HashSet<>();
		
		public NetworkClientProxy(NetworkClient realClient) {
			this.realClient = realClient;
		}
		
		public NetworkClientProxy addBlackURL(String blackURL) {
			this.blackListURLs.add(blackURL);
			return this;
		}
		
		public String call(String url) {
			if(blackListURLs.contains(url))
				throw new IllegalArgumentException(url + " is in black list");
			return realClient.call(url);
		}
	}
	
	public static void main(String[] args) {
		NetworkClient freeClient = new FreeNetworkClient();
		NetworkClient proxy = new NetworkClientProxy(freeClient)
				.addBlackURL("blackurl.com");
		System.out.println(proxy.call("youngmokeys.org"));
		try {
			proxy.call("blackurl.com");
		}
		catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
	}
}
