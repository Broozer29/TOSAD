package service;

import java.util.ArrayList;

import domain.BusinessRuleType;
import domain.Column;
import domain.Operator;
import domain.Table;
import domain.Value;
import domain.businessrules.AttributeCompareRule;
import domain.businessrules.AttributeListRule;
import domain.businessrules.AttributeOtherRule;
import domain.businessrules.AttributeRangeRule;
import domain.businessrules.BusinessRule;
import domain.businessrules.TupleCompareRule;

public class BusinessRuleConstructor {
	private String recievedData;

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
		BusinessRule newBusinessRule = null;
		if (typeOfConstraint.getId().equals("ACR")) {
			newBusinessRule = new AttributeCompareRule();
			newBusinessRule.setCompareRule(compareRule);
			newBusinessRule.setMaxValue(maxValue);
		}
		
		if (typeOfConstraint.getId().equals("ARR")) {
			newBusinessRule = new AttributeRangeRule();
			newBusinessRule.setMinValue(minValue);
			newBusinessRule.setMaxValue(maxValue);
		}
		
		if (typeOfConstraint.getId().equals("ALR")) {
			newBusinessRule = new AttributeListRule();
			newBusinessRule.setListOfValues(listOfValues);
		}
		
		if (typeOfConstraint.getId().equals("TCR")) {
			newBusinessRule = new TupleCompareRule();
			newBusinessRule.setCompareRule(compareRule);
			newBusinessRule.setSecondColumn(secondColumn);
		}
		
		if (typeOfConstraint.getId().equals("AOR")) {
			newBusinessRule = new AttributeOtherRule();
			newBusinessRule.setLetterLength(letterLength);
			newBusinessRule.setStartPosition(startPosition);
			newBusinessRule.setMinValue(minValue);
			newBusinessRule.setMaxValue(maxValue);
		}
		newBusinessRule.setTable(table);
		newBusinessRule.setColumn(column);
		newBusinessRule.setTypeOfConstraint(typeOfConstraint);
		return newBusinessRule;
	}

	public String getRecievedData() {
		return recievedData;
	}

	public void setRecievedData(String recievedData) {
		this.recievedData = recievedData;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public BusinessRuleType getTypeOfConstraint() {
		return typeOfConstraint;
	}

	public void setTypeOfConstraint(BusinessRuleType typeOfConstraint) {
		this.typeOfConstraint = typeOfConstraint;
	}

	public Value getMinValue() {
		return minValue;
	}

	public void setMinValue(Value minValue) {
		this.minValue = minValue;
	}

	public Value getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Value maxValue) {
		this.maxValue = maxValue;
	}

	public Operator getCompareRule() {
		return compareRule;
	}

	public void setCompareRule(Operator compareRule) {
		this.compareRule = compareRule;
	}

	public Column getSecondColumn() {
		return secondColumn;
	}

	public void setSecondColumn(Column secondColumn) {
		this.secondColumn = secondColumn;
	}

	public Value getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Value startPosition) {
		this.startPosition = startPosition;
	}

	public Value getLetterLength() {
		return letterLength;
	}

	public void setLetterLength(Value letterLength) {
		this.letterLength = letterLength;
	}

	public ArrayList<Value> getListOfValues() {
		return listOfValues;
	}

	public void setListOfValues(ArrayList<Value> listOfValues) {
		this.listOfValues = listOfValues;
	}
	
}
