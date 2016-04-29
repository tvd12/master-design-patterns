package com.tvd12.designparttern.builder;

public class RTFReader {

	public RTFReader(TextConverter obj) {
		mBuilder = obj;
	}

	void parseRTF(Document doc) {
		while ((mChar = doc.getNextToken()) != EOF) {
			mBuilder.convertCharacter(mChar);
			mBuilder.convertParagraph();
		}
	}

	private static final char EOF = '0';

	public char mChar;
	public TextConverter mBuilder;
}
