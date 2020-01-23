package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import service.BusinessRuleConstructor;
import domain.BusinessRule;
import domain.Value;

public class BusinessRulePostgresDaoImpl implements BusinessRuleDao {

	private static Connection conn = PostgresBaseDao.getConnection();
	private BusinessRuleTypeDao brtpdi = new BusinessRuleTypePostgresDaoImpl();
	private ValueDao vpdi = new ValuePostgresDaoImpl();
	private OperatorDao opdi = new OperatorPostgresDaoImpl();
	private ColumnDao cpdi = new ColumnPostgresDaoImpl();
	private TableDao tpdi = new TablePostgresDaoImpl();

	@Override
	public List<BusinessRule> findAll() {

		try {

			Statement stmt = conn.createStatement();
			String strQuery = "SELECT * FROM KLAS";
			ResultSet rs = stmt.executeQuery(strQuery);
			while (rs.next()) {
				BusinessRuleConstructor businessRuleConstructor = new BusinessRuleConstructor();
				int businessRuleID = rs.getInt("ID");
				businessRuleConstructor.setListOfValues((ArrayList<Value>) vpdi.findByBusinessRuleID(businessRuleID));
				businessRuleConstructor.setCompareRule(opdi.findByID(rs.getString("OPERATOR_ID")));
				businessRuleConstructor.setColumn(cpdi.findByBusinessRuleID(businessRuleID));
				businessRuleConstructor.setTable(tpdi.findByBusinessRuleID(businessRuleID));
				businessRuleConstructor.setExample(rs.getString("EXAMPLE"));
				businessRuleConstructor.setId(businessRuleID);
				businessRuleConstructor.setTypeOfConstraint(brtpdi.findByCode(rs.getString("BUSINESSRULETYPE_CODE")));
//				b.setTypeOfConstraint(typeOfConstraint);
			}

		} catch (SQLException sqle) {

		}

		return null;
	}

	@Override
	public BusinessRule findById(int id) {
		BusinessRuleConstructor businessRuleConstructor = new BusinessRuleConstructor();

		try {
			String strQuery = "SELECT * FROM BUSINESSRULE WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery(strQuery);

			while (rs.next()) {
				int businessRuleID = rs.getInt("ID");
				businessRuleConstructor.setListOfValues((ArrayList<Value>) (vpdi.findByBusinessRuleID(businessRuleID)));
				businessRuleConstructor.setCompareRule(opdi.findByID(rs.getString("OPERATOR_ID")));
				businessRuleConstructor.setColumn(cpdi.findByBusinessRuleID(businessRuleID));
				businessRuleConstructor.setTable(tpdi.findByBusinessRuleID(businessRuleID));
				businessRuleConstructor.setExample(rs.getString("EXAMPLE"));
				businessRuleConstructor.setId(businessRuleID);
				businessRuleConstructor.setTypeOfConstraint(brtpdi.findByCode(rs.getString("BUSINESSRULETYPE_CODE")));

			}
		} catch (SQLException sqle) {

		}

		return businessRuleConstructor.createBusinessRule();
	}

	@Override
	public boolean save(BusinessRule businessRule) {
		try {

			String strQuery = "INSERT INTO BUSINESSRULE (ID, OPERATOR_ID, BUSINESSRULETYPE_CODE, EXAMPLE) VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setInt(1, Integer.parseInt(businessRule.getID().getID()));
			pstmt.setInt(2, Integer.parseInt(businessRule.getCompareRule().getID()));
			pstmt.setString(3, businessRule.getTypeOfConstraint().getId());
			pstmt.setString(4, businessRule.getCode().getGiven());
			opdi.save(businessRule.getCompareRule());
			for (Value i : businessRule.getListOfValues()) {
				vpdi.save(i);
			}

			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(BusinessRule businessRule) {
		try {

			String strQuery = "update BUSINESSRULE SET ID = ?, OPERATOR_ID = ?, BUSINESSRULETYPE_CODE = ?, EXAMPLE = ? WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setInt(1, Integer.parseInt(businessRule.getID().getID()));
			pstmt.setInt(2, Integer.parseInt(businessRule.getCompareRule().getID()));
			pstmt.setString(3, businessRule.getTypeOfConstraint().getId());
			pstmt.setString(4, businessRule.getCode().getGiven());
			pstmt.setInt(5, Integer.parseInt(businessRule.getID().getID()));
			opdi.update(businessRule.getCompareRule());
			for (Value i : businessRule.getListOfValues()) {
				vpdi.update(i);
			}
			pstmt.executeUpdate();

			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(BusinessRule businessRule) {
		try {

			String strQuery = "DELETE FROM BUSINESSRULE WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(strQuery);
			pstmt.setInt(1, Integer.parseInt(businessRule.getID().getID()));
			opdi.delete(businessRule.getCompareRule());
			for (Value i : businessRule.getListOfValues()) {
				vpdi.delete(i);
			}
			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

}
