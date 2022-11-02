package com.tvd12.designparttern.bridge;

public class ShapeBridgeExample {
	public static void main(String[] args) {
		Shape redCircle = new Circle(100, 100, 10, new RedCircle());
		Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());
		
		redCircle.draw();
		greenCircle.draw();
	}
}

interface DrawAPI {
	public void drawCircle(int radius, int x, int y);
}

class GreenCircle implements DrawAPI {

	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("Drawing Circle[ color: green, radius: " 
				+ radius 
				+ ", x: " 
				+ x + ", " 
				+ y + "]");
	}

}

class RedCircle implements DrawAPI {
	@Override
	public void drawCircle(int radius, int x, int y) {
		System.out.println("Drawing Circle[ color: red, radius: " 
				+ radius 
				+ ", x: " 
				+ x + ", " 
				+ y + "]");
	}
}

abstract class Shape {

	protected Shape(DrawAPI drawAPI) {
		this.mDrawAPI = drawAPI;
	}

	public abstract void draw();

	protected DrawAPI mDrawAPI;
}

class Circle extends Shape {
	public Circle(int x, int y, int radius, DrawAPI drawAPI) {
		super(drawAPI);
		this.mX = x;
		this.mY = y;
		this.mRadius = radius;
	}

	@Override
	public void draw() {
		mDrawAPI.drawCircle(mRadius, mX, mY);
	}

	private int mX, mY, mRadius;
}