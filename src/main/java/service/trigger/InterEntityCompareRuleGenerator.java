package service.trigger;

import domain.BusinessRule;
import domain.Column;
import domain.Value;

public class InterEntityCompareRuleGenerator implements TriggerGenerator {

	private String table1;
	private String table2;
	private String column1;
	private String column2;
	private String column1Key;
	private String column2Key;
	private String operator1;
	private String operator2;
	private String name;
	private String message1 = "Message1";
	private String message2 = "Message2";

	@Override
	public String generateTrigger(BusinessRule b) {

		name = b.getNaam();
		table1 = b.getDeTables().get(0).getName();
		table2 = b.getDeTables().get(1).getName();
		for (Column c : b.getDeColumns()) {
			if (c.getDataType().equals("column1")) {
				column1 = c.getName();
			} else if (c.getDataType().equals("column2")) {
				column2 = c.getName();
			} else if (c.getDataType().equals("column1Key")) {
				column1Key = c.getName();
			} else if (c.getDataType().equals("column2Key")) {
				column2Key = c.getName();
			}
		}
		for (Value v : b.getDeValues()) {
			if (v.getDataType().equals("operator1")) {
				operator1 = v.getGiven();
			} else if (v.getDataType().equals("operator2")) {
				operator2 = v.getGiven();
			}
		}

		String strQuery = " CREATE OR REPLACE TRIGGER " + name + "_1_trigger \r\n" + "BEFORE INSERT OR UPDATE ON "
				+ table1 + "\r\n" + "FOR EACH ROW\r\n" + "ENABLE\r\n" + "\r\n" + "DECLARE\r\n"
				+ "    l_passed BOOLEAN;\r\n" + "    l_column " + table2 + "." + column2 + "%type;\r\n" + "    \r\n"
				+ "    cursor lc_1 IS \r\n" + "        SELECT " + column2 + " \r\n" + "        FROM " + table2 + " \r\n"
				+ "        WHERE " + table2 + "." + column2Key + " = :NEW." + column1Key + ";\r\n" + "    \r\n"
				+ "BEGIN\r\n" + "    open lc_1;\r\n" + "    fetch lc_1 into l_column;\r\n" + "    close lc_1;\r\n"
				+ "    l_passed:=  :NEW." + column1 + " " + operator1 + " l_column;\r\n" + "    IF l_passed then\r\n"
				+ "        DBMS_OUTPUT.PUT_LINE('succesvol'); \r\n" + "    ELSE\r\n"
				+ "         raise_application_error(-20000\r\n" + "                , '" + message1 + "');\r\n"
				+ "    END IF;\r\n" + "END;\r\n" + "/\r\n" + " CREATE OR REPLACE TRIGGER " + name + "_2_trigger \r\n"
				+ "BEFORE INSERT OR UPDATE ON " + table2 + "\r\n" + "FOR EACH ROW\r\n" + "ENABLE\r\n" + "\r\n"
				+ "DECLARE\r\n" + "    l_passed BOOLEAN;\r\n" + "    l_column " + table1 + "." + column1 + "%type;\r\n"
				+ "    \r\n" + "    cursor lc_2 IS \r\n" + "        SELECT " + column1 + " \r\n" + "        FROM "
				+ table1 + "\r\n" + "        WHERE " + table1 + "." + column1Key + " = :NEW." + column2Key + ";\r\n"
				+ "    \r\n" + "BEGIN\r\n" + "    open lc_2;\r\n" + "    fetch lc_2 into l_begindatum;\r\n"
				+ "    close lc_2;\r\n" + "    l_passed:=  :NEW." + column2 + " " + operator2 + " l_column;\r\n"
				+ "    IF l_passed then\r\n" + "        DBMS_OUTPUT.PUT_LINE('succesvol'); \r\n" + "    ELSE\r\n"
				+ "         raise_application_error(-20000\r\n" + "                , '" + message2 + "');\r\n"
				+ "    END IF;\r\n" + "END;\r\n" + "/";

		return strQuery;
	}

}
