package service.trigger;

import domain.BusinessRule;

public class EntityOtherRuleGenerator implements TriggerGenerator{

	@Override
	public String generateTrigger(BusinessRule b) {
		String s="CREATE OR REPLACE TRIGGER eor_trigger \r\n" + 
				"BEFORE INSERT OR UPDATE ON "+b.getDeTables().get(0)+"r\n" + 
				"FOR EACH ROW\r\n" + 
				"ENABLE\r\n" + 
				"\r\n" + 
				"DECLARE\r\n" + 
				" l_aantal pls_integer;\r\n" + 
				" l_passed BOOLEAN;\r\n" + 
				"BEGIN\r\n" + 
				" select count(*)\r\n" + 
				" into l_aantal\r\n" + 
				" from "+b.getDeTables().get(0)+" \r\n" + 
				" where "+b.getDeTables()+"."+b.getDeColumns().get(0)+" = :NEW."+b.getDeColumns().get(0)+";\r\n" + 
				" l_passed := l_aantal <= "+b.getDeValues().get(0)+";\r\n" + 
				"     IF l_passed then\r\n" + 
				"        DBMS_OUTPUT.PUT_LINE('succesvol'); \r\n" + 
				"    ELSE\r\n" + 
				"        DBMS_OUTPUT.PUT_LINE('error range exceeded'); \r\n" + 
				"    END IF;\r\n" + 
				" \r\n" + 
				" END;\r\n" + 
				" /";
		return s;
	}
	
	

}
