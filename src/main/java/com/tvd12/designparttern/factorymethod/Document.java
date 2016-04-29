package com.tvd12.designparttern.factorymethod;

public interface Document {

	public void open();
	
	public void save(String fileName);
	
	public void close();
	
}
