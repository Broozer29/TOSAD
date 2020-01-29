package service.trigger;

import java.sql.PreparedStatement;

import domain.BusinessRule;

public interface TriggerGenerator {
	
	public String generateTrigger(BusinessRule b);

}
