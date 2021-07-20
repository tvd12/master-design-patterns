package com.tvd12.designparttern.interpreter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// Context
public class LanguageTranslateRequest {

	private final LanguageType inputLanguageType;
	private final LanguageType outputlLanguageType;
	private final String text;
	
}
