package persistence.target;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.BusinessRule;

public class ConstraintExecutorImpl implements ConstraintExecutor {

	@Override
	public void executeConstraint(Connection connection, BusinessRule businessRule) throws SQLException {
		if (businessRule.getTypeOfCode().equals("constraint")) {
			try {
				Statement stmt = connection.createStatement();
				String strQuery = businessRule.getConstraint();
				ResultSet rs = stmt.executeQuery(strQuery);
				connection.commit();
				stmt.close();
				System.out.println("Het uitvoeren van de constraint is wel gelukt!");
			} catch (SQLException e) {
				System.out.println("Het uitvoeren van de constraint is niet gelukt!");
				connection.rollback();
				e.printStackTrace();
			}
		}
		
		if (businessRule.getTypeOfCode().equals("trigger")) {
			try {
				Statement stmt = connection.createStatement();
				String strQuery = businessRule.getTrigger();
				ResultSet rs = stmt.executeQuery(strQuery);
				connection.commit();
				stmt.close();
				System.out.println("Het uitvoeren van de trigger is wel gelukt!");
			} catch (SQLException e) {
				System.out.println("Het uitvoeren van de trigger is niet gelukt!");
				connection.rollback();
				e.printStackTrace();
			}
		}
	}

}
