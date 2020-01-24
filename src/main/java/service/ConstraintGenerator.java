package service;

import domain.BusinessRule;
import domain.Value;

public interface ConstraintGenerator {
	public String generateCode(BusinessRule businessRule);
}
