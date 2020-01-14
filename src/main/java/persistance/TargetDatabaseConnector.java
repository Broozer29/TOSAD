package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TargetDatabaseConnector {

	static Connection targetDatabaseConnection = null;

	public static Connection getInstance() {
		if (targetDatabaseConnection == null) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/TosadTarget",
						"postgres", "Spetter6");
				targetDatabaseConnection = conn;
				System.out.println("Connected to the database! (Target)");
			} catch (SQLException e) {
				throw new RuntimeException(String.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage()));
			}
		}
		return targetDatabaseConnection;
	}
}