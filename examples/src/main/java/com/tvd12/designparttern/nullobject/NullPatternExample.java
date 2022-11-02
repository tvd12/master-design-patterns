package com.tvd12.designparttern.nullobject;

public class NullPatternExample {
	public static void main(String[] args) {
		AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
		AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
		AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
		AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");

		System.out.println("Customers");
		System.out.println(customer1.getName());
		System.out.println(customer2.getName());
		System.out.println(customer3.getName());
		System.out.println(customer4.getName());
	}
}

abstract class AbstractCustomer {

	public abstract boolean isNil();

	public abstract String getName();

	protected String mName;

}

class RealCustomer extends AbstractCustomer {

	public RealCustomer(String name) {
		this.mName = name;
	}

	@Override
	public String getName() {
		return mName;
	}

	@Override
	public boolean isNil() {
		return false;
	}

}

class NullCustomer extends AbstractCustomer {

	@Override
	public String getName() {
		return "Not Available in Customer Database";
	}

	@Override
	public boolean isNil() {
		return true;
	}
}

class CustomerFactory {

	public static final String[] NAMES = { "Rob", "Joe", "Julie" };

	public static AbstractCustomer getCustomer(String name) {
		for (int i = 0; i < NAMES.length; i++) {
			if (NAMES[i].equalsIgnoreCase(name)) {
				return new RealCustomer(name);
			}
		}

		return new NullCustomer();
	}
}