package persistance;

import java.sql.Connection;

import service.BusinessRule;

public interface ConstraintExecutor {
	public void executeConstraint(Connection connection, BusinessRule businessRule);
}
