package com.tvd12.designparttern.abstractfactory;

public class WindowsLookAndFeel extends LookAndFeel {

	@Override
	public EditBox createEditBox() {
		return new WindowEditBox();
	}

	@Override
	public Button createButton() {
		return new WindownButton();
	}

}
