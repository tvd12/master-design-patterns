package com.tvd12.designparttern.memento;

import java.util.ArrayList;
import java.util.List;

public class MementoExample {
	public static void main(String[] args) {
		Originator originator = new Originator();
		CareTaker careTaker = new CareTaker();
		
		originator.setState("State #1");
		originator.setState("State #2");
		careTaker.add(originator.saveStateToMemento());
		
		originator.setState("State #3");
		careTaker.add(originator.saveStateToMemento());
		
		originator.setState("State #4");
		System.out.println("Current State: " + originator.getState());		
	      
		originator.getStateFromMemento(careTaker.get(0));
		System.out.println("First saved State: " + originator.getState());
		originator.getStateFromMemento(careTaker.get(1));
		System.out.println("Second saved State: " + originator.getState());
	}
}

class Memento {
	
	public Memento(String state) {
		this.mState = state;
	}
	
	public String getState() {
		return this.mState;
	}
	
	private String mState;
}

class Originator {
	
	public void setState(String state) {
		this.mState = state;
	}
	
	public String getState() {
		return this.mState;
	}
	
	public Memento saveStateToMemento() {
		return new Memento(mState);
	}
	
	public void getStateFromMemento(Memento memento) {
		this.mState = memento.getState();
	}
	
	private String mState;
}

class CareTaker {
	
	public void add(Memento state) {
		mMementos.add(state);
	}
	
	public Memento get(int index) {
		if(mMementos.size() > index) {
			return mMementos.get(index);
		}
		
		return null;
	}
	
	private List<Memento> mMementos = new ArrayList<>();
}