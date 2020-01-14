package tosad.tosad;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import persistance.ConstraintExecutor;
import persistance.ConstraintExecutorImpl;
import persistance.TargetDatabaseConnector;
import service.BusinessRule;
import service.BusinessRuleConstructor;
import service.JSonReader;

public class App {
	public static void main(String[] args) throws IOException, SQLException {
		String recievedData = "";

		ServerSocket ss = new ServerSocket(4711);
		Socket s = ss.accept();
		Scanner scanner = new Scanner(s.getInputStream());
		while (scanner.hasNextLine()) {
			recievedData = scanner.nextLine();
		}
		
		
		

		BusinessRuleConstructor brConstructor = new BusinessRuleConstructor(recievedData);
		JSonReader jsonReader = new JSonReader(recievedData, brConstructor);
		jsonReader.fillConstructor();
		BusinessRule generatedBusinessRule = brConstructor.createBusinessRule();
		generatedBusinessRule.generateBusinessRule();

		System.out.println(generatedBusinessRule.getCode());

//		Connection toolDatabaseConnection = ToolDatabaseConnector.getInstance();
		Connection targetDatabaseConnection = TargetDatabaseConnector.getInstance();
		PreparedStatement stmt = targetDatabaseConnection.prepareStatement("select * from persoon");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		System.out.println("Query werk");
		ConstraintExecutor constraintExecutor = new ConstraintExecutorImpl();
		constraintExecutor.executeConstraint(targetDatabaseConnection, generatedBusinessRule);
	}
}
