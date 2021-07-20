package com.tvd12.designparttern.interpreter;

import lombok.AllArgsConstructor;
import lombok.Getter;

//Context
@Getter
@AllArgsConstructor
public class LanguageTranslateRequest {

	private final LanguageType inputLanguageType;
	private final LanguageType outputlLanguageType;
	private final String text;
}
