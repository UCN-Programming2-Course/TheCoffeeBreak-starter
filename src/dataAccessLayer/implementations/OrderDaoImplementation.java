package dataAccessLayer.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.DataContext;
import dataAccessLayer.OrderDao;
import modelLayer.Order;

public class OrderDaoImplementation implements OrderDao {

	private final DataContext dataContext;

	public OrderDaoImplementation(DataContext dataContext) {

		this.dataContext = dataContext;
	}

	@Override
	public List<Order> getAll() {

		List<Order> result = new ArrayList<Order>();
		try {

			String sql = "SELECT Orders.Id, Customers.Name, Orders.Discount, Orders.Finished FROM Orders "
					+ "JOIN Customers ON Customers.Id = Orders.CustomerId ";

			PreparedStatement statement = dataContext.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				Order order = new Order();
				order.setOrderNumber(rs.getInt(1));
				order.setCustomerName(rs.getString(2));
				order.setDiscount(rs.getDouble(3));
				if (rs.getDate(4) == null)
					order.setStatus(Order.ACTIVE);
				else
					order.setStatus(Order.FINISHED);
				result.add(order);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int create(Order order) {

		// TODO insert order and orderlines here
		
		return 0;
	}

	@Override
	public int update(Order order) {
		
		// TODO update order here
		
		return 0;
	}

	@Override
	public int delete(Order order) {
		
		// TODO delete order here
		
		return 0;
	}
}
