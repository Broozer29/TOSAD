package service;

import org.json.JSONObject;

public class JSonReader {
	private String jsonString;

	public JSonReader(String jsonString) {
		this.jsonString = jsonString;
	}

	public int getRuleID() {
		JSONObject jsonObj = new JSONObject(jsonString);
		return jsonObj.getInt("businessRuleID");
	}

	public String getTypeOfSQL() {
		JSONObject jsonObj = new JSONObject(jsonString);
		return jsonObj.getString("typeOfSQL");
	}
}
