package service;

import java.util.ArrayList;
import java.util.List;

import domain.BusinessRule;
import domain.BusinessRuleImpl;
import domain.BusinessRuleType;
import domain.Column;
import domain.Table;
import domain.Value;

public class BusinessRuleConstructor {
	private String recievedData;

	private Table table;
	private Column column;
	private BusinessRuleType typeOfConstraint;
	private Value minValue;
	private Value maxValue;

	private Value compareRule;
	private Column secondColumn;

	private Value startPosition;
	private Value letterLength;
	private Value id;
	private Value example;

	private ArrayList<Value> listOfValues;

	public BusinessRuleConstructor() {
	}

	// Legenda voor rules:
	// ACR: Attribute Compare Rule
	// ARR: Attribute Range Rule
	// ALR: Attribute List Rule
	// AOR: Attribute Other Rule
	// TCR: Tuple Compare Rule

	public BusinessRule createBusinessRule() {
		BusinessRule newBusinessRule = new BusinessRuleImpl();
		if (typeOfConstraint.getId().equals("ACR")) {
			newBusinessRule.setCompareRule(compareRule);
			newBusinessRule.setMaxValue(maxValue);
		}

		if (typeOfConstraint.getId().equals("ARR")) {
			newBusinessRule.setMinValue(minValue);
			newBusinessRule.setMaxValue(maxValue);
		}

		if (typeOfConstraint.getId().equals("ALR")) {
			newBusinessRule.setListOfValues(listOfValues);
		}

		if (typeOfConstraint.getId().equals("TCR")) {
			newBusinessRule.setCompareRule(compareRule);
			newBusinessRule.setSecondColumn(secondColumn);
		}

		if (typeOfConstraint.getId().equals("AOR")) {
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

	public void setTable(List<Table> list) {
		this.table = list.get(0);
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(List<Column> list) {
		this.column = list.get(0);
		if (list.size() > 1) {
			this.secondColumn = list.get(1);
		}
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

	public Value getCompareRule() {
		return compareRule;
	}

	public void setCompareRule(Value compareRule) {
		this.compareRule = compareRule;
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

	public Value getId() {
		return id;
	}

	public void setId(int id) {
		this.id = changeToValue(Integer.toString(id));
	}

	public Value getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = changeToValue(example);
	}

	private Value changeToValue(String valueData) {
		Value newValue = new Value();
		newValue.setGiven(valueData);
		return newValue;
	}

}
