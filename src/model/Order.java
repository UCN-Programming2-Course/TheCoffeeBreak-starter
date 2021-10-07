package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private int orderNumber;
	private String status;
	private double discount;
	private Customer customer;
	
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalPrice() {
		double totalPrice = 0;
		for (int idx = 0; idx < orderLines.size(); idx++) {
			totalPrice += orderLines.get(idx).getSubTotal();
		}

		return totalPrice * (1 + this.discount);
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public static final String ACTIVE = "Active";
	public static final String FINISHED = "Finished";
}
