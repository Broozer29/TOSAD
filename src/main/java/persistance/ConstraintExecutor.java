package persistance;

import java.sql.Connection;

import domain.businessrules.BusinessRule;

public interface ConstraintExecutor {
	public void executeConstraint(Connection connection, BusinessRule businessRule);
}
