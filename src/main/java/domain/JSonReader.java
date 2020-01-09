package domain;

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
		
		// Legenda voor rules: 
		// ACR: Attribute Compare Rule 
		// ARR: Attribute Range Rule
		// ALR: Attribute List Rule 
		// AOR: Attribute Other Rule 
		// TCR: Tuple Compare Rule
		
		if(typeOfJSONConstraint.contains("ACR")) {
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
		if (jsonObj.getString("typeOfConstraint").equals("ACR<") || jsonObj.getString("typeOfConstraint").equals("ACR<=")) {
			ruleConstructor.setMaxValue(jsonObj.getString("maxValue"));
		}
		
		if (jsonObj.getString("typeOfConstraint").equals("ACR>=") || jsonObj.getString("typeOfConstraint").equals("ACR>=")) {
			ruleConstructor.setMinValue(jsonObj.getString("minValue"));
		}
		
		if (jsonObj.getString("typeOfConstraint").equals("ACR=")) {
			ruleConstructor.setMinValue(jsonObj.getString("maxValue"));
		}
		ruleConstructor.setTableName(jsonObj.getString("tableName"));
		ruleConstructor.setColumnName(jsonObj.getString("columnName"));
		ruleConstructor.setTypeOfConstraint(jsonObj.getString("typeOfConstraint"));
		
	}
	
	private void fillAttributeRangeRule(JSONObject jsonObj) {
		
	}
	
	private void fillAttributeListRule(JSONObject jsonObj) {
		
	}
	
	private void fillAttributeOtherRule(JSONObject jsonObj) {
		
	}
	
	private void fillTupleCompareRule(JSONObject jsonObj) {
		
	}
}
