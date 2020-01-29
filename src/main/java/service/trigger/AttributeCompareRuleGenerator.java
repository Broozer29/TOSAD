package service.trigger;

import domain.BusinessRule;
import domain.Value;

public class AttributeCompareRuleGenerator implements TriggerGenerator{
	

	private String name;
	private String table;
	private String column;
	private String operator;
	private String value;
	
	

	@Override
	public String generateTrigger(BusinessRule b) {
		
		name=b.getNaam();
		table=b.getDeTables().get(0).getName();
		column=b.getDeColumns().get(0).getName();
		for(Value v : b.getDeValues()) {
			if(v.getDataType().equals("operator")){
				operator=v.getGiven();
			}
			else if(v.getDataType().equals("value")){
				value=v.getGiven();
			}
		}
		String strQuery = "CREATE OR REPLACE TRIGGER "+name+"_TRIGGER\r\n" + 
				"BEFORE INSERT OR UPDATE ON "+table+"\r\n" + 
				"FOR EACH ROW\r\n" + 
				"ENABLE\r\n" + 
				"\r\n" + 
				"DECLARE\r\n" + 
				"    l_passed BOOLEAN;\r\n" + 
				"BEGIN\r\n" + 
				"  l_passed := :NEW."+column+" "+operator+" "+value+";\r\n" + 
				"  IF l_passed THEN\r\n" + 
				"    DBMS_OUTPUT.PUT_LINE('SUCCESVOL');\r\n" + 
				"  ELSE\r\n" + 
				"    DBMS_OUTPUT.PUT_LINE('VOLDOET NIET AAN TRIGGER');\r\n" + 
				"    raise_application_error(-20000, 'voldoet niet aan trigger voorwaarden');\r\n" + 
				"  END IF;\r\n" + 
				"END;";

		return strQuery;
	}

}
