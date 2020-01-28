package persistence.target;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import domain.BusinessRule;

public class ConstraintExecutorImpl implements ConstraintExecutor {

	@Override
	public void executeConstraint(Connection connection, BusinessRule businessRule) {
		if (businessRule.getTypeOfCode().equals("constraint")) {
			try {
				Statement st = connection.createStatement();
				st.executeUpdate(businessRule.getConstraint());
				connection.commit();
				st.close();
				System.out.println("Het uitvoeren van de constraint is wel gelukt!");
			} catch (SQLException e) {
				System.out.println("Het uitvoeren van de constraint is niet gelukt!");
				e.printStackTrace();
			}
		}
		
		if (businessRule.getTypeOfCode().equals("trigger")) {
			try {
				System.out.println("\n" + businessRule.getTrigger() + "\n");
				Statement st = connection.createStatement();
				st.executeUpdate(businessRule.getTrigger());
				connection.commit();
				st.close();
				System.out.println("Het uitvoeren van de constraint is wel gelukt!");
			} catch (SQLException e) {
				System.out.println("Het uitvoeren van de constraint is niet gelukt!");
				e.printStackTrace();
			}
		}
	}

}
