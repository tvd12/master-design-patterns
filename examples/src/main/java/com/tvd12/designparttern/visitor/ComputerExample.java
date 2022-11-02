package com.tvd12.designparttern.visitor;

public class ComputerExample {

	public static void main(String[] args) {
		ComputerPartVisitor visitor = new ComputerPartDisplayVisitor();
		ComputerPart part = new Computer();
		part.accept(visitor);
	}

}

interface ComputerPartVisitor {

	public void visit(Computer computer);

	public void visit(Mouse mouse);

	public void visit(Keyboard keyboard);

	public void visit(Monitor monitor);

}

interface ComputerPart {
	public void accept(ComputerPartVisitor computerPartVisitor);
}

class ComputerPartDisplayVisitor implements ComputerPartVisitor {

	@Override
	public void visit(Computer computer) {
		System.out.println("Displaying Computer.");
	}

	@Override
	public void visit(Mouse mouse) {
		System.out.println("Displaying Mouse.");
	}

	@Override
	public void visit(Keyboard keyboard) {
		System.out.println("Displaying Keyboard.");
	}

	@Override
	public void visit(Monitor monitor) {
		System.out.println("Displaying Monitor.");
	}
}

class Computer implements ComputerPart {

	public Computer() {
		mParts = new ComputerPart[] {
			new Mouse(), new Keyboard(),
			new Monitor()
		};
	}

	public void accept(ComputerPartVisitor computerPartVisitor) {
		for (ComputerPart part : mParts) {
			part.accept(computerPartVisitor);
		}
		computerPartVisitor.visit(this);
	}

	private ComputerPart mParts[];
}

class Keyboard implements ComputerPart {
	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}
}

class Mouse implements ComputerPart {
	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}
}

class Monitor implements ComputerPart {
	public void accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.visit(this);
	}
}