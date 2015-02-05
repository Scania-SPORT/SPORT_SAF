package com.scania.saf.todo.rule;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.scania.saf.config.ConfigureSaf;
import com.scania.saf.log.SafLog;
import com.scania.saf.todo.common.CommonTestSteps;


public class TestRuleTestClassLevel extends CommonTestSteps implements TestRule {
	
	public TestRuleTestClassLevel() {
		
		/**Initiate log4j configuration and instantiate ConfigureSaf.*/ 
		ClassLoader classLoader = getClass().getClassLoader();
		/**Get the language Excel file from class*/
		File log4jConfigFile = new File(classLoader.getResource("configuration/log4jConfiguration.xml").getFile());
		String log4jConfigFilePath=log4jConfigFile.getPath();
		DOMConfigurator.configure(log4jConfigFilePath);
		initiateSiteDriverAndPageInstance();
		
		//configureSaf.loadPropery();
		ConfigureSaf.assignProperty();
	}

	public Statement apply(Statement base, Description description) {
		return statement(base, description);
	}

	private Statement statement(final Statement base, final Description description) {
		return new Statement() {
			
			@Override
			public void evaluate() throws Throwable {
				try {
					SafLog.testClassStart(description);		
					setPreviousTestCaseSucceeded(false);
					base.evaluate();
					SafLog.testClassEnd(description);
				} catch (Exception e) {
					SafLog.info(e.toString());
					CommonTestSteps.quiteDriver();
				}
			}
		};
	}

}
