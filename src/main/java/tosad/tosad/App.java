package tosad.tosad;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Scanner;

import domain.BusinessRule;
import domain.BusinessRuleConstructor;
import domain.JSonReader;
import persistance.TargetDatabaseConnector;
import persistance.ToolDatabaseConnector;

public class App {
	public static void main(String[] args) throws IOException {
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
//		Connection targetDatabaseConnection = TargetDatabaseConnector.getInstance();

	}
}
