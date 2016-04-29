package com.tvd12.designparttern.builder;

public class ASCIIText {

	public ASCIIText() {
		mBuilder = new StringBuilder();
	}
	
	public void append(char c) {
		mBuilder.append(c);
	}
	
	@Override
	public String toString() {
		return mBuilder.toString();
	}
	
	private StringBuilder mBuilder;
}
