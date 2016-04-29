package com.tvd12.designparttern.command;

import java.util.LinkedList;
import java.util.Queue;

public class MeanOrderExample { //Customer
	public static void main(String[] args) {
		SoupOrder soupOrder = new SoupOrder(new Cook(100));
		GrillOrder grillOrder = new GrillOrder(new Cook(200));
		
		Waiter waiter = new Waiter();
		waiter.takeOrder(soupOrder);
		waiter.takeOrder(grillOrder);
		
		waiter.processOrders();
	}
}

class Cook {
	
	public Cook() {
		this(10);
	}
	
	public Cook(int time) {
		this.mTimeCook = time;
	}
	
	public void boil() {
		System.out.println("boil in " + mTimeCook);
	}
	
	public void grill() {
		System.out.println("grill in " + mTimeCook);
	}
	
	private int mTimeCook;
}

interface IOrder {
	public void execute();
}

class SoupOrder implements IOrder {
	public SoupOrder(Cook cook) {
		this.mCook = cook;
	}
	public void execute() {
		mCook.boil();
	}
	private Cook mCook;
}

class GrillOrder implements IOrder {
	public GrillOrder(Cook cook) {
		this.mCook = cook;
	}
	
	public void execute() {
		mCook.grill();
	}
	
	private Cook mCook;
}

class Waiter {
	
	public Waiter() {
		mOrders = new LinkedList<>();
	}
	
	public void takeOrder(IOrder order) {
		mOrders.add(order);
	}
	
	public void processOrders() {
		for(IOrder order : mOrders) {
			order.execute();
		}
		mOrders.clear();
	}
	
	private Queue<IOrder> mOrders;
}

