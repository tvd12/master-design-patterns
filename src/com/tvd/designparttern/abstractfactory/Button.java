package com.tvd.designparttern.abstractfactory;

public abstract class Button {

	public void setColor(String color) {
		this.mColor = color;
	}
	
	public String getColor() {
		return mColor;
	}
	
	protected String mColor;
}
