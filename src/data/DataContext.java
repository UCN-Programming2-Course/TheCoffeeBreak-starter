package data;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataContext {

	Connection getConnection();

	Connection startTransaction() throws SQLException;

	void commitTransaction();

	void rollbackTransaction();
}
