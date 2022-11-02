package com.tvd12.designparttern.abstractfactory;

public abstract class Button {

	public void setColor(String color) {
		this.mColor = color;
	}
	
	public String getColor() {
		return mColor;
	}
	
	protected String mColor;
}
