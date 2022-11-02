package com.tvd12.designparttern.interpreter;

import java.util.HashMap;
import java.util.Map;

// TermialExpression
public class LanguageEnglishToVietnameseInterpreter implements LanguageInterperter {

	private Map<String, String> dictionary = new HashMap<>();
	
	public LanguageEnglishToVietnameseInterpreter() {
		// you can read from database or file
		dictionary.put("hello", "xin chao");
		dictionary.put("good morning", "chao buoi sang");
	}
	
	@Override
	public String interpreter(String input) {
		return dictionary.get(input);
	}
	
}
