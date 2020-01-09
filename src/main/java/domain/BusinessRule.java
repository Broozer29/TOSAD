package domain;

public class BusinessRule {
	private String code;
	private String table;
	private String column;
	private String typeOfConstraint;
	private String minValue;
	private String maxValue;

	public String getCode() {
		return this.code;
	}

	public BusinessRule(String table, String column, String typeOfConstraint) {
		this.table = table;
		this.column = column;
		this.typeOfConstraint = typeOfConstraint;
	}
	

// Legenda voor rules: 
// ACR: Attribute Compare Rule 
// ARR: Attribute Range Rule
// ALR: Attribute List Rule 
// AOR: Attribute Other Rule 
// TCR: Tuple Compare Rule

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

	public void generateBusinessRule() {
		switch (typeOfConstraint.toUpperCase()) {
		case "NOT NULL":
			generateNotNullConstraint();
			break;
		case "ACR>":
			generateAttributeCompareRuleLargerThan();
			break;
		case "ACR<":
			generateAttributeCompareRuleSmallerThan();
			break;
		case "ACR>=":
			generateAttributeCompareRuleLargerInclusive();
			break;
		case "ACR<=":
			generateAttributeCompareRuleSmallerThanInclusive();
			break;
		case "ACR=":
			generateAttributeCompareRuleEqual();
			break;
		}

	}

	private void generateNotNullConstraint() {
		this.code = "ALTER TABLE " + this.table + " MODIFY " + this.column + " NOT NULL;";
	}

	private void generateAttributeCompareRuleLargerThan() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + ">" + this.minValue + ");";
	}

	private void generateAttributeCompareRuleLargerInclusive() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + ">=" + this.minValue + ");";
	}

	private void generateAttributeCompareRuleSmallerThan() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + "<" + this.maxValue + ");";
	}

	private void generateAttributeCompareRuleSmallerThanInclusive() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + "<=" + this.maxValue + ");";
	}

	private void generateAttributeCompareRuleEqual() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + "=" + this.maxValue + ");";
	}
}
