package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Value;

public class OperatorPostgresDaoImpl implements OperatorDao{
	private static Connection conn = PostgresBaseDao.getConnection();

	@Override
	public Value findByID(String ID) {
		Value value = new Value();

		try {
			Statement stmt = conn.createStatement();
			String strQuery = "SELECT * FROM OPERATOR WHERE ID = " + ID;
			ResultSet rs = stmt.executeQuery(strQuery);

			while (rs.next()) {
			value.setID(Integer.toString(rs.getInt("ID")));
			value.setGiven(rs.getString("SYMBOL"));
				
			}
		} catch (SQLException sqle) {

		}

		return value;
	}

	@Override
	public boolean save(Value value) {
		try {
			String strQuery = "INSERT INTO OPERATOR (ID, SYMBOL) VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setInt(1, Integer.parseInt(value.getID()));
			pstmt.setString(2, value.getGiven());
			return true;
			
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean update(Value value) {
		try {

			String strQuery = "update OPERATOR SET ID = ?, SYMBOL = ? WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setInt(1, Integer.parseInt(value.getID()));
			pstmt.setString(2, value.getGiven());
			pstmt.setInt(3, Integer.parseInt(value.getID()));
			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Value value) {
		try {

			String strQuery = "DELETE FROM OPERATOR WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setInt(1, Integer.parseInt(value.getID()));
			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	

}
