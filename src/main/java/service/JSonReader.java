package service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.BusinessRuleType;
import domain.Column;
import domain.ConcreteOperator;
import domain.Equal;
import domain.IsNot;
import domain.Larger;
import domain.Operator;
import domain.Smaller;
import domain.Table;
import domain.Value;

public class JSonReader {
	private String jsonString;
	private BusinessRuleConstructor ruleConstructor;

	public JSonReader(String jsonString, BusinessRuleConstructor ruleConstructor) {
		this.jsonString = jsonString;
		this.ruleConstructor = ruleConstructor;
	}

	public void fillConstructor() {
		JSONObject jsonObj = new JSONObject(jsonString);

		String typeOfJSONConstraint = jsonObj.getString("typeOfConstraint");

		ruleConstructor.setTable(createTable(jsonObj.getString("tableName")));
		ruleConstructor.setColumn(createColumn(jsonObj.getString("columnName")));
		ruleConstructor.setTypeOfConstraint(createBusinessRuleType(typeOfJSONConstraint));

		// Legenda voor rules:
		// ACR: Attribute Compare Rule
		// ARR: Attribute Range Rule
		// ALR: Attribute List Rule
		// AOR: Attribute Other Rule
		// TCR: Tuple Compare Rule

		if (typeOfJSONConstraint.contains("ACR")) {
			fillAttributeCompareRule(jsonObj);
		} else if (typeOfJSONConstraint.contains("ARR")) {
			fillAttributeRangeRule(jsonObj);
		} else if (typeOfJSONConstraint.contains("ALR")) {
			fillAttributeListRule(jsonObj);
		} else if (typeOfJSONConstraint.contains("AOR")) {
			fillAttributeOtherRule(jsonObj);
		} else if (typeOfJSONConstraint.contains("TCR")) {
			fillTupleCompareRule(jsonObj);
		}

	}

	private void fillAttributeCompareRule(JSONObject jsonObj) {
		ruleConstructor.setMaxValue(createValue(jsonObj.getString("maxValue")));
		ruleConstructor.setCompareRule(createOperator(jsonObj.getString("compareRule")));

	}

	private void fillAttributeRangeRule(JSONObject jsonObj) {
		ruleConstructor.setMaxValue(createValue(jsonObj.getString("maxValue")));
		ruleConstructor.setMinValue(createValue(jsonObj.getString("minValue")));
	}

	private void fillAttributeListRule(JSONObject jsonObj) {
		ArrayList<Value> listOfValues = new ArrayList<Value>();

		JSONArray jArray = (JSONArray) jsonObj.get("listOfValues");
		if (jArray != null) {
			for (int i = 0; i < jArray.length(); i++) {
				listOfValues.add(createValue(jArray.getString(i)));
			}
		}

		ruleConstructor.setListOfValues(listOfValues);
	}

	private void fillAttributeOtherRule(JSONObject jsonObj) {
		ruleConstructor.setLetterLength(createValue(jsonObj.getString("letterLength")));
		ruleConstructor.setStartPosition(createValue(jsonObj.getString("startPosition")));
		ruleConstructor.setMinValue(createValue(jsonObj.getString("minValue")));
		ruleConstructor.setMaxValue(createValue(jsonObj.getString("maxValue")));
	}

	private void fillTupleCompareRule(JSONObject jsonObj) {
		ruleConstructor.setSecondColumn(createColumn(jsonObj.getString("secondColumn")));
		ruleConstructor.setCompareRule(createOperator(jsonObj.getString("compareRule")));
	}

	private Table createTable(String tableName) {
		Table newTable = new Table();
		newTable.setName(tableName);
		return newTable;
	}

	private Column createColumn(String columnName) {
		Column newColumn = new Column();
		newColumn.setName(columnName);
		return newColumn;
	}

	private Value createValue(String value) {
		Value newValue = new Value();
		newValue.setGiven(value);
		return newValue;
	}

	private Operator createOperator(String operator) {
		Operator newOperator = new ConcreteOperator();
		if (operator.equals("!=")) {
			newOperator = (new Equal(new IsNot(new ConcreteOperator())));
		}
		if (operator.equals("=")) {
			newOperator = (new Equal(new ConcreteOperator()));
		}
		if (operator.equals(">")) {
			newOperator = (new Larger(new ConcreteOperator()));
		}
		if (operator.equals("<")) {
			newOperator = (new Smaller(new ConcreteOperator()));
		}
		if (operator.equals("<=")) {
			newOperator = (new Equal(new Smaller(new ConcreteOperator())));
		}
		if (operator.equals(">=")) {
			newOperator = (new Equal(new Larger(new ConcreteOperator())));
		}
		
		return newOperator;
	}
	
	private BusinessRuleType createBusinessRuleType(String type) {
		BusinessRuleType newBusinessRuleType = new BusinessRuleType();
		newBusinessRuleType.setId(type);
		return newBusinessRuleType;
	}
}
