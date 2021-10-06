package controllerLayer;

import java.util.List;

import modelLayer.Order;
import modelLayer.Product;

public interface OrdersController {

	List<Order> getActiveOrders();
	
	boolean createNewOrder(Order order);
	
	boolean setOrderStatusToFinished(Order order);

	List<Product> getAllProducts();
}
