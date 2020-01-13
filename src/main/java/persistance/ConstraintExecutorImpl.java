package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.BusinessRule;

public class ConstraintExecutorImpl implements ConstraintExecutor {

	@Override
	public void executeConstraint(Connection connection, BusinessRule businessRule) {

		try {
			PreparedStatement stmt = connection.prepareStatement(businessRule.getCode());
			stmt.execute();
			stmt.close();
			System.out.println("Het uitvoeren van de constraint is wel gelukt!");
		} catch (SQLException e) {
			System.out.println("Het uitvoeren van de constraint is niet gelukt!");
			e.printStackTrace();
		}
	}

}
