package com.tvd12.designparttern.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ButtonObserverExample {

	public static void main(String[] args) {
		Button button = new Button("Download");
		button.attach(new ButtonObserver());
		button.focus();
		button.blur();
	}
	
}

abstract class View {

	/**
	 * Get name of view
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * Get type of view
	 * @return
	 */
	public abstract String getType();
	
	private List<ViewObserver> observers;
	
	public void attach(ViewObserver observer) {
		if(observers == null) {
			observers = new CopyOnWriteArrayList<>();
		}
		observers.add(observer);
	}
	
	public void detach(ViewObserver observer) {
		if(observers == null) {
			return;
		}
		observers.remove(observer);
	}
	
	public void notifyFocusToObservers() {
		if(observers == null) return;
		
		for (ViewObserver observer : observers) {
			if(observer == null) continue;
			observer.onFocus(this);
		}
	}
	
	public void notifyBlurToObservers() {
		if(observers == null) return;
		
		for (ViewObserver observer : observers) {
			if(observer == null) continue;
			observer.onBlur(this);
		}
	}
	
}

abstract class ViewObserver {

	/**
	 * 
	 * @param view
	 */
	public abstract void onFocus(View view);
	
	/**
	 * 
	 * @param view
	 */
	public abstract void onBlur(View view);
	
}

class Button extends View {
	
	private String name;
	
	public Button(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}

	public void focus() {
		notifyFocusToObservers();
	}
	
	public void blur() {
		notifyBlurToObservers();
	}
}

class ButtonObserver extends ViewObserver {

	@Override
	public void onFocus(View view) {
		System.out.println("User focus into "
				+ view.getType() 
				+ "(" + view.getName() + ")");
	}

	@Override
	public void onBlur(View view) {
		System.out.println("User focus into "
				+ view.getType() 
				+ "(" + view.getName() + ")");
	}

}