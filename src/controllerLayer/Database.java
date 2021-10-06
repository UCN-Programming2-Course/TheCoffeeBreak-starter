package controllerLayer;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import dataAccessLayer.DataContext;

public class Database implements DataContext {

	private static final String serverName = "localhost\\sqlexpress";
	private static final String databaseName = "TheCoffeeBreak";
	private static final String username = "barista";
	private static final String password = "ThisIsALongPassword";	

	@Override
	public Connection getConnection() throws SQLException {

		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser(username);
		ds.setPassword(password);
		ds.setServerName(serverName);
		ds.setDatabaseName(databaseName);
		return ds.getConnection();
	}

}
