package com.tvd12.designparttern.flyweight;

import java.util.HashMap;
import java.util.Map;

public class GraphicsFlyweightExample {
	
	private static final String COLORS[] =
		{
			"Red", "Green", "Blue", "White", "Black"
		};

	public static void main(String[] args) {
		for(int i = 0 ; i < 20 ; i++) {
			Circle circle = (Circle)ShapeFactory
					.getCircle(getRandomColor());
			circle.setX(getRandomX());
			circle.setY(getRandomY());
			circle.setRadius(100);
			circle.draw();
		}
	}
	
	private static String getRandomColor() {
		return COLORS[(int)(Math.random() * COLORS.length)];
	}
	
	private static int getRandomX() {
		return (int)(Math.random() * 100 );
	}
	
	private static int getRandomY() {
		return (int)(Math.random() * 100);
	}
}

interface Shape {
	public void draw();
}

class Circle implements Shape {
	
	public Circle(String color) {
		this.mColor = color;
	}
	
	public void setX(int x) {
		this.mX = x;
	}
	
	public void setY(int y) {
		this.mY = y;
	}
	
	public void setRadius(int radius) {
		this.mRadius = radius;
	}
	
	@Override
	public void draw() {
		System.out.println("Circle: Draw() [Color : " 
				+ mColor 
				+ ", x : " + mX 
				+ ", y :" + mY
				+ ", radius :" + mRadius);
	}
	
	private String mColor;
	private int mX;
	private int mY;
	private int mRadius;
}

class ShapeFactory {
	
	public static final Shape getCircle(String color) {
		Circle circle = (Circle) mCircleMap.get(color);
		
		if(circle == null) {
			circle = new Circle(color);
			mCircleMap.put(color, circle);
			
			System.out.println("Creating circle of color : " + color);
		}
		
		return circle;
		
	}
	
	static {
		mCircleMap = new HashMap<>();
	}
	
	private static Map<String, Shape> mCircleMap;
}
