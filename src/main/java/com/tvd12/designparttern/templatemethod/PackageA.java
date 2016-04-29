package com.tvd12.designparttern.templatemethod;

public class PackageA extends Trip {

	@Override
	public void doComingTransport() {
		System.out.println("The turists are comming by air ...");
	}

	@Override
	public void doDayA() {
		System.out.println("The turists are visiting the aquarium ...");
	}

	@Override
	public void doDayB() {
		System.out.println("The turists are going to the beach ...");
	}

	@Override
	public void doDayC() {
		System.out.println("The turists are going to mountains ...");
	}

	@Override
	public void doReturningTransport() {
		System.out.println("The turists are going home by air ...");
	}

}
