package com.tvd12.designparttern.visitor;

import java.util.ArrayList;
import java.util.List;

public class CustommerExample {

}

interface IVisitor {
	public void visit(Customer customer);
	public void visit(Order order);
	public void visit(Item item);
}

interface IVisitable {
	public void accept(IVisitor visitor);
}

class CustomerGroup implements IVisitable {
	
	public CustomerGroup() {
		mCustomers = new ArrayList<>();
	}
	
	public void accept(IVisitor visitor) {
		for(Customer customer : mCustomers) {
			customer.accept(visitor);
		}
	}
	
	public void addCustomer(Customer customer) {
		mCustomers.add(customer);
	}
	
	private List<Customer> mCustomers;
}

class Customer implements IVisitable {
	
	public Customer() {
		this.mOrders = new ArrayList<>();
	}
	
	public static Customer create(String name) {
		Customer customer = new Customer();
		customer.setName(name);
		
		return customer;
	}
	
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		this.mName = name;
	}
	
	public void addOrder(Order order) {
		this.mOrders.add(order);
	}
	
	private String mName;
	private List<Order> mOrders;
}

class Item implements IVisitable {
	
	public static Item create(String name) {
		Item item = new Item();
		item.setName(name);
		
		return item;
	}
	
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	public void setName(String name) {
		this.mName = name;
	}
	
	public String getName() {
		return this.mName;
	}
	
	private String mName;
}

class Order implements IVisitable {
	
	public Order() {
		this.mItems = new ArrayList<>();
	}
	
	public static Order create(String name) {
		Order order = new Order();
		order.setName(name);
		
		return order;
	}
	
	public static Order create(String name, String itemName) {
		Order order = create(name);
		order.addItem(Item.create(itemName));
		
		return order;
	}
	
	public void setName(String name) {
		this.mName = name;
	}
	
	public String getName() {
		return this.mName;
	}
	
	public void accept(IVisitor visitor) {
		for(Item item : mItems) {
			item.accept(visitor);
		}
	}
	
	public void addItem(Item item) {
		this.mItems.add(item);
	}
	
	private String mName;
	private List<Item> mItems;
}

class GeneralReport implements IVisitor {
	
	@Override
	public void visit(Customer customer) {
		customer.accept(this);
		mCustomersNo ++;
	}
	
	@Override
	public void visit(Order order) {
		order.accept(this);
		mOrdersNo ++;
	}
	
	@Override
	public void visit(Item item) {
		item.accept(this);
		mItemsNo ++;
	}
	
	public void displayResults() {
		System.out.println("mCustomersNo: " + mCustomersNo
				+ "\n"
				+ "mOrdersNo: " + mOrdersNo
				+ "\n"
				+ "mItemsNo: " + mItemsNo);
	}
	
	private int mCustomersNo;
	private int mOrdersNo;
	private int mItemsNo;
}