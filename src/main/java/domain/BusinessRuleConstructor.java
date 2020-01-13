package domain;

import java.util.ArrayList;

public class BusinessRuleConstructor {
	private String recievedData;

	private String typeOfConstraint;
	private String tableName;
	private String columnName;
	
	private String minValue;
	private String maxValue;
	
	private String compareRule;
	private String secondColumn;
	
	private String letterLength;
	private String startPosition;
	
	private ArrayList<String> listOfValues;

	public BusinessRuleConstructor(String recievedData) {
		this.recievedData = recievedData;
	}
	
	// Legenda voor rules: 
	// ACR: Attribute Compare Rule 
	// ARR: Attribute Range Rule
	// ALR: Attribute List Rule 
	// AOR: Attribute Other Rule 
	// TCR: Tuple Compare Rule

	public BusinessRule createBusinessRule() {
		BusinessRule newBusinessRule = new BusinessRule(tableName, columnName, typeOfConstraint);
		
		if (typeOfConstraint.equals("ACR")) {
			newBusinessRule.setCompareRule(compareRule);
			if (compareRule.equals("<") || compareRule.equals("<=") || compareRule.equals("=")) {
				newBusinessRule.setMaxValue(maxValue);
			}
			if (compareRule.equals(">") || compareRule.equals(">=")) {
				newBusinessRule.setMinValue(minValue);
			}
		}
		
		if (typeOfConstraint.equals("ARR")) {
			newBusinessRule.setMinValue(minValue);
			newBusinessRule.setMaxValue(maxValue);
		}
		
		if (typeOfConstraint.equals("ALR")) {
			newBusinessRule.setListOfValues(listOfValues);
		}
		
		if (typeOfConstraint.equals("TCR")) {
			newBusinessRule.setCompareRule(compareRule);
			newBusinessRule.setSecondColumn(secondColumn);
		}
		
		if (typeOfConstraint.equals("AOR")) {
			newBusinessRule.setLetterLength(letterLength);
			newBusinessRule.setStartPosition(startPosition);
			newBusinessRule.setMinValue(minValue);
			newBusinessRule.setMaxValue(maxValue);
		}
		
		return newBusinessRule;
	}

	public String getRecievedData() {
		return recievedData;
	}

	public void setRecievedData(String recievedData) {
		this.recievedData = recievedData;
	}

	public String getTypeOfConstraint() {
		return typeOfConstraint;
	}

	public void setTypeOfConstraint(String typeOfConstraint) {
		this.typeOfConstraint = typeOfConstraint;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
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
	

	public ArrayList<String> getListOfValues() {
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
}
