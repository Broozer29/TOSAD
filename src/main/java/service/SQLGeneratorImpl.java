package service;

import java.util.ArrayList;

import domain.BusinessRule;
import domain.BusinessRuleType;
import domain.Value;

public class SQLGeneratorImpl implements SQLGenerator {

	public Value generateCode(BusinessRule businessRule) {
		Value generateCode = new Value();
		String typeOfRule = getTypeOfRule(businessRule);
		switch (typeOfRule) {
		case "AttributeCompareRule":
			break;
		case "AttributeListRule":
			break;
		case "AttributeOtherRule":
			break;
		case "AttributeRangeRule":
			break;
		case "EntityOtherRule":
			break;
		case "InterEntityCompareRule":
			break;
		case "ModifyRule":
			break;
		case "TupleCompareRule":
			break;
		case "TupleOtherRule":
			break;
		}

		return generateCode;
	}

	public Value generateTriggerCode(BusinessRule businessRule) {
		Value generateCode = new Value();
		String typeOfRule = getTypeOfRule(businessRule);
		switch (typeOfRule) {
		case "AttributeCompareRule":
			generateAttributeCompareConstraint(businessRule);
			break;
		case "AttributeListRule":
			generateAttributeListConstraint(businessRule);
			break;
		case "AttributeOtherRule":
			generateAttributeOtherConstraint(businessRule);
			break;
		case "AttributeRangeRule":
			generateAttributeRangeConstraint(businessRule);
			break;
		case "EntityOtherRule":
			break;
		case "InterEntityCompareRule":
			break;
		case "ModifyRule":
			break;
		case "TupleCompareRule":
			generateTupleCompareConstraint(businessRule);
			break;
		case "TupleOtherRule":
			break;
		}

		return generateCode;
	}

	private String getTypeOfRule(BusinessRule businessRule) {
		BusinessRuleType businessRuleType = businessRule.getTypeOfConstraint();
		return businessRuleType.getName();
	}

	private String generateAttributeCompareConstraint(BusinessRule businessRule) {
		return "ALTER TABLE " + businessRule.getTable().getName() + " ADD CHECK (" + businessRule.getColumn().getName()
				+ " " + businessRule.getCompareRule().getGiven() + " " + businessRule.getMaxValue().getGiven() + ");";
	}

	private ArrayList<String> convertListOfValues(BusinessRule businessRule) {
		ArrayList<String> newList = new ArrayList<String>();
		for (Value value : businessRule.getListOfValues()) {
			String newString = "'" + value.getGiven() + "'";
			newList.add(newString);
		}
		return newList;
	}

	private String generateAttributeListConstraint(BusinessRule businessRule) {
		ArrayList<String> stringValueList = convertListOfValues(businessRule);
		String generateCode = "ALTER TABLE " + businessRule.getTable().getName() + " ADD CHECK (" + " status in " + "("
				+ stringValueList + ")" + ");";
		generateCode = generateCode.replace("[", "");
		generateCode = generateCode.replace("]", "");
		return generateCode;
	}

	private String generateAttributeOtherConstraint(BusinessRule businessRule) {
		return "ALTER TABLE " + businessRule.getTable().getName() + " ADD CHECK (" + "substr("
				+ businessRule.getColumn().getName() + ", " + businessRule.getStartPosition().getGiven() + " ,"
				+ businessRule.getLetterLength().getGiven() + ")" + " between (" + "'"
				+ businessRule.getMinValue().getGiven() + "' and " + "'" + businessRule.getMaxValue().getGiven() + "')"
				+ ");";
	}

	private String generateAttributeRangeConstraint(BusinessRule businessRule) {
		return "ALTER TABLE " + businessRule.getTable().getName() + " ADD CHECK (" + businessRule.getColumn().getName()
				+ " between " + businessRule.getMinValue().getGiven() + " and " + businessRule.getMaxValue().getGiven()
				+ ");";
	}

	private String generateTupleCompareConstraint(BusinessRule businessRule) {
		return "ALTER TABLE " + businessRule.getTable().getName() + " ADD CHECK (" + businessRule.getColumn().getName()
				+ businessRule.getCompareRule().getGiven() + businessRule.getSecondColumn().getName() + ");";
	}


}
