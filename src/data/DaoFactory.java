package data;

import data.daos.CustomerDaoSQLServer;
import data.daos.OrderDaoSQLServer;
import data.daos.ProductDaoSQLServer;

public class DaoFactory {

	// TODO implement the factory methods so they instantiate the contrete dao implementation and return the object as the interface
	
	public static OrderDao createOrderDao(DataContext dbContext) {
		
		return new OrderDaoSQLServer(dbContext);
	}
	
	public static ProductDao createProductDao(DataContext dbContext) {
		
		return new ProductDaoSQLServer(dbContext);
	}

	public static CustomerDao createCustomerDao(DataContext dataContext) {

		return new CustomerDaoSQLServer(dataContext);
	}
}
