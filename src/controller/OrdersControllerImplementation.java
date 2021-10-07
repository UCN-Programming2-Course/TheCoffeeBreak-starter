package controller;

import java.sql.SQLException;
import java.util.List;

import data.CustomerDao;
import data.DaoFactory;
import data.DataContext;
import data.OrderDao;
import data.ProductDao;
import model.Order;
import model.Product;

public class OrdersControllerImplementation implements OrdersController {

	private final DataContext dataContext;
	private final OrderDao orderDao;
	private final ProductDao productDao;
	private final CustomerDao customerDao;

	public OrdersControllerImplementation(DataContext dataContext) {

		this.dataContext = dataContext;
		this.orderDao = DaoFactory.createOrderDao(dataContext);
		this.productDao = DaoFactory.createProductDao(dataContext);
		this.customerDao = DaoFactory.createCustomerDao(dataContext);
	}

	@Override
	public List<Order> getActiveOrders() {
		List<Order> orders = orderDao.getAll();

		orders.removeIf(o -> o.getStatus() != Order.ACTIVE);

		return orders;
	}

	@Override
	public boolean createNewOrder(Order order) {
		// The chosen strategy for customers in this solution is that a customer is
		// created with every new order, which requires a small change in the database
		// though. When an order is marked as finished the customer is deleted again
		// (see the setOrderStatusToFinished method)
		// Because of this, I need to start the transaction here, so it spans over
		// two daos.

		boolean result = false;
		try {
			dataContext.startTransaction();

			int customerId = customerDao.create(order.getCustomer());
			order.getCustomer().setId(customerId);
			result = orderDao.create(order) == 1;

			dataContext.commitTransaction();

		} catch (SQLException e) {

			dataContext.rollbackTransaction();
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean setOrderStatusToFinished(Order order) {

		boolean result = false;
		try {
			dataContext.startTransaction();
			customerDao.delete(order.getCustomer());

			order.setStatus(Order.FINISHED);
			result = orderDao.update(order) == 1;
			
			dataContext.commitTransaction();
		} catch (SQLException e) {

			dataContext.rollbackTransaction();
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAll();
	}

}
