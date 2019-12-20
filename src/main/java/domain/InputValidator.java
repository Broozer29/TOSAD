package domain;

import java.util.ArrayList;

public class InputValidator {
	
	ArrayList<Table> tableList = new ArrayList<Table>();
	Table selectedTable = null;
	
	//Deze functie is vereist voor het testen van het prototype.
	public void testFillTableList() {
		Table testTable = new Table();
		testTable.setTableName("testTable");
		
		Column testColumn = new Column();
		testColumn.setColumnName("testColumn");
		ArrayList<Column> testColumnList = new ArrayList<Column>();
		testColumnList.add(testColumn);
		
		testTable.setColumnList(testColumnList);
		tableList.add(testTable);
	}
	
	public Boolean checkValidConstraint(String typeOfConstraint) {
		switch (typeOfConstraint.toUpperCase()) {
		case "NOT NULL":
			return true;

		case "LARGER":
			return true;
			
		case "SMALLER":
			return true;
		}
		return false;
	}
	
	
	public Boolean checkValidTableName(String tableName) {
		for (Table table : tableList) {
			if (table.getTableName().equals(tableName)) {
				selectedTable = table;
				return true;
			}
		}
		return false;
	}
	
	public Boolean checkValidColumn(String columnName) {
		if (selectedTable.containsColumn(columnName) != null) {
			return true;
		}
		return false;
	}
	

}
