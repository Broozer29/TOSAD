package domain;

import java.util.ArrayList;

import org.json.*;

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
		
		ruleConstructor.setTableName(jsonObj.getString("tableName"));
		ruleConstructor.setColumnName(jsonObj.getString("columnName"));
		ruleConstructor.setTypeOfConstraint(typeOfJSONConstraint);

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
		if (jsonObj.getString("typeOfConstraint").equals("ACR")) {
			if (jsonObj.getString("compareRule").equals("<") || jsonObj.getString("compareRule").equals("=<")) {
				ruleConstructor.setMaxValue(jsonObj.getString("maxValue"));
			}
			if (jsonObj.getString("compareRule").equals(">") || jsonObj.getString("compareRule").equals(">="))
				ruleConstructor.setMinValue(jsonObj.getString("minValue"));

			if (jsonObj.getString("compareRule").equals("=")) {
				ruleConstructor.setMaxValue(jsonObj.getString("maxValue"));
			}

		}
		ruleConstructor.setCompareRule(jsonObj.getString("compareRule"));


	}

	private void fillAttributeRangeRule(JSONObject jsonObj) {
		ruleConstructor.setMaxValue(jsonObj.getString("maxValue"));
		ruleConstructor.setMinValue(jsonObj.getString("minValue"));
	}

	private void fillAttributeListRule(JSONObject jsonObj) {
		ArrayList<String> listOfValues = new ArrayList<String>();

		JSONArray jArray = (JSONArray) jsonObj.get("listOfValues");
		if (jArray != null) {
			for (int i = 0; i < jArray.length(); i++) {
				listOfValues.add(jArray.getString(i));
			}
		}

		ruleConstructor.setListOfValues(listOfValues);
	}

	private void fillAttributeOtherRule(JSONObject jsonObj) {
		ruleConstructor.setLetterLength(jsonObj.getString("letterLength"));
		ruleConstructor.setStartPosition(jsonObj.getString("startPosition"));
		ruleConstructor.setMinValue(jsonObj.getString("minValue"));
		ruleConstructor.setMaxValue(jsonObj.getString("maxValue"));
	}

	private void fillTupleCompareRule(JSONObject jsonObj) {
		ruleConstructor.setSecondColumn(jsonObj.getString("secondColumn"));
		ruleConstructor.setCompareRule(jsonObj.getString("compareRule"));
	}
}
