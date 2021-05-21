package com.tvd12.designparttern.singleton;

import java.util.HashMap;
import java.util.Map;

public class SingletonExample {

	public static void main(String[] args) {
		Context context = Context.getInstance();
		context.addSingleton(new HelloRepo());
		context.addSingleton(new WorldRepo());
		context.addSingleton(new HelloService());
		
		HelloService helloService = context.getSingleton(HelloService.class);
		System.out.println(helloService.helloWorld());
	}
	
	private static class Context {
		private final Map<Class<?>, Object> singletons;
		private final static Context CONTEXT = new Context();
		
		private Context() {
			this.singletons = new HashMap<>();
		}
		
		public static Context getInstance() {
			return CONTEXT;
		}
		
		public void addSingleton(Object singleton) {
			this.singletons.put(singleton.getClass(), singleton);
		}
		
		@SuppressWarnings("unchecked")
		public <T> T getSingleton(Class<T> type) {
			return (T)singletons.get(type);
		}
	}
	
	private static class HelloService {
		private final HelloRepo helloRepo = 
				Context.getInstance().getSingleton(HelloRepo.class);
		private final WorldRepo worldRepo =
				Context.getInstance().getSingleton(WorldRepo.class);
		
		public String helloWorld() {
			return helloRepo.hello() + " " + worldRepo.world();
		}
	}
	
	private static class HelloRepo {
		
		public String hello() {
			return "Hello";
		}
	}
	
	public static class WorldRepo {
		
		public String world() {
			return "World";
		}
	}
	
}
