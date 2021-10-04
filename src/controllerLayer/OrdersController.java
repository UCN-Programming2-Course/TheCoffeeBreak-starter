package controllerLayer;

import java.util.List;

import model.Order;

public interface OrdersController {

	List<Order> getActiveOrders();
	
	boolean createNewOrder(Order order);
	
	boolean setOrderStatusToFinished(Order order);
}
