package com.tvd12.designparttern.command;

import java.util.ArrayList;
import java.util.List;

public class CalculatorAppExample {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		
		Invoker invoker = Invoker.create();
		invoker.calculate(new CalculatorCommand(calculator, '+', 10));
		invoker.calculate(new CalculatorCommand(calculator, '*', 5));
		invoker.calculate(new CalculatorCommand(calculator, '-', 100));
		invoker.calculate(new CalculatorCommand(calculator, '/', 2));
		invoker.calculate(new CalculatorCommand(calculator, '*', 5));
		invoker.calculate(new CalculatorCommand(calculator, '+', 100));
		
		
		System.out.println("\n=================\n");
		
		invoker.undo(3);
		
		System.out.println("\n=================\n");
		
		invoker.redo(2);
	}
	
}

class Calculator {
	
	public Calculator() {
		this.mCurrentValue = 0;
	}
	
	public void doOperation(char operator, int operand) {
		
		int beforeValue = mCurrentValue;
		
		switch (operator) {
		case '+':
			this.mCurrentValue += operand;
			break;
		case '-':
			this.mCurrentValue -= operand;
			break;
		case '*':
			this.mCurrentValue *= operand;
			break;
		case '/':
			this.mCurrentValue /= operand;
			break;

		default:
			break;
		}
		
		System.out.println(mCurrentValue 
				+ " := " 
				+ beforeValue
				+ " "
				+ operator
				+ " "
				+ operand);
	}
	
	public void println() {
		System.out.println("value = " + mCurrentValue);
	}
	
	protected int mCurrentValue;
}

interface Command {
	public void execute();
	
	public void unexecute();
}

class CalculatorCommand implements Command {
	
	public CalculatorCommand(Calculator calculator,
			char operator, int operand) {
		this.mCalculator = calculator;
		this.mOperator = operator;
		this.mOperand = operand;
	}
	
	public void execute() {
		this.mCalculator.doOperation(mOperator, mOperand);
	}
	
	public void unexecute() {
		this.mCalculator.doOperation(getUndo(mOperator), mOperand);
	}
	
	public char getUndo(char operator) {
		switch (operator) {
		case '+':
			return '-';
		case '-':
			return '+';
		case '*':
			return '/';
		case '/':
			return '*';
		default:
			return operator;
		}
	}
	
	private Calculator mCalculator;
	private char mOperator;
	private int mOperand;
}

class Invoker {
	
	public Invoker() {
		this.mCommands = new ArrayList<>();
		this.mCurrentCommand = 0;
	}
	
	public static Invoker create() {
		return new Invoker();
	}
	
	public void calculate(Command command) {
		command.execute();
		mCommands.add(command);
		mCurrentCommand ++;
	}
	
	public void undo(int levels) {

		int beforeCommand = mCurrentCommand;
		
		if(mCurrentCommand > levels) {
			mCurrentCommand -= levels;
		}
		else {
			mCurrentCommand = 0;
		}
		
		for(int i = beforeCommand ; i > mCurrentCommand ; i--) {
			mCommands.get(i - 1).unexecute();
		}
	}
	
	public void redo(int levels) {
		int beforeCommand = mCurrentCommand;
		
		if(mCurrentCommand + levels <= mCommands.size()) {
			mCurrentCommand += levels;
		}
		else {
			mCurrentCommand = mCommands.size();
		}
		
		for(int i = beforeCommand ; i < mCurrentCommand ; i++) {
			mCommands.get(i).execute();
		}
	}
	
	private List<Command> mCommands;
	private int mCurrentCommand;
}


