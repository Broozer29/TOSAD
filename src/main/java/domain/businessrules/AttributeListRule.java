package domain.businessrules;

import java.util.ArrayList;

import domain.BusinessRuleType;
import domain.Column;
import domain.Operator;
import domain.Table;
import domain.Value;

public class AttributeListRule implements BusinessRule {
	private Value minValue;
	private Value maxValue;
	private Operator compareRule;
	private Table table;
	private Column column;
	private Column secondColumn;
	private BusinessRuleType businessRuleType;
	private Value startPosition;
	private Value letterLength;
	private ArrayList<Value> listOfValues;
	private String code = null;
	
	private String triggerCode = null;
	private String createTrigger = null;
	private String triggerNaam = null;
	
	
	public void setMinValue(Value minValue) {
		this.minValue = minValue;
	}

	public void setMaxValue(Value maxValue) {
		this.maxValue = maxValue;
	}

	public void setCompareRule(Operator compareRule) {
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

	public String getCode() {
		if(this.code == null) {
			generateCode();
		}
		return this.code;
	}
	
	private void generateCode() {
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
	
	public String getTriggerCode() {
		return null;
	}

	@Override
	public void setTriggerNaam(String triggerNaam) {
		// TODO Auto-generated method stub
		
	}
}