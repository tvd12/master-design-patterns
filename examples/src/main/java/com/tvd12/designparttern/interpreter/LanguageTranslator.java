package com.tvd12.designparttern.interpreter;

import java.util.HashMap;
import java.util.Map;

// Client
public class LanguageTranslator {
	
	private final Map<LanguageType, Map<LanguageType, LanguageInterperter>> interperters;
	
	public LanguageTranslator() {
		Map<LanguageType, LanguageInterperter> englishInterpreters = new HashMap<>();
		englishInterpreters.put(LanguageType.VI, new LanguageEnglishToVietnameseInterpreter());
		
		Map<LanguageType, LanguageInterperter> vietnameseInterpreters = new HashMap<>();
		vietnameseInterpreters.put(LanguageType.EN, new LanguageVietnameseToEnglishInterpreter());
		
		interperters = new HashMap<>();
		interperters.put(LanguageType.EN, englishInterpreters);
		interperters.put(LanguageType.VI, vietnameseInterpreters);
	}
	
	public String translate(LanguageTranslateRequest request) {
		return interperters
				.get(request.getInputLanguageType())
				.get(request.getOutputlLanguageType())
				.interpreter(request.getText());
	}
	
	public static void main(String[] args) {
		LanguageTranslator translator = new LanguageTranslator();
		String englishText = "hello";
		String translatedVietnameseText = translator.translate(
			new LanguageTranslateRequest(
					LanguageType.EN, 
					LanguageType.VI, englishText
			)
		);
		
		System.out.println("English text: " + englishText);
		System.out.println("Vietnamese text: " + translatedVietnameseText);
		
		
		String vietnamesesText = "chao buoi sang";
		String translatedEnglishText = translator.translate(
				new LanguageTranslateRequest(
						LanguageType.VI, 
						LanguageType.EN, vietnamesesText
				)
			);
		
		System.out.println("Vietnamese text: " + vietnamesesText);
		System.out.println("English text: " + translatedEnglishText);
	}

}
