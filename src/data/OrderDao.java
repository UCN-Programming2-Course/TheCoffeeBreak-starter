package data;

import java.util.List;

import model.Order;

public interface OrderDao {

	// TODO define CRUD methods to the Orders table
	List<Order> getAll();
	
	int create(Order order);
	
	int update(Order order);
	
	int delete(Order order);
}
