package tosad.tosad;

import domain.BusinessRule;

public interface BusinessBuilder {
	public void setTableName(String tableName);
	public void setColumnName(String columnName);
	public void setTypeOfConstraint(String command);
	public void setMinValue(String minValue);
	public void setMaxValue(String maxValue);
	
	public BusinessRule build();
}
