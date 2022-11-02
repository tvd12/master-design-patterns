package com.tvd12.designparttern.abstractfactory;

public class LinuxLookAndFeel extends LookAndFeel {

	@Override
	public EditBox createEditBox() {
		return new LinuxEditBox();
	}

	@Override
	public Button createButton() {
		return new LinuxButton();
	}

}
