package persistence.tool.table.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Table;
import persistence.tool.connection.postgres.PostgresBaseDao;
import persistence.tool.table.TableDao;

public class TablePostgresDaoImpl implements TableDao {
	
	private static Connection conn = PostgresBaseDao.getConnection();

	@Override
	public List<Table> findByBusinessRuleID(int businessID) {
		List<Table> deTables = new ArrayList<Table>();

		try {
			String strQuery = "SELECT * FROM TABLENAME JOIN BUSINESSRULEKOPPELTABLENAME AS BKT ON TABLENAME_NAME = NAME WHERE BKT.BUSINESSRULE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setInt(0, businessID);
			ResultSet rs = pstmt.executeQuery(strQuery);

			while (rs.next()) {
				Table t = new Table();
				t.setName("NAME");
				deTables.add(t);

			}
		} catch (SQLException sqle) {

		}

		return deTables;
	}

	@Override
	public boolean save(Table t) {
		try {

			String strQuery = "INSERT INTO TABLENAME (NAME) values(?)";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, t.getName());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Table t) {
		try {

			String strQuery = "update TABLENAME SET NAME = ? WHERE NAME = ?";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getName());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}


	

}
