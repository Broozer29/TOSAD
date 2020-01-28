package persistence.target;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TargetDatabaseConnector {

	static Connection targetDatabaseConnection = null;
	

	public static Connection getInstance() {
		if (targetDatabaseConnection == null) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:sqlserver://145.89.157.57:8521/TOCBAteam@EDU24",
						"cursist", "cursist");
				targetDatabaseConnection = conn;
				System.out.println("Connected to the database! (Target)");
			} catch (SQLException e) {
				throw new RuntimeException(String.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage()));
			}
		}
		return targetDatabaseConnection;
	}
}