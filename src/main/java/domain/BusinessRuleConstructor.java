package domain;

public class BusinessRuleConstructor {
	private String recievedData;

	private String typeOfConstraint;
	private String tableName;
	private String columnName;
	private String minValue;
	private String maxValue;

	public BusinessRuleConstructor(String recievedData) {
		this.recievedData = recievedData;
	}

	public BusinessRule createBusinessRule() {
		BusinessRule newBusinessRule = new BusinessRule(tableName, columnName, typeOfConstraint);

		if (typeOfConstraint.equals("ACR<") || typeOfConstraint.equals("ACR<=") || typeOfConstraint.equals("ACR=")) {
			newBusinessRule.setMaxValue(maxValue);
		}
		if (typeOfConstraint.equals("ACR>") || typeOfConstraint.equals("ACR>=")) {
			newBusinessRule.setMinValue(minValue);
		}
		return newBusinessRule;
	}

	public String getRecievedData() {
		return recievedData;
	}

	public void setRecievedData(String recievedData) {
		this.recievedData = recievedData;
	}

	public String getTypeOfConstraint() {
		return typeOfConstraint;
	}

	public void setTypeOfConstraint(String typeOfConstraint) {
		this.typeOfConstraint = typeOfConstraint;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
}
