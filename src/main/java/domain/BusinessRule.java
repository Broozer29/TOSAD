package domain;

import java.util.ArrayList;

import service.BusinessRuleConstructor;

public interface BusinessRule {
	public void setID(Value idValue);
	
	public void setMinValue(Value minValue);
	public void setMaxValue(Value maxValue);
	public void setCompareRule(Value compareRule);
	public void setTable(Table table);
	public void setColumn(Column column);
	public void setSecondColumn(Column column);
	public void setTypeOfConstraint(BusinessRuleType businessRuleType);
	public void setStartPosition(Value startPosition);
	public void setLetterLength(Value letterLength);
	public void setListOfValues(ArrayList<Value> listOfValues);
	public void setTriggerNaam(String triggerNaam);
	public void setCode(Value value);
	public void setTriggerCode(Value code);
	
	public Value getCode();
	public Value getTriggerCode();

	public Value getID();
	public Value getMinValue();
	public Value getMaxValue();
	public Value getCompareRule();
	public Table getTable();
	public Column getColumn();
	public Column getSecondColumn();
	public BusinessRuleType getTypeOfConstraint();
	public Value getStartPosition();
	public Value getLetterLength();
	public ArrayList<Value> getListOfValues();
	public String getTriggerNaam();
}
