package com.tvd.designparttern.abstractfactory;

public abstract class EditBox {

	public void setStyle(String style) {
		this.mStyle = style;
	}
	
	public String getStyle() {
		return this.mStyle;
	}
	
	protected String mStyle;
}
