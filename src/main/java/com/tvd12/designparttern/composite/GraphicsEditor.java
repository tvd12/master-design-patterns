package com.tvd12.designparttern.composite;

import java.util.ArrayList;
import java.util.List;

public class GraphicsEditor {
	public static void main(String[] args) {
		List<Shape> allShapesInSoftware = new ArrayList<>();
		
		//create a line shape
		Shape lineShape = new Line(0, 0, 1, 1);
		
		//add it to the shapes
		allShapesInSoftware.add(lineShape);
		
		//create a rectangle shape
		Shape rectangleShape = new Rectangle();
		
		//add it to shapes
		allShapesInSoftware.add(rectangleShape);
		
		//create a complex shape
		ComplexShape complexShape = new ComplexShape();
		complexShape.addToShape(rectangleShape);
		complexShape.addToShape(lineShape);
		
		allShapesInSoftware.add(complexShape);
		
		ComplexShape veryComplexShape = new ComplexShape();
		veryComplexShape.addToShape(complexShape);
		veryComplexShape.addToShape(lineShape);
		
		allShapesInSoftware.add(veryComplexShape);
		
		renderGraphics(allShapesInSoftware);
		
	}
	
	private static void renderGraphics(List<Shape> shapesToRender){

		// note that despite the fact there are 
		// simple and complex shapes 
		// this method deals with them uniformly 
		// and using the Shape interface
		
		for(Shape s : shapesToRender){
			s.renderShapeToScreen();
		}
		
	}
}

interface Shape {

	public void renderShapeToScreen();

	public Shape[] explodeShape();
}

class Line implements Shape {

	public Line(int point1X, int point1Y, int point2X, int point2Y) {

	}

	@Override
	public Shape[] explodeShape() {
		Shape[] shapeParts = new Shape[] { this };

		return shapeParts;
	}

	@Override
	public void renderShapeToScreen() {
		System.out.println("Line::renderShapeToScreen");
	}

}

class Rectangle implements Shape {

	@Override
	public Shape[] explodeShape() {

		return RECTANGLE_EDGES;

	}

	/**
	 * this method is implemented directly in basic shapes in complex shapes
	 * this method is implemented using delegation
	 */
	public void renderShapeToScreen() {
		System.out.println("\nRectangle::renderShapeToScreen");
		for (Shape s : RECTANGLE_EDGES) {

			// delegate to child objects
			s.renderShapeToScreen();

		}

	}
	
	// List of shapes forming the rectangle
		// rectangle is centered around origin
		private static final Shape[] RECTANGLE_EDGES = { 
				new Line(-1, -1, 1, -1), 
				new Line(-1, 1, 1, 1), 
				new Line(-1, -1, -1, 1),
				new Line(1, -1, 1, 1) 
				};
}

class ComplexShape implements Shape {
	
	public void addToShape(Shape shpeToAddToCurrentShape) {
		mShapeList.add(shpeToAddToCurrentShape);
	}
	
	@Override
	public Shape[] explodeShape() {
		return mShapeList.toArray(new Shape[mShapeList.size()]);
	}
	
	@Override
	public void renderShapeToScreen() {
		for(Shape s : mShapeList) {
			s.renderShapeToScreen();
		}
	}
	
	private List<Shape> mShapeList = new ArrayList<>();
}