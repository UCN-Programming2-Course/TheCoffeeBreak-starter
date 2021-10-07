package controller;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import data.DataContext;

public class Database implements DataContext {

	private static final String serverName = "localhost\\sqlexpress";
	private static final String databaseName = "TheCoffeeBreak";
	private static final String username = "barista";
	private static final String password = "ThisIsALongPassword";

	private Connection connection = null;

	@Override
	public Connection getConnection() {

		if (connection == null) {

			try {

				SQLServerDataSource ds = new SQLServerDataSource();
				ds.setUser(username);
				ds.setPassword(password);
				ds.setServerName(serverName);
				ds.setDatabaseName(databaseName);
				connection = ds.getConnection();

			} catch (SQLServerException e) {

				e.printStackTrace();
			}
		}
		return connection;
	}

	@Override
	public Connection startTransaction() throws SQLException {
		connection = getConnection();
		connection.setAutoCommit(false);
		return connection;
	}

	@Override
	public void commitTransaction() {

		try {

			connection.commit();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		connection = null;
	}

	@Override
	public void rollbackTransaction() {
		try {

			connection.rollback();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		connection = null;
	}
}
