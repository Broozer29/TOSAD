package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import domain.BusinessRule;
import persistence.target.ConstraintExecutor;
import persistence.target.ConstraintExecutorImpl;
import persistence.target.TargetDatabaseConnector;
import persistence.tool.businessRule.postgres.BusinessRulePostgresDaoImpl;
import service.ConstraintGenerator;
import service.ConstraintGeneratorImpl;
import service.JSonReader;
import service.trigger.AttributeRangeRuleGenerator;
import service.trigger.EntityOtherRuleGenerator;
import service.trigger.InterEntityCompareRuleGenerator;
import service.trigger.TriggerGenerator;

public class Reciever {
	public static void main(String[] args) throws IOException, SQLException {
		ServerSocket ss = new ServerSocket(4711);
		while (true) {
			String recievedData = "";
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
				generatedBusinessRule.setConstraint(sqlGenerator.generateCode(generatedBusinessRule));
				System.out.println(generatedBusinessRule.getConstraint());
			}

			if (typeOfSQL.equals("trigger")) {
				if (generatedBusinessRule.getRuleType().getCode().equals("ARNG")) {
					TriggerGenerator triggerGenerator = new AttributeRangeRuleGenerator();
					generatedBusinessRule.setTrigger(triggerGenerator.generateTrigger(generatedBusinessRule));
				}
				if (generatedBusinessRule.getRuleType().getCode().equals("EOTH")) {
					TriggerGenerator triggerGenerator = new EntityOtherRuleGenerator();
					generatedBusinessRule.setTrigger(triggerGenerator.generateTrigger(generatedBusinessRule));
				}
				if (generatedBusinessRule.getRuleType().getCode().equals("ICMP")) {
					TriggerGenerator triggerGenerator = new InterEntityCompareRuleGenerator();
					generatedBusinessRule.setTrigger(triggerGenerator.generateTrigger(generatedBusinessRule));
				}
				System.out.println(generatedBusinessRule.getTrigger());
			}

			businessRuleDao.update(generatedBusinessRule);
			Connection targetDatabaseConnection = new TargetDatabaseConnector().getInstance();
			ConstraintExecutor constraintExecutor = new ConstraintExecutorImpl();
			constraintExecutor.executeConstraint(targetDatabaseConnection, generatedBusinessRule);
		}
	}
}
