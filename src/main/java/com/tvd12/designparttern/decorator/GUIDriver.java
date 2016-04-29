package com.tvd12.designparttern.decorator;

public class GUIDriver {
	public static void main(String[] args) {
		Window window = new SimpleWindow();
		
		window.renderWindow();
		
		window = new ScrollableWindow(window);
		
		window.renderWindow();
	}
}

interface Window {
	public void renderWindow();
}

class SimpleWindow implements Window {
	@Override
	public void renderWindow() {
		System.out.println("SimpleWindow::renderWindow");
	}
}

class DecoratedWindow implements Window {
	
	public DecoratedWindow(Window referenceWindow) {
		this.mPrivateWindowReference = referenceWindow;
	}
	
	@Override
	public void renderWindow() {
		mPrivateWindowReference.renderWindow();
	}
	
	private Window mPrivateWindowReference;
}

class ScrollableWindow extends DecoratedWindow {
	
	public ScrollableWindow(Window windowReference) {
		super(windowReference);
	}
	
	@Override
	public void renderWindow() {
		renderScrollBarObject();
		
		super.renderWindow();
	}
	
	private void renderScrollBarObject() {
		mScrollBarObjectRepresentation = new Object();
	}
	
	@SuppressWarnings("unused")
	private Object mScrollBarObjectRepresentation;
}
