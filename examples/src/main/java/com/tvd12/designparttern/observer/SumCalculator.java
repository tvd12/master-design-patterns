package com.tvd12.designparttern.observer;

import java.util.function.Consumer;

public class SumCalculator {

	public static int sumWithReturn(int a, int b) {
		return a + b;
	}
	
	public static void sumWithCallback(int a, int b, Consumer<Integer> callback) {
		int sum = a + b;
		callback.accept(sum);
	}
	
	public static void sumAsync(int a, int b, Consumer<Integer> callback) {
		Thread newThread = new Thread(() -> callback.accept(a + b));
		newThread.start();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("sumWithReturn: " + sumWithReturn(1, 2));
		sumWithCallback(1, 2, it -> {
			System.out.println("sumWithReturn: " + it);
		});
		sumAsync(1, 2, it -> {
			System.out.println("sumAsync: " + it);
		});
		Thread.sleep(1000);
	}
	
}
