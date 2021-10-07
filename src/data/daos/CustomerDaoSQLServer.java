package data.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import data.CustomerDao;
import data.DataContext;
import model.Customer;

public class CustomerDaoSQLServer extends BaseDao implements CustomerDao {

	public CustomerDaoSQLServer(DataContext dataContext) {
		super(dataContext);

	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(Customer customer) {
		int customerId = 0;
		try {
			// first I create a customer with the provided name
			String insertCustomerSQL = "INSERT INTO Customers (Name) VALUES (?)";
			PreparedStatement insertCustomerStatement;

			insertCustomerStatement = dataContext.getConnection().prepareStatement(insertCustomerSQL,
					Statement.RETURN_GENERATED_KEYS);

			insertCustomerStatement.setString(1, customer.getName());
			int customersCreated = insertCustomerStatement.executeUpdate();

			if (customersCreated == 0)
				throw new SQLException("Customer could not be created");

			ResultSet customerIds = insertCustomerStatement.getGeneratedKeys();
			if (customerIds.next()) {
				customerId = customerIds.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerId;
	}

	@Override
	public int update(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Customer customer) {

		int result = 0;
		try {

			String deleteCustomerSQL = "DELETE FROM Customers WHERE Id = ?";
			PreparedStatement deleteCustomerStatement = dataContext.getConnection().prepareStatement(deleteCustomerSQL);
			deleteCustomerStatement.setInt(1, customer.getId());
			result = deleteCustomerStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

}
