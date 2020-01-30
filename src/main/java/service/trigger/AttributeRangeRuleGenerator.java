package service.trigger;

import domain.BusinessRule;
import domain.Value;

public class AttributeRangeRuleGenerator implements TriggerGenerator {

	private String name;
	private String table;
	private String column;
	private String minValue;
	private String maxValue;
	private String operator;

	@Override
	public String generateTrigger(BusinessRule b) {
		name = b.getNaam();
		table = b.getDeTables().get(0).getName();
		column = b.getDeColumns().get(0).getName();
		for (Value v : b.getDeValues()) {
			if (v.getDataType().equals("operator")) {
				operator = v.getGiven();
			} else if (v.getDataType().equals("minValue")) {
				minValue = v.getGiven();
			} else if (v.getDataType().equals("maxValue")) {
				maxValue = v.getGiven();
			}

		}

		String s = "CREATE OR REPLACE TRIGGER " + name + "_trigger\r\n" + "BEFORE INSERT OR UPDATE ON " + table + "\r\n"
				+ "FOR EACH ROW\r\n" + "ENABLE\r\n" + "\r\n" + "DECLARE\r\n" + "    l_passed BOOLEAN;\r\n" + "BEGIN\r\n"
				+ "    l_passed:= :NEW." + column + " " + operator + " " + minValue + " AND " + maxValue + ";\r\n"
				+ "    IF l_passed then\r\n" + "        DBMS_OUTPUT.PUT_LINE('succesvol'); \r\n" + "    ELSE\r\n"
				+ "    raise_application_error(-20000, 'voldoet niet aan trigger voorwaarden');\r\n" + "    END IF;\r\n"
				+ "END;\r";

		return s;
	}

};