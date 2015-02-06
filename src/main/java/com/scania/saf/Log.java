package com.scania.saf;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

	static Logger logger = Logger.getLogger(Log.class);

	static {
		PropertyConfigurator.configure(Log.class.getClassLoader().getResource("log4j.properties").getPath());
		logger.setLevel(Level.DEBUG);
	}

	public static void debug(Object ... parameters) {
		logger.debug(buildLog(parameters));
	}

	public static void info(Object ... parameters) {
		logger.info(buildLog(parameters));
	}

	public static void error(Object ... parameters) {
		logger.error(parameters);
	}
	
	public static void fatal(Object ... parameters) {
		logger.fatal(buildLog(parameters));
	}

	private static String buildLog(Object... parameters) {
		StackTraceElement element = Stack.getStackTraceElement(2);
		StringBuilder logBuilder = new StringBuilder(simpleClassName(element)+"."+element.getMethodName()+"(");
		StringBuilder parameterBuilder = new StringBuilder();
		for(Object parameter : parameters) {
			if(parameterBuilder.length() > 0)
				parameterBuilder.append(", ");
			parameterBuilder.append(parameter.toString());
		}
		logBuilder.append(parameterBuilder.toString());
		logBuilder.append(")");
		return logBuilder.toString();
	}
	
	private static String simpleClassName(StackTraceElement element) {
		String className = element.getClassName();
		return className.substring(className.lastIndexOf(".") + 1);
	}
	
	public static void main(String[] args) {
		Log.fatal();
	}

}
