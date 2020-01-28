package persistence.tool.trigger;

import domain.BusinessRule;

public interface TriggerGenerator {
	
	public String generateTrigger(BusinessRule b);

}
