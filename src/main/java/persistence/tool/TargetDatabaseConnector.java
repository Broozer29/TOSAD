package persistence.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TargetDatabaseConnector {

	static Connection targetDatabaseConnection = null;
	

	public static Connection getInstance() {
		if (targetDatabaseConnection == null) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:postgresql://145.89.157.57:5432/Tool","postgres", "S()nt5LogE");
				targetDatabaseConnection = conn;
			} catch (SQLException e) {
				throw new RuntimeException(String.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage()));
			}
		}
		return targetDatabaseConnection;
	}
}