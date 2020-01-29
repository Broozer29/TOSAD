package persistence.target;

import java.sql.Connection;
import java.sql.SQLException;

import domain.BusinessRule;

public interface ConstraintExecutor {
	public void executeConstraint(Connection connection, BusinessRule businessRule) throws SQLException;
}
