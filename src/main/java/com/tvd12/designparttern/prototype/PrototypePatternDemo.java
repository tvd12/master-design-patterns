package com.tvd12.designparttern.prototype;

import java.util.Hashtable;

public class PrototypePatternDemo {
	
	public static void main(String[] args) {
		ShapeCache.loadCache();

	    Shape clonedShape = (Shape) ShapeCache.getShape(1);
	    System.out.println("Shape : " + clonedShape.getType());		

	    Shape clonedShape2 = (Shape) ShapeCache.getShape(2);
	    System.out.println("Shape : " + clonedShape2.getType());
	}
	
}

abstract class Shape implements Cloneable {
	
	public Shape() {
		this(0, "shape");
	}
	
	public Shape(int id) {
		setId(id);
	}
	
	public Shape(String type) {
		setType(type);
	}
	
	public Shape(int id, String type) {
		setId(id);
		setType(type);
	}
	
	public abstract void draw();
	
	public void setId(int id) {
		this.mId = id;
	}
	
	public void setType(String type) {
		this.mType = type;
	}
	
	public int getId() {
		return this.mId;
	}
	
	public String getType() {
		return this.mType;
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{")
			.append("\tid: ").append("\"" + getId() + "\"")
			.append(",\n")
			.append("\ttype: ").append("\"" + getType() + "\"")
			.append("\n")
			.append("}");
		
		return builder.toString();
	}
	
	private int mId;
	private String mType;
	
}

class Square extends Shape {

	public Square(int id) {
		super(id, "Square");
	}
	
	@Override
	public void draw() {
		System.out.println("Call by: Square");
	}

}

class ShapeCache {

	public static Shape getShape(int shapeId) {
		Shape cachedShape = sShapes.get(shapeId);
		
		if(cachedShape != null) {
			return (Shape)cachedShape.clone();
		}
		
		return null;
	}
	
	public static void loadCache() {
		Shape rectangle = new Rectangle(1);
		Shape square = new Square(2);
		
		sShapes.put(rectangle.getId(), rectangle);
		sShapes.put(square.getId(), square);
	}
	
	private static Hashtable<Integer, Shape> sShapes
		= new Hashtable<>();
}

class Rectangle extends Shape {

	public Rectangle(int id) {
		super(id, "Rectangle");
	}
	
	@Override
	public void draw() {
		System.out.println("Call by: Rectangle");
	}

}

