package com.tvd12.designparttern.templatemethod;

public class PackageB extends Trip {

	@Override
	public void doComingTransport() {
		System.out.println("The turists are comming by train ...");
	}

	@Override
	public void doDayA() {
		System.out.println("The turists are visiting the mountain ...");
	}

	@Override
	public void doDayB() {
		System.out.println("The turists are going to the beach ...");
	}

	@Override
	public void doDayC() {
		System.out.println("The turists are going to zoo ...");
	}

	@Override
	public void doReturningTransport() {
		System.out.println("The turists are going home by train ...");
	}

}
