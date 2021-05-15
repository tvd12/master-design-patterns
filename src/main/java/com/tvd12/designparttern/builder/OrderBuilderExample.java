package com.tvd12.designparttern.builder;

public class OrderBuilderExample {

	public static class MyOrder {
		
	    private double money;
	    
	    public MyOrder(Builder builder) {
	        this.money = builder.money;
	    }
	    
	    public double getMoney() {
	        return money;
	    }
	    
	    public static class Builder {
	    	
	        private double money;
	        
	        public Builder money(double money) {
	            this.money = money;
	            return this;
	        }
	        
	        public MyOrder build() {
	            return new MyOrder(this);
	        }
	    }
	}
	
	public static class BillService {
	    public void pay(MyOrder order) {
	        payWithCreditCard(order.getMoney());
	    }
	    
	    private void payWithCreditCard(double money) {
	    	System.out.println("user pay with credit card: " + money);
	    }
	}
	
	public static void main(String[] args) {
		BillService billService = new BillService();
		
		MyOrder order = new MyOrder.Builder()
				.money(1000)
				.build();
		
		billService.pay(order);
	}
	
}
