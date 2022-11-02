package com.tvd12.designparttern.memento;

import java.util.Scanner;
import java.util.Stack;

public class TextEditorNoMemento {
	
	private final Stack<String> stateStack = new Stack<>();
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
				stateStack.add(text.toString());
				if(text.length() > 0) {
					text.append(" ");
				}
				text.append(line);
				System.out.println("saved: " + text);
			}
		}
	}
	
	public void restore() {
		text.delete(0, text.length());
		String beforeState = stateStack.isEmpty() ? "" : stateStack.pop();
		text.append(beforeState);
		System.out.println("you just restore, current text is: " + text);
	}
	
	public void close() {
		System.out.println("finished, your text is: " + text);
	}
	
	public static void main(String[] args) throws Exception {
		TextEditorNoMemento textEditor = new TextEditorNoMemento();
		textEditor.start();
	}
}
