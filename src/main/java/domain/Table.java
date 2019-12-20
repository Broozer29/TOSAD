package domain;

import java.util.ArrayList;

public class Table {
	private String tableName;
	private ArrayList<Column> columnList;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public ArrayList<Column> getColumnList() {
		return columnList;
	}
	public void setColumnList(ArrayList<Column> columnList) {
		this.columnList = columnList;
	}
	
	public Column containsColumn(String columnName) {
		for (Column column : columnList) {
			if (column.getColumnName().equals(columnName)) {
				return column;
			}
		}
		return null;
	}
}
