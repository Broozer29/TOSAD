package tosad.tosad;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import domain.BusinessRule;
import domain.BusinessRuleImpl;
import persistance.BusinessRulePostgresDaoImpl;
import persistance.ConstraintExecutor;
import persistance.ConstraintExecutorImpl;
import persistance.TargetDatabaseConnector;
import service.SQLGenerator;
import service.SQLGeneratorImpl;
//import service.JSonReader;

public class Reciever {
	public static void main(String[] args) throws IOException, SQLException {
		String recievedData = "";

		ServerSocket ss = new ServerSocket(4711);
		Socket s = ss.accept();
		Scanner scanner = new Scanner(s.getInputStream());
		while (scanner.hasNextLine()) {
			recievedData = scanner.nextLine();
		}
		
		BusinessRulePostgresDaoImpl businessRuleDao = new BusinessRulePostgresDaoImpl();
		BusinessRule generatedBusinessRule = new BusinessRuleImpl();
		
		int id = 10;
		generatedBusinessRule = businessRuleDao.findById(id);
		SQLGenerator sqlGenerator = new SQLGeneratorImpl();
		generatedBusinessRule.setCode(sqlGenerator.generateCode(generatedBusinessRule));
		
		System.out.println(generatedBusinessRule.getCode().getGiven());
//		JSonReader jsonReader = new JSonReader(recievedData, brConstructor);
//		jsonReader.fillConstructor();
		
//		Connection toolDatabaseConnection = ToolDatabaseConnector.getInstance();
		Connection targetDatabaseConnection = TargetDatabaseConnector.getInstance();
		ConstraintExecutor constraintExecutor = new ConstraintExecutorImpl();
		constraintExecutor.executeConstraint(targetDatabaseConnection, generatedBusinessRule);
	}
}
