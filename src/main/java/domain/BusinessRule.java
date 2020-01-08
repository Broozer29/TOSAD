package domain;

public class BusinessRule {
	private String code;
	private String table;
	private String column;
	private String alterCommand;
	private String minValue;
	private String maxValue;
	
	public String getCode() {
		return this.code;
	}

	public BusinessRule(String table, String column, String alterCommand, String minValue, String maxValue) {
		this.table = table;
		this.column = column;
		this.alterCommand = alterCommand;
		this.minValue = minValue;
		this.maxValue = maxValue;
		generateBusinessRule();
	}

	private void generateBusinessRule() {
		switch (alterCommand.toUpperCase()) {
		case "NOT NULL":
			generateNotNullConstraint();
			break;
		case "LARGER":
			generateLargerThanConstraint();
			break;
		case "SMALLER":
			generateSmallerThanConstraint();
			break;
		}
	}
	
	private void generateNotNullConstraint() {
		this.code = "ALTER TABLE " + this.table + " MODIFY " + this.column + " NOT NULL;"; 
	}
	
	private void generateLargerThanConstraint() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + ">=" + this.minValue + ");";
	}
	
	private void generateSmallerThanConstraint() {
		this.code = "ALTER TABLE " + this.table + " ADD CHECK (" + this.column + "<=" + this.maxValue + ");";
	}
}
