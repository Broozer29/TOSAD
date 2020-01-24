package tosad.tosad;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import domain.BusinessRule;
import persistence.BusinessRulePostgresDaoImpl;
import persistence.ConstraintExecutor;
import persistence.ConstraintExecutorImpl;
import persistence.TargetDatabaseConnector;
import service.ConstraintGenerator;
import service.ConstraintGeneratorImpl;
import service.JSonReader;

public class Reciever {
	public static void main(String[] args) throws IOException, SQLException {
		String recievedData = "";
		ServerSocket ss = new ServerSocket(4711);
		Socket s = ss.accept();
		Scanner scanner = new Scanner(s.getInputStream());
		while (scanner.hasNextLine()) {
			recievedData = scanner.nextLine();
		}
		
		System.out.println(recievedData);

		BusinessRulePostgresDaoImpl businessRuleDao = new BusinessRulePostgresDaoImpl();
		BusinessRule generatedBusinessRule = new BusinessRule();

		JSonReader jsonReader = new JSonReader(recievedData);
		int id = jsonReader.getRuleID();
		String typeOfSQL = jsonReader.getTypeOfSQL();

		generatedBusinessRule = businessRuleDao.findById(id);

		if (typeOfSQL.equals("constraint")) {
			ConstraintGenerator sqlGenerator = new ConstraintGeneratorImpl();
			System.out.println(sqlGenerator.generateCode(generatedBusinessRule));
			generatedBusinessRule.setConstraint(sqlGenerator.generateCode(generatedBusinessRule));
		}
		
		if (typeOfSQL.equals("trigger")) {
			//
		}

		Connection targetDatabaseConnection = TargetDatabaseConnector.getInstance();
		ConstraintExecutor constraintExecutor = new ConstraintExecutorImpl();
		constraintExecutor.executeConstraint(targetDatabaseConnection, generatedBusinessRule);
	}
}
