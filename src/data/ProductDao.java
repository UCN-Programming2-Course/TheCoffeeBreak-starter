package data;

import java.util.List;

import model.Product;

public interface ProductDao {

	// TODO define CRUD methods to the Products table
	List<Product> getAll();
}
