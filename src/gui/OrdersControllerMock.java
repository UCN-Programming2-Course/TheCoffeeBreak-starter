package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controllerLayer.OrdersController;
import model.Order;
import model.OrderLine;
import model.Product;

public class OrdersControllerMock implements OrdersController {

	private static List<Order> orders = new ArrayList<Order>();
	
	public OrdersControllerMock() {
		
		Product product1 = new Product();
		product1.setName("Coffee1");
		product1.setDescription("Test test test teststestse tesset");
		product1.setPrice(20.00);
		
		Product product2 = new Product();
		product2.setName("Coffee2");
		product2.setDescription("Test test test teststestse tesset");
		product2.setPrice(25.00);
		
		Product product3 = new Product();
		product3.setName("Coffee3");
		product3.setDescription("Test test test teststestse tesset");
		product3.setPrice(22.50);
		
		OrderLine orderLine11 = new OrderLine();
		orderLine11.setItem(product1);
		orderLine11.setQuantity(1);
		OrderLine orderLine12 = new OrderLine();
		orderLine12.setItem(product3);
		orderLine12.setQuantity(1);
				
		Order order1 = new Order();
		order1.setOrderNumber(1);
		order1.setStatus("Active");
		order1.setCustomerName("Svend");
		order1.getOrderLines().add(orderLine11);
		order1.getOrderLines().add(orderLine12);
		orders.add(order1);
		
		OrderLine orderLine21 = new OrderLine();
		
		Order order2 = new Order();
		order2.setOrderNumber(2);
		order2.setStatus("Active");		
		order2.setCustomerName("Valdemar");
		order2.getOrderLines().add(orderLine21);
		orders.add(order2);
		
		OrderLine orderLine31 = new OrderLine();
		OrderLine orderLine32 = new OrderLine();
		OrderLine orderLine33 = new OrderLine();
		
		Order order3 = new Order();
		order3.setOrderNumber(3);
		order3.setStatus("Active");
		order3.setCustomerName("Knud");
		order3.getOrderLines().add(orderLine31);
		order3.getOrderLines().add(orderLine32);
		order3.getOrderLines().add(orderLine33);
		orders.add(order3);
	}
	
	@Override
	public List<Order> getActiveOrders() {
		
		return orders.stream().filter(o->o.getStatus() == Order.ACTIVE).collect(Collectors.toList());	
	}

	@Override
	public boolean createNewOrder(Order order) {
		// TODO Auto-generated method stub
		
		orders.add(order);
		return true;
	}

	@Override
	public boolean setOrderStatusToFinished(Order order) {
		
		order.setStatus("Finished");
		
		return true;
	}

}
