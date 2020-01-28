package persistence.target;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.BusinessRule;

public class ConstraintExecutorImpl implements ConstraintExecutor {

	@Override
	public void executeConstraint(Connection connection, BusinessRule businessRule) {
		if (businessRule.getTypeOfCode().equals("constraint")) {
			try {
				PreparedStatement stmt = connection.prepareStatement(businessRule.getConstraint());
				stmt.execute();
				stmt.close();
				System.out.println("Het uitvoeren van de constraint is wel gelukt!");
			} catch (SQLException e) {
				System.out.println("Het uitvoeren van de constraint is niet gelukt!");
				e.printStackTrace();
			}
		}
		
		if (businessRule.getTypeOfCode().equals("trigger")) {
			try {
				System.out.println("\n" + businessRule.getTrigger() + "\n");
				PreparedStatement stmt = connection.prepareStatement(businessRule.getTrigger());
				stmt.execute();
				stmt.close();
				System.out.println("Het uitvoeren van de constraint is wel gelukt!");
			} catch (SQLException e) {
				System.out.println("Het uitvoeren van de constraint is niet gelukt!");
				e.printStackTrace();
			}
		}
	}

}
