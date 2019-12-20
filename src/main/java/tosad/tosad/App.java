package tosad.tosad;

import java.util.Scanner;

import domain.BusinessRule;
import domain.InputValidator;

public class App {
	public static void main(String[] args) {
		boolean validStatement = false;
		boolean validTable = false;
		boolean validColumn = false;
		
		String typeOfConstraint = "";
		String tableName = "";
		String columnName = "";
		
		String minValue = "";
		String maxValue = "";
		
		Scanner scanner = new Scanner(System.in);
		InputValidator validator = new InputValidator();
		
		validator.testFillTableList();

		while (!validStatement) {
			System.out.println("Kies een van de volgende constraints:\n'NOT NULL', 'LARGER' , 'SMALLER'");
			typeOfConstraint = scanner.nextLine();
			validStatement = validator.checkValidConstraint(typeOfConstraint);
		}
		
		while (!validTable) {
			System.out.println("Kies de table waar je de constraint op wilt uitvoeren: (Alleen testTable werkt voor testen).");
			tableName = scanner.nextLine();
			validTable = validator.checkValidTableName(tableName);
		}
		
		while (!validColumn) {
			System.out.println("Kies de column waar je de constraint op wilt uitvoeren: (Alleen testColumn werkt voor testen)");
			columnName = scanner.nextLine();
			validColumn = validator.checkValidColumn(columnName);
		}
		
		if (typeOfConstraint.equals("LARGER")) {
			System.out.println("Geef het getal dat de waarde minimaal mag zijn");
			minValue = scanner.nextLine();
		}
		
		if (typeOfConstraint.equals("SMALLER")) {
			System.out.println("Geef het getal dat de waarde maximaal mag zijn.");
			maxValue = scanner.nextLine();
		}
		
		BusinessBuilder builder = new BusinessBuilderImpl();
		builder.setTypeOfConstraint(typeOfConstraint);
		builder.setTableName(tableName);
		builder.setColumnName(columnName);
		builder.setMinValue(minValue);
		builder.setMaxValue(maxValue);
		BusinessRule businessRule = builder.build();
		System.out.println(businessRule.getCode());
	}
}
