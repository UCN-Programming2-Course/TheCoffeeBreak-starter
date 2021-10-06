package controllerLayer.implementations;

import java.util.List;

import controllerLayer.OrdersController;
import dataAccessLayer.DaoFactory;
import dataAccessLayer.DataContext;
import dataAccessLayer.OrderDao;
import dataAccessLayer.ProductDao;
import modelLayer.Order;
import modelLayer.Product;

public class OrdersControllerImplementation implements OrdersController {
	
	private final OrderDao orderDao;
	private final ProductDao productDao;
	
	public OrdersControllerImplementation(DataContext dataContext) {
		
		this.orderDao = DaoFactory.createOrderDao(dataContext);
		this.productDao = DaoFactory.createProductDao(dataContext);		
	}
	
	@Override
	public List<Order> getActiveOrders() {
		List<Order> orders = orderDao.getAll();
		
		orders.removeIf(o -> o.getStatus() != Order.ACTIVE);
		
		return orders;
	}

	@Override
	public boolean createNewOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setOrderStatusToFinished(Order order) {

		order.setStatus(Order.FINISHED);
		int result = orderDao.update(order);
		
		return result == 1;
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAll();
	}

}
