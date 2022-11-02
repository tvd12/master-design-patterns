package com.tvd12.designparttern.memento;

public class CalculatorExample {

	public static void main(String[] args) {
		Calculator calculator = new CalculatorImp();
		
		calculator.setFirstNumber(10);
		calculator.setSecondNumber(100);
		
		System.out.println(calculator.getCalculationResult());
		
		PreviousCalculationToCareTaker memento = 
				calculator.backupLastCalculation();
		
		calculator.setFirstNumber(17);
		
		calculator.setSecondNumber(-290);
		
		System.out.println(calculator.getCalculationResult());
		
		calculator.restorePreviousCalculation(memento);
		
		System.out.println(calculator.getCalculationResult());
	}
	
}

interface PreviousCalculationToCareTaker {
	//no operation
}

interface PreviousCalculationToOriginator {
	public int getFirstNumber();
	
	public int getSecondNumber();
}

interface Calculator {
	public PreviousCalculationToCareTaker backupLastCalculation();
	
	public void restorePreviousCalculation(PreviousCalculationToCareTaker memento);
	
	public int getCalculationResult();
	
	public void setFirstNumber(int firstNumber);
	
	public void setSecondNumber(int secondNumber);
}

class PreviousCalculationImp implements PreviousCalculationToCareTaker,
		PreviousCalculationToOriginator {
	
	public PreviousCalculationImp(int firstNumber, int secondNumber) {
		this.mFistNumber = firstNumber;
		this.mSecondNumber = secondNumber;
	}
	
	public static final PreviousCalculationImp create(
			int firstNumber, int secondNumber) {
		PreviousCalculationImp pRet = 
				new PreviousCalculationImp(firstNumber, secondNumber);
		
		return pRet;
	}
	
	@Override
	public int getFirstNumber() {
		return mFistNumber;
	}
	
	@Override
	public int getSecondNumber() {
		return mSecondNumber;
	}
	
	private int mFistNumber;
	private int mSecondNumber;
}

class CalculatorImp implements Calculator {
	
	@Override
	public PreviousCalculationToCareTaker backupLastCalculation() {
		return PreviousCalculationImp.create(mFirstNumber, mSecondNumber);
	}
	
	@Override
	public int getCalculationResult() {
		return mFirstNumber + mSecondNumber;
	}
	
	@Override
	public void restorePreviousCalculation(PreviousCalculationToCareTaker memento) {
		PreviousCalculationToOriginator temp = (PreviousCalculationToOriginator)memento;
		
		this.mFirstNumber = temp.getFirstNumber();
		this.mSecondNumber = temp.getSecondNumber();
	}
	
	@Override
	public void setFirstNumber(int firstNumber) {
		this.mFirstNumber = firstNumber;
	}
	
	@Override
	public void setSecondNumber(int secondNumber) {
		this.mSecondNumber = secondNumber;
	}
	
	private int mFirstNumber;
	private int mSecondNumber;
}
