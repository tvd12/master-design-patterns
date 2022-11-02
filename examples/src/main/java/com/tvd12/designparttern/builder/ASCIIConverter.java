package com.tvd12.designparttern.builder;

public class ASCIIConverter extends TextConverter {
	
	public ASCIIConverter() {
		mAsciiText = new ASCIIText();
	}

	@Override
	public void convertCharacter(char c) {
		char assciiChar = new Character(c).charValue();
		mAsciiText.append(assciiChar);
	}
	
	@Override
	public void convertParagraph() {
		
	}
	
	public ASCIIText getResult() {
		return mAsciiText;
	}
	
	private ASCIIText mAsciiText; //result

}
