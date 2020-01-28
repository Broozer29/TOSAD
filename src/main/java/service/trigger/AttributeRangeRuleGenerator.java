package service.trigger;

import java.util.ArrayList;

import domain.BusinessRule;
import domain.Value;

public class AttributeRangeRuleGenerator implements TriggerGenerator {

	@Override
	public String generateTrigger(BusinessRule b) {
		ArrayList<Value> valueList = new ArrayList<Value>();
		valueList = (ArrayList<Value>) b.getDeValues();
		for (Value value : valueList) {
			System.out.println(value.getGiven() + " met datatype : " + value.getDataType());
		}
		
		String s = "CREATE OR REPLACE TRIGGER "+b.getNaam()+"_trigger\r\n" + 
				"BEFORE INSERT OR UPDATE ON "+b.getDeTables().get(0).getName()+"\r\n" + 
				"FOR EACH ROW\r\n" + 
				"ENABLE\r\n" + 
				"\r\n" + 
				"DECLARE\r\n" + 
				"    l_passed BOOLEAN;\r\n" + 
				"BEGIN\r\n" + 
				"    l_passed:= :NEW."+b.getDeColumns().get(0).getName()+" "+b.getDeValues().get(2).getGiven()+" "+b.getDeValues().get(0).getGiven()+" AND "+b.getDeValues().get(1).getGiven()+";\r\n" + 
				"    IF l_passed then\r\n" + 
				"        DBMS_OUTPUT.PUT_LINE('succesvol'); \r\n" + 
				"    ELSE\r\n" + 
				"        DBMS_OUTPUT.PUT_LINE('error range exceeded'); \r\n" + 
				"    END IF;\r\n" + 
				"END;\r\n" + 
				"/";

		return s;
	}

}
;