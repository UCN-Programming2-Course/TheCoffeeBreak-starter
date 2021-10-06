package dataAccessLayer;

import java.util.List;

import modelLayer.Product;

public interface ProductDao {

	// TODO define CRUD methods to the Products table
	List<Product> getAll();
}
