package dataAccessLayer;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataContext {
	
	Connection getConnection() throws SQLException;
}
