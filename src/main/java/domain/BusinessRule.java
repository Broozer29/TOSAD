package domain;

import java.util.ArrayList;
import java.util.List;

public class BusinessRule {
	private String code;
	private String table;
	private String column;
	private String typeOfConstraint;
	private String minValue;
	private String maxValue;
	
	private ArrayList<String> listOfValues;

	public String getCode() {
		return this.code;
	}

	public BusinessRule(String table, String column, String typeOfConstraint) {
		this.table = table;
		this.column = column;
		this.typeOfConstraint = typeOfConstraint;
	}
	

// Legenda voor rules: 
// ACR: Attribute Compare Rule 
// ARR: Attribute Range Rule
// ALR: Attribute List Rule 
// AOR: Attribute Other Rule 
// TCR: Tuple Compare Rule

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	
	public List<String> getListOfValues() {
		return listOfValues;
	}

	public void setListOfValues(ArrayList<String> listOfValues) {
		this.listOfValues = listOfValues;
	}

	public void generateBusinessRule() {
		switch (typeOfConstraint.toUpperCase()) {
		case "NOT NULL":
			generateNotNullConstraint();
			break;
		case "ACR>":
			generateAttributeCompareRuleLargerThan();
			break;
		case "ACR<":
			generateAttributeCompareRuleSmallerThan();
			break;
		case "ACR>=":
			generateAttributeCompareRuleLargerInclusive();
			break;
		case "ACR<=":
			generateAttributeCompareRuleSmallerThanInclusive();
			break;
		case "ACR=":
			generateAttributeCompareRuleEqual();
			break;
		case "ARR":
			generateAttributeRangeRule();
			break;
		case "ALR":
			generateAttributeListRule();
			break;
		}

	}

	private void generateNotNullConstraint() {
		this.code = "ALTER TABLE " + this.table + " MODIFY " + this.column + " NOT NULL;";
	}

	private void generateAttributeCompareRuleLargerThan() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + ">" + this.minValue + ");";
	}

	private void generateAttributeCompareRuleLargerInclusive() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + ">=" + this.minValue + ");";
	}

	private void generateAttributeCompareRuleSmallerThan() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + "<" + this.maxValue + ");";
	}

	private void generateAttributeCompareRuleSmallerThanInclusive() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + "<=" + this.maxValue + ");";
	}

	private void generateAttributeCompareRuleEqual() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + "=" + this.maxValue + ");";
	}
	
	private void generateAttributeRangeRule() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + " between " + this.minValue + " and " + this.maxValue + ");"; 
	}
	
	private void generateAttributeListRule() {
		convertListOfValues();
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + " status in " + "(" + this.listOfValues + ")" + ");";
		this.code = this.code.replace("[", "");
		this.code = this.code.replace("]", "");
	}
	
	private void convertListOfValues(){
		ArrayList<String> newList = new ArrayList<String>();
		for (String str : listOfValues) {
			String newString = "'" + str + "'";
			newList.add(newString);
		}
		
		this.listOfValues = newList;
	}

}
