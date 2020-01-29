package service;

import java.util.ArrayList;
import java.util.List;

import domain.BusinessRule;
import domain.BusinessRuleType;
import domain.Column;
import domain.Table;
import domain.Value;

public class ConstraintGeneratorImpl implements ConstraintGenerator {

	private Table table;
	private Table secondTable;

	private Column column;
	private Column secondColumn;

	public String generateCode(BusinessRule businessRule) {
		String generateCode = new String();
		BusinessRuleType typeOfRule = businessRule.getRuleType();
		setDefaultValues(businessRule);

		switch (typeOfRule.getCode()) {
		case "ACMP":
			generateCode = generateAttributeCompareConstraint(businessRule);
			break;
		case "ALIS":
			generateCode = generateAttributeListConstraint(businessRule);
			break;
		case "AOTH":

			generateCode = generateAttributeOtherConstraint(businessRule);
			break;
		case "ARNG":
			generateCode = generateAttributeRangeConstraint(businessRule);
			break;
		case "TCMP":
			// ALTER TABLE broeken ADD CONSTRAINT TOSAD_BRULE_TCR_01 CHECK (prijsInclBtw >
			// prijsExclBtw);
			// ORA-01735: invalid ALTER TABLE option
			generateCode = generateTupleCompareConstraint(businessRule);
			break;
		}

		return generateCode;
	}

	private String generateAttributeCompareConstraint(BusinessRule businessRule) {
		Value operatorValue = getOperaterValue(businessRule);
		Value maxValue = getMaxValue(businessRule);

		return "ALTER TABLE " + this.table.getName() + " ADD CONSTRAINT " + businessRule.getNaam() + " CHECK " + "("
				+ this.column.getName() + " " + operatorValue.getGiven() + " " + maxValue.getGiven() + ");";
	}

	private ArrayList<String> convertListOfValues(BusinessRule businessRule) {
		ArrayList<String> newList = new ArrayList<String>();
		for (Value value : businessRule.getDeValues()) {
			String newString = "'" + value.getGiven() + "'";
			newList.add(newString);
		}
		return newList;
	}

	private String generateAttributeListConstraint(BusinessRule businessRule) {
		ArrayList<String> stringValueList = convertListOfValues(businessRule);
		String generateCode = "ALTER TABLE " + this.table.getName() + " ADD CONSTRAINT " + businessRule.getNaam()
				+ " CHECK " + "(" + this.column.getName() + " IN " + "(" + stringValueList + ")" + ");";
		generateCode = generateCode.replace("[", "");
		generateCode = generateCode.replace("]", "");
		return generateCode;
	}

	private String generateAttributeOtherConstraint(BusinessRule businessRule) {
		Value startPosition = new Value();
		Value letterLength = new Value();
		Value minValue = getMinValue(businessRule);
		Value maxValue = getMaxValue(businessRule);

		for (Value value : businessRule.getDeValues()) {
			if (value.getDataType().equals("startPosition")) {
				startPosition = value;
			}
			if (value.getDataType().equals("letterLength")) {
				letterLength = value;
			}
		}

		return "ALTER TABLE " + this.table.getName() + " ADD CONSTRAINT " + businessRule.getNaam() + " CHECK " + "("
				+ "substr(" + this.column.getName() + ", " + startPosition.getGiven() + " ," + letterLength.getGiven()
				+ ")" + " between (" + "'" + minValue.getGiven() + "' and " + "'" + maxValue.getGiven() + "')" + ");";
	}

	private String generateAttributeRangeConstraint(BusinessRule businessRule) {
		Value minValue = getMinValue(businessRule);
		Value maxValue = getMaxValue(businessRule);
		return "ALTER TABLE " + this.table.getName() + " ADD CONSTRAINT " + businessRule.getNaam() + " CHECK " + "("
				+ this.column.getName() + " >=" + minValue.getGiven() + " AND " + this.column.getName() + " <= "
				+ maxValue.getGiven() + ");";

	}

	private String generateTupleCompareConstraint(BusinessRule businessRule) {
		Value compareValue = getOperaterValue(businessRule);
		return "ALTER TABLE " + this.table.getName() + " ADD CONSTRAINT " + businessRule.getNaam() + " CHECK " + "("
				+ this.column.getName() + " " + compareValue.getGiven() + " " + this.secondColumn.getName() + ");";
	}

	private void setDefaultValues(BusinessRule businessRule) {
		List<Table> tableList = businessRule.getDeTables();
		this.table = tableList.get(0);
		if (tableList.size() > 1) {
			this.secondTable = tableList.get(1);
		}

		List<Column> columnList = businessRule.getDeColumns();
		this.column = columnList.get(0);
		if (columnList.size() > 1) {
			this.secondColumn = columnList.get(1);
		}
	}

	private Value getMinValue(BusinessRule businessRule) {
		Value minValue = new Value();
		for (Value value : businessRule.getDeValues()) {
			if (value.getDataType().equals("minValue")) {
				minValue = value;
			}
		}
		return minValue;
	}

	private Value getMaxValue(BusinessRule businessRule) {
		Value maxValue = new Value();
		for (Value value : businessRule.getDeValues()) {
			if (value.getDataType().equals("maxValue")) {
				maxValue = value;
			}
		}
		return maxValue;
	}

	private Value getOperaterValue(BusinessRule businessRule) {
		Value operatorValue = new Value();
		for (Value value : businessRule.getDeValues()) {
			if (value.getDataType().equals("operator")) {
				operatorValue = value;
			}
		}
		return operatorValue;
	}

}
