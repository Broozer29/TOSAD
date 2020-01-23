package service;

import domain.BusinessRule;
import domain.Value;

public interface SQLGenerator {
	public Value generateCode(BusinessRule businessRule);
	public Value generateTriggerCode(BusinessRule businessRule);
}
