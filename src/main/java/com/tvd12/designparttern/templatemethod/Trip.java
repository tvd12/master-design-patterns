package com.tvd12.designparttern.templatemethod;

public abstract class Trip {

	public abstract void doComingTransport();

	public abstract void doDayA();
	
	public abstract void doDayB();
	
	public abstract void doDayC();
	
	public abstract void doReturningTransport();
	
	public void performTrip() {
		doComingTransport();
		doDayA();
		doDayB();
		doDayC();
		doReturningTransport();
	}
	
}
