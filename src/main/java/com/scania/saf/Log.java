package com.scania.saf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

	static Logger logger = LoggerFactory.getLogger(Log.class);

	public static void debug(Object ... parameters) {
		logger.debug(buildLog(parameters));
	}

	public static void info(Object ... parameters) {
		logger.info(buildLog(parameters));
	}

	public static void error(Object ... parameters) {
		logger.error(buildLog(parameters));
	}
	
	private static String buildLog(Object... parameters) {
		StackTraceElement element = Stack.getStackTraceElement(2);
		StringBuilder logBuilder = new StringBuilder(simpleClassName(element)+"."+element.getMethodName()+"(");
		StringBuilder parameterBuilder = new StringBuilder();
		for(Object parameter : parameters) {
			if(parameterBuilder.length() > 0)
				parameterBuilder.append(", ");
			parameterBuilder.append((parameter == null ? "null" : parameter.toString()));
		}
		logBuilder.append(parameterBuilder.toString());
		logBuilder.append(")");
		return logBuilder.toString();
	}
	
	private static String simpleClassName(StackTraceElement element) {
		String className = element.getClassName();
		return className.substring(className.lastIndexOf(".") + 1);
	}
	
}
