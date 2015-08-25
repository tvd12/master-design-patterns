package com.tvd.designparttern.factory;

public class FactoryDemo {

	public static void main(String[] args) {
		IconFactory factory = IconFactory.getInstance();
		ShortcutView shortcut = (ShortcutView)factory.getIconView("shortcut");
		
		System.out.println(shortcut.getInfo());
	}
	
}
