package com.tvd12.designparttern.command;

import java.util.ArrayList;
import java.util.List;

public class StockTradeExample {

	public static void main(String[] args) {
		StockTrade stock = new StockTrade("Ta Van Dung", 10);
		BuyStockCommand bsc = new BuyStockCommand(stock);
		SellStockCommand ssc = new SellStockCommand(stock);
		Agent agent = new Agent();
		
		agent.takeOrder(bsc);
		agent.takeOrder(ssc);
		
		agent.placeOrders(); // Buy shell Shares
	}

}

class StockTrade {

	public StockTrade() {
		this("TVD", 10);
	}
	
	public StockTrade(String name, int quantity) {
		this.mName = name;
		this.mQuantity = quantity;
	}
	
	public void buy() {
        System.out.println("You want to buy stock " + toString());
    }
    public void sell() {
        System.out.println("You want to sell stock " + toString());
    }
    
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("{\"" + mName + "\", " + mQuantity + "}");
    	
    	return builder.toString();
    }
	
    private String mName;
    private int mQuantity;
}

interface Order {

	public abstract void execute();
	
}

class BuyStockCommand implements Order {

	public BuyStockCommand(StockTrade stock) {
		this.mStock = stock;
	}
	
	public void execute() {
		this.mStock.buy();
	}
	
	private StockTrade mStock;
}


class SellStockCommand implements Order {

	public SellStockCommand(StockTrade stock) {
		this.mStock = stock;
	}
	
	public void execute() {
		this.mStock.sell();
	}
	
	private StockTrade mStock;
}


class Agent {

	public Agent() {
		mOrdersQueue = new ArrayList<>();
	}

	public void takeOrder(Order order) {
		mOrdersQueue.add(order);
	}

	public void placeOrders() {
		for (Order order : mOrdersQueue) {
			order.execute();
		}
		mOrdersQueue.clear();
	}

	private List<Order> mOrdersQueue;
}

