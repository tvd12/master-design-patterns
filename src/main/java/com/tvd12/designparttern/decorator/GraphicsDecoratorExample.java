package com.tvd12.designparttern.decorator;

public class GraphicsDecoratorExample {
	public static void main(String[] args) {
		Shape circle = new Circle();

		Shape redCircle = new RedShapeDecorator(new Circle());

		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("Circle with normal border");
		circle.draw();

		System.out.println("\nCircle of red border");
		redCircle.draw();

		System.out.println("\nRectangle of red border");
		redRectangle.draw();
	}
}

interface Shape {
	public void draw();
}

class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Shape: Rectangle");
	}
}

class Circle implements Shape {
	@Override
	public void draw() {
		System.out.println("Shape: Circle");
	}
}

abstract class ShapeDecorator implements Shape {

	public ShapeDecorator(Shape decoratedShape) {
		this.mDecoratedShape = decoratedShape;
	}

	@Override
	public void draw() {
		mDecoratedShape.draw();
	}

	protected Shape mDecoratedShape;
}

class RedShapeDecorator extends ShapeDecorator {

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}

	@Override
	public void draw() {
		mDecoratedShape.draw();
		setRedBorder(mDecoratedShape);
	}

	private void setRedBorder(Shape decoratedShape) {
		System.out.println("Border Color: Red");
	}
}
