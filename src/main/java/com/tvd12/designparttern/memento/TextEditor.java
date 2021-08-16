package com.tvd12.designparttern.memento;

import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
	
	private final Memento memento = new Memento();
	private final StringBuilder text = new StringBuilder();
	
	@SuppressWarnings("resource")
	public void start() {
		System.out.println("please enter you text:");
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String line;
			try {
				line = scanner.nextLine();
			}
			catch (Exception e) {
				continue;
			}
			if(line.equals("b")) {
				restore();
			}
			else if(line.equals("c")) {
				close();
				break;
			}
			else {
				save(line);
			}
		}
	}
	
	public void save(String line) {
		memento.addNewState(text.toString());
		if(text.length() > 0) {
			text.append(" ");
		}
		text.append(line);
		System.out.println("saved: " + text);
	}
	
	private void restore() {
		text.delete(0, text.length());
		text.append(memento.takeLastState());
		System.out.println("you just restore, current text is: " + text);
	}
	
	public void close() {
		System.out.println("finished, your text is: " + text);
	}

	public static class Memento {
		private final Stack<String> stateStack = new Stack<>();
		
		public void addNewState(String state) {
			stateStack.add(state);
		}
		
		public String takeLastState() {
			return stateStack.isEmpty() ? "" : stateStack.pop();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		TextEditor textEditor = new TextEditor();
		textEditor.start();
	}
}
