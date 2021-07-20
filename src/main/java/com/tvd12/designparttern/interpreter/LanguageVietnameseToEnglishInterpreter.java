package com.tvd12.designparttern.interpreter;

import java.util.HashMap;
import java.util.Map;

// TermialExpression
public class LanguageVietnameseToEnglishInterpreter implements LanguageInterperter {

	private Map<String, String> dictionary = new HashMap<>();
	
	public LanguageVietnameseToEnglishInterpreter() {
		// you can read from database or file
		dictionary.put("xin chao", "hello");
		dictionary.put("chao buoi sang", "good morning");
	}
	
	@Override
	public String interpreter(String input) {
		return dictionary.get(input);
	}
	
}
