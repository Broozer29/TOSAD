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

	private String compareRule;
	private String secondColumn;

	private String startPosition;
	private String letterLength;

	private ArrayList<String> listOfValues;

	public String getCode() {
		return this.code;
	}

	public BusinessRule(String table, String column, String typeOfConstraint) {
		this.table = table;
		this.column = column;
		this.typeOfConstraint = typeOfConstraint;
	}

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

	public String getCompareRule() {
		return compareRule;
	}

	public void setCompareRule(String compareRule) {
		this.compareRule = compareRule;
	}

	public String getSecondColumn() {
		return secondColumn;
	}

	public void setSecondColumn(String secondColumn) {
		this.secondColumn = secondColumn;
	}

	public String getLetterLength() {
		return letterLength;
	}

	public void setLetterLength(String letterLength) {
		this.letterLength = letterLength;
	}

	public String getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}

	// Legenda voor rules:
	// ACR: Attribute Compare Rule
	// ARR: Attribute Range Rule
	// ALR: Attribute List Rule
	// AOR: Attribute Other Rule
	// TCR: Tuple Compare Rule

	public void generateBusinessRule() {
		switch (typeOfConstraint.toUpperCase()) {
		case "NOT NULL":
			generateNotNullConstraint();
			break;
		case "ACR":
			generateAttributeCompareRuleMinvalue();
			break;
		case "ARR":
			generateAttributeRangeRule();
			break;
		case "ALR":
			generateAttributeListRule();
			break;
		case "TCR":
			generateTupleCompareRule();
			break;
		case "AOR":
			generateAttributeOtherRule();
			break;
		}

	}

	private void generateNotNullConstraint() {
		this.code = "ALTER TABLE " + this.table + " MODIFY " + this.column + " NOT NULL;";
	}

	private void generateAttributeCompareRuleMinvalue() {
		String valueToUse = "";

		if (compareRule.equals("=") || compareRule.equals("<") || compareRule.equals("<=")) {
			valueToUse = this.maxValue;
		}
		if (compareRule.equals(">") || compareRule.equals(">=")) {
			valueToUse = this.minValue;
		}
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + " " + this.compareRule + " "
				+ valueToUse + ");";
	}

	private void generateAttributeRangeRule() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + " between " + this.minValue + " and "
				+ this.maxValue + ");";
	}

	private void generateAttributeListRule() {
		convertListOfValues();
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + " status in " + "(" + this.listOfValues + ")" + ");";
		this.code = this.code.replace("[", "");
		this.code = this.code.replace("]", "");
	}

	private void convertListOfValues() {
		ArrayList<String> newList = new ArrayList<String>();
		for (String str : listOfValues) {
			String newString = "'" + str + "'";
			newList.add(newString);
		}
		this.listOfValues = newList;
	}

	private void generateTupleCompareRule() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + this.compareRule + this.secondColumn
				+ ")" + ");";
	}

	private void generateAttributeOtherRule() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + "substr(" + this.column + ", " + this.startPosition
				+ " ," + this.letterLength + ")" + " between (" + "'" + this.minValue + "' and " + "'" + this.maxValue
				+ "')" + ");";
	}
}
