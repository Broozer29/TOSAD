package persistence;

import java.sql.Connection;

import domain.BusinessRule;

public interface ConstraintExecutor {
	public void executeConstraint(Connection connection, BusinessRule businessRule);
}
