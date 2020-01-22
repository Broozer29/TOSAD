package domain.businessrules;

import java.util.ArrayList;

import domain.BusinessRuleType;
import domain.Column;
import domain.Table;
import domain.Value;

public interface BusinessRule {
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
	
	public String getCode();
	public String getTriggerCode();
}
