package dataAccessLayer;

import dataAccessLayer.implementations.OrderDaoImplementation;
import dataAccessLayer.implementations.ProductDaoImplementation;

public class DaoFactory {

	// TODO implement the factory methods so they instantiate the contrete dao implementation and return the object as the interface
	
	public static OrderDao createOrderDao(DataContext dbContext) {
		
		return new OrderDaoImplementation(dbContext);
	}
	
	public static ProductDao createProductDao(DataContext dbContext) {
		
		return new ProductDaoImplementation(dbContext);
	}
}
