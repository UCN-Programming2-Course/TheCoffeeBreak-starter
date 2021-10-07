package data.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.DataContext;
import data.OrderDao;
import microsoft.sql.Types;
import model.Customer;
import model.Order;
import model.OrderLine;

public class OrderDaoSQLServer implements OrderDao {

	private final DataContext dataContext;

	public OrderDaoSQLServer(DataContext dataContext) {

		this.dataContext = dataContext;
	}

	@Override
	public List<Order> getAll() {

		List<Order> result = new ArrayList<Order>();
		try {

			String sql = "SELECT Orders.Id, Orders.Discount, Orders.Finished, Customers.Id, Customers.Name "
					+ "FROM Orders " + "JOIN Customers ON Customers.Id = Orders.CustomerId ";

			PreparedStatement statement = dataContext.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// mapping order
				Order order = new Order();
				order.setOrderNumber(rs.getInt(1));
				order.setDiscount(rs.getDouble(2));
				if (rs.getDate(3) == null)
					order.setStatus(Order.ACTIVE);
				else
					order.setStatus(Order.FINISHED);
				// mapping customer
				Customer customer = new Customer();
				customer.setId(rs.getInt(4));
				customer.setName(rs.getString(5));
				order.setCustomer(customer);
				result.add(order);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int create(Order order) {
		Connection connection = null;
		try {
			connection = dataContext.getConnection();

			String insertOrderSQL = "INSERT INTO Orders VALUES(?, ?, ?)";
			PreparedStatement insertOrderStatement = connection.prepareStatement(insertOrderSQL,
					Statement.RETURN_GENERATED_KEYS);
			insertOrderStatement.setLong(1, order.getCustomer().getId());
			insertOrderStatement.setDouble(2, order.getDiscount());
			insertOrderStatement.setNull(3, Types.DATETIME);
			int ordersCreated = insertOrderStatement.executeUpdate();

			if (ordersCreated == 0)
				throw new SQLException("Order could not be created");

			ResultSet orderIds = insertOrderStatement.getGeneratedKeys();
			if (orderIds.next()) {
				// now the orderlines can be created
				int orderId = orderIds.getInt(1);

				for (int idx = 0; idx < order.getOrderLines().size(); idx++) {
					OrderLine ol = order.getOrderLines().get(idx);

					String insertOrderLineSQL = "INSERT INTO Orderlines (OrderId, ProductId, Quantity, SubTotal) VALUES (?, ?, ?, ?)";
					PreparedStatement insertOrderlineStatement = connection.prepareStatement(insertOrderLineSQL);
					insertOrderlineStatement.setInt(1, orderId);
					insertOrderlineStatement.setInt(2, ol.getItem().getId());
					insertOrderlineStatement.setInt(3, ol.getQuantity());
					insertOrderlineStatement.setDouble(4, ol.getSubTotal());

					System.out.println(ol.getItem().getId());

					int orderlinesCreated = insertOrderlineStatement.executeUpdate();

					if (orderlinesCreated == 0)
						throw new SQLException("Orderline could not be created");

				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int update(Order order) {

		int result = 0;

		try {

			String updateOrderSQL = "UPDATE Orders SET Finished = ? WHERE Id = ?";
			PreparedStatement updateOrderStatement = dataContext.getConnection().prepareStatement(updateOrderSQL);
			updateOrderStatement.setDate(1, new Date(System.currentTimeMillis()));
			updateOrderStatement.setInt(2, order.getOrderNumber());
			result = updateOrderStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int delete(Order order) {

		// TODO delete order here

		return 0;
	}
}
