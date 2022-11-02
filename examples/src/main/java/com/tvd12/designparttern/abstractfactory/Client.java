package com.tvd12.designparttern.abstractfactory;

public class Client {

	public static void main(String[] args) {
		LookAndFeel windowLAF = new WindowsLookAndFeel();
		LookAndFeel linuxLAF = new LinuxLookAndFeel();
		
		Button windowButton = windowLAF.createButton();
		Button linuxButton = linuxLAF.createButton();
		
		System.out.println(windowButton.getColor() 
				+ ", "
				+ linuxButton.getColor());
	}
	
}
