package domain;

import java.util.ArrayList;

public class BusinessRuleImpl implements BusinessRule {
	private Value minValue;
	private Value maxValue;
	private Value compareRule;
	private Value id;
	private Table table;
	private Column column;
	private Column secondColumn;
	private BusinessRuleType businessRuleType;
	private Value startPosition;
	private Value letterLength;
	private ArrayList<Value> listOfValues;
	private Value code = null;
	
	private Value triggerCode = null;
	private String triggerNaam = null;
	

	public void setID(Value idValue) {
		this.id = idValue;
	}

	public void setMinValue(Value minValue) {
		this.minValue = minValue;
	}

	public void setMaxValue(Value maxValue) {
		this.maxValue = maxValue;
	}

	public void setCompareRule(Value compareRule) {
		this.compareRule = compareRule;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public void setSecondColumn(Column column) {
		this.secondColumn = column;
	}

	public void setTypeOfConstraint(BusinessRuleType businessRuleType) {
		this.businessRuleType = businessRuleType;
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

	public void setTriggerNaam(String triggerNaam) {
		this.triggerNaam = triggerNaam;
	}
	
	public void setCode(Value code) {
		this.code = code;
	}
	
	public void setTriggerCode(Value code) {
		this.triggerCode = code;
	}

	public Value getCode() {
		return this.code;
	}

	public Value getTriggerCode() {
		return this.triggerCode;
	}

	public Value getID() {
		return this.id;
	}

	public Value getMinValue() {
		return this.minValue;
	}

	public Value getMaxValue() {
		return this.maxValue;
	}

	public Value getCompareRule() {
		return this.compareRule;
	}

	public Table getTable() {
		return this.table;
	}

	public Column getColumn() {
		return this.column;
	}

	public Column getSecondColumn() {
		return this.secondColumn;
	}

	public BusinessRuleType getTypeOfConstraint() {
		return this.businessRuleType;
	}

	public Value getStartPosition() {
		return this.startPosition;
	}

	public Value getLetterLength() {
		return this.letterLength;
	}

	public ArrayList<Value> getListOfValues() {
		return this.listOfValues;
	}

	public String getTriggerNaam() {
		return this.triggerNaam;
	}

}
