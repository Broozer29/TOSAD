package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ToolDatabaseConnector {

	static Connection toolDatabaseConnection;
	static Boolean connectionExists = false;;

	private ToolDatabaseConnector() {
		initializeConnection();
	}

	private static void initializeConnection() {
		if (!connectionExists) {
			try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/TosadTool", "postgres",
					"Spetter6")) {

				if (conn != null) {
					toolDatabaseConnection = conn;
					connectionExists = true;
					System.out.println("Connected to the database! Test succesvol woohoo (Tool)");
				} else {
					System.out.println("Failed to make connection!");
				}

			} catch (SQLException e) {
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getInstance() {
		if (!connectionExists) {
			initializeConnection();
		}
		return toolDatabaseConnection;
	}

}
