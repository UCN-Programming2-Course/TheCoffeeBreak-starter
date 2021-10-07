package data;

import java.util.List;

import model.Customer;

public interface CustomerDao {
	
	List<Customer> getAll();
	
	int create(Customer customer);
	
	int update(Customer customer);
	
	int delete(Customer customer);
}
