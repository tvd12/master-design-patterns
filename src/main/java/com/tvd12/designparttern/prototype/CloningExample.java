package com.tvd12.designparttern.prototype;

public class CloningExample {
	
	public static void test() {
		Object a = null;
		if(a == null) {
			throw new IllegalArgumentException("hahaha");
		}
		
	}

	public static void main(String[] args) {
		CloneEx cloneEx = new CloneEx();
		cloneEx.setPerson(new Person("Ta Van Dung"));
		cloneEx.setString("WFT?");
		
		CloneEx copy = (CloneEx)cloneEx.clone();
		
		cloneEx.getPerson().setName("Haha");
		cloneEx.setString("I Know!");
		
		System.out.println("origin.person = " + cloneEx.getPerson().getName()
				+"\n"
				+ "origin.string = " + cloneEx.getString()
				+ "\n\n"
				+ "copy.person = " + copy.getPerson().getName()
				+"\n"
				+ "copy.string = " + copy.getString());
		
	}
	
}

class Person implements Cloneable {
	
	public Person(String name) {
		this.setName(name);
	}
	
	public void setName(String name) {
		this.mName = name;
	}
	
	public String getName() {
		return this.mName;
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	protected String mName;
}

class CloneEx implements Cloneable {
	
	public void setPerson(Person person) {
		this.mPerson = person;
	}
	
	public Person getPerson() {
		return this.mPerson;
	}
	
	public void setString(String string) {
		this.mString = string;
	}
	
	public String getString() {
		return this.mString;
	}
	
	public CloneEx copy() {
		CloneEx object = new CloneEx();
		object.cloneEx();
		object.setPerson((Person)getPerson().clone());
		
		return object;
	}
	
	public Object cloneEx() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Object clone() {
		try {
			CloneEx object =  (CloneEx)super.clone();
			object.setPerson((Person)getPerson().clone());
			
			return object;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Person mPerson;
	private String mString;
}
