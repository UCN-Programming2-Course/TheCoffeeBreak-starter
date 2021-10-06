package dataAccessLayer.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.DataContext;
import dataAccessLayer.ProductDao;
import modelLayer.Product;

public class ProductDaoImplementation implements ProductDao {

	private final DataContext dataContext;

	public ProductDaoImplementation(DataContext dataContext) {

		this.dataContext = dataContext;
	}

	@Override
	public List<Product> getAll() {

		List<Product> result = new ArrayList<Product>();
		try {
			Connection conn = dataContext.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM Products");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
			
				Product p = new Product();
				p.setName(rs.getString(2));
				p.setDescription(rs.getString(3));
				p.setPrice(rs.getDouble(4));
				result.add(p);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
