package tosad.tosad;

import domain.BusinessRule;

public class BusinessBuilderImpl implements BusinessBuilder {
	private String tableName;
	private String columnName;
	private String minValue;
	private String maxValue;
	private String alterCommand;

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public void setTypeOfConstraint(String command) {
		this.alterCommand = command;
	}

	@Override
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	@Override
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public BusinessRule build() {
		return new BusinessRule(this.tableName, this.columnName, this.alterCommand, this.minValue, this.maxValue);
	}

}
