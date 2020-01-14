package domain;

import java.util.ArrayList;

public class BusinessRule {

	private Operator operator;
	private ArrayList<Value> deValues;
	private ArrayList<Table> deTables;
	private ArrayList<Column> deColumns;
	private BusinessRuleType ruleType;
	private String example;
	private String code;
	private String typeOfConstraint;

	public BusinessRule() {
		deValues = new ArrayList<Value>();
		deTables = new ArrayList<Table>();
	}

	public Operator getOperator() {
		return operator;
	}

	public ArrayList<Column> getDeColumns() {
		return deColumns;
	}

	public void setDeColumns(ArrayList<Column> deColumns) {
		this.deColumns = deColumns;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public ArrayList<Value> getDeValues() {
		return deValues;
	}

	public void setDeValues(ArrayList<Value> deValues) {
		this.deValues = deValues;
	}

	public BusinessRuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(BusinessRuleType ruleType) {
		this.ruleType = ruleType;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTypeOfConstraint() {
		return typeOfConstraint;
	}

	public void setTypeOfConstraint(String typeOfConstraint) {
		this.typeOfConstraint = typeOfConstraint;
	}

	public ArrayList<Table> getDeTables() {
		return deTables;
	}

	public void setDeTables(ArrayList<Table> deTables) {
		this.deTables = deTables;
	}

}
