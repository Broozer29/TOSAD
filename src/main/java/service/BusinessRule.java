package service;

import java.util.ArrayList;

import domain.BusinessRuleType;
import domain.Column;
import domain.Operator;
import domain.Table;
import domain.Value;

public class BusinessRule {
	private String code;
	private Table table;
	private Column column;
	private BusinessRuleType typeOfConstraint;
	private Value minValue;
	private Value maxValue;

	private Operator compareRule;
	private Column secondColumn;

	private Value startPosition;
	private Value letterLength;

	private ArrayList<Value> listOfValues;

	public BusinessRule(Table table, Column column, BusinessRuleType typeOfConstraint) {
		this.table = table;
		this.column = column;
		this.typeOfConstraint = typeOfConstraint;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public void setTypeOfConstraint(BusinessRuleType typeOfConstraint) {
		this.typeOfConstraint = typeOfConstraint;
	}

	public void setMinValue(Value minValue) {
		this.minValue = minValue;
	}

	public void setMaxValue(Value maxValue) {
		this.maxValue = maxValue;
	}

	public void setCompareRule(Operator compareRule) {
		this.compareRule = compareRule;
	}

	public void setSecondColumn(Column secondColumn) {
		this.secondColumn = secondColumn;
	}

	public void setStartPosition(Value startPosition) {
		this.startPosition = startPosition;
	}

	public void setLetterLength(Value letterLength) {
		this.letterLength = letterLength;
	}

	public void setListOfValues(ArrayList<Value> listOfValues) {
		this.listOfValues = listOfValues;
	}
	
	// Legenda voor rules:
	// ACR: Attribute Compare Rule
	// ARR: Attribute Range Rule
	// ALR: Attribute List Rule
	// AOR: Attribute Other Rule
	// TCR: Tuple Compare Rule

	public void generateBusinessRule() {
		switch (typeOfConstraint.getId().toUpperCase()) {
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
		this.code = "ALTER TABLE " + this.table.getName() + " MODIFY " + this.column.getName() + " NOT NULL;";
	}

	private void generateAttributeCompareRuleMinvalue() {
		String valueToUse = "";
		if (compareRule.getCode().equals("=") || compareRule.getCode().equals("<") || compareRule.getCode().equals("<=")) {
			valueToUse = this.maxValue.getGiven();
		}
		if (compareRule.getCode().equals(">") || compareRule.getCode().equals(">=")) {
			valueToUse = this.minValue.getGiven();
		}
		this.code = "ALTER TABLE " + this.table.getName() + " ADD CHECK (" + this.column.getName() + " " + this.compareRule.getCode() + " "
				+ valueToUse + ");";
	}

	private void generateAttributeRangeRule() {
		this.code = "ALTER TABLE " + this.table.getName() + " ADD CHECK (" + this.column.getName() + " between " + this.minValue.getGiven() + " and "
				+ this.maxValue.getGiven() + ");";
	}

	private void generateAttributeListRule() {
		ArrayList<String> stringValueList = convertListOfValues();
		this.code = "ALTER TABLE " + this.table.getName() + " ADD CHECK (" + " status in " + "(" + stringValueList + ")" + ");";
		this.code = this.code.replace("[", "");
		this.code = this.code.replace("]", "");
	}

	private ArrayList<String> convertListOfValues() {
		ArrayList<String> newList = new ArrayList<String>();
		for (Value value : listOfValues) {
			String newString = "'" + value.getGiven() + "'";
			newList.add(newString);
		}
		return newList;
	}

	private void generateTupleCompareRule() {
		System.out.println(this.compareRule);
		this.code = "ALTER TABLE " + this.table.getName() + " ADD CHECK (" + this.column.getName() + this.compareRule.getCode() + this.secondColumn.getName()
				+ ");";
	}

	private void generateAttributeOtherRule() {
		this.code = "ALTER TABLE " + this.table.getName() + " ADD CHECK (" + "substr(" + this.column.getName() + ", " + this.startPosition.getGiven()
				+ " ," + this.letterLength.getGiven() + ")" + " between (" + "'" + this.minValue.getGiven() + "' and " + "'" + this.maxValue.getGiven()
				+ "')" + ");";
	}
}
