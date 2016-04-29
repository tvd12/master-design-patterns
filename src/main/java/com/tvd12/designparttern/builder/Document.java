package com.tvd12.designparttern.builder;

public class Document {

	public Document(char[] data) {
		this.mData = data;
		this.mIndex = 0;
	}
	
	public Document() {
		this(DATA);
	}
	
	public char getNextToken() {
		return mData[mIndex ++];
	}
	
//	public char mToken;
//	public static int sValue;
	
	private int mIndex;
	private char[] mData;
	
	private static char[] DATA = {
			32, 33, 34, 35, '0'
	};
	
}
