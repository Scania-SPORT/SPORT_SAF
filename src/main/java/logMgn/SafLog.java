package logMgn;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.junit.runner.Description;

import commonFuncMgn.ReadInputDataFromFile;

import parametermanagement.UserdataParamters;


public class SafLog extends UserdataParamters{
	
	static Logger logger=Logger.getRootLogger();
	
	StackTraceElement stackTraceElements[]=(new Throwable()).getStackTrace();

	public static void debug(String... debugMessageToLog){
		logger.debug(getTestClassNameTestMethodName()+arrayToStringMessage(debugMessageToLog));
	}

	public static void info(String... infoMessageToLog) {
		logger.info(getTestClassNameTestMethodName() + arrayToStringMessage(infoMessageToLog));		
	}

	public static String testStep(String...testStepMessageToLog) {
		//TODO move to aproprate place
		ReadInputDataFromFile readInputDataFromFile=new ReadInputDataFromFile();
		logger.info(getTestClassNameTestMethodName()+ arrayToStringMessage(testStepMessageToLog));	
		String inputData=readInputDataFromFile.getInputData(testStepMessageToLog);
		return inputData;
	}
	
	public static void warning(String... warningMessageToLog) {
		logger.info(getTestClassNameTestMethodName() + arrayToStringMessage(warningMessageToLog));	
		
	}

	public static String getTestClassNameTestMethodName(){
		StackTraceElement stackTraceElements[]=(new Throwable()).getStackTrace();
		String testClassName= stackTraceElements[2].getClassName();
		testClassName=testClassName.substring(testClassName.lastIndexOf(".")+1,testClassName.length()).toUpperCase()+ " ";
		String testMethodName= stackTraceElements[2].getMethodName() + " ";	
		return testClassName+testMethodName;
	}
	
	private static String arrayToStringMessage(String...string){
		int stringSize= string.length;
		String messageToLog="";
		StackTraceElement stackTraceElements[]=(new Throwable()).getStackTrace();
		 String testClassName= stackTraceElements[2].getClassName();
		for (int i=0; i<stringSize; i++){
			messageToLog= " " + string[i];
		}
		return messageToLog;
	}

	public static void testClassStart(Description description) {
		String testClass=description.getClassName();
		String testClassName= testClass.substring(testClass.lastIndexOf(".")+1, testClass.length()).toUpperCase();
		//String testClassName=getDescriptionClassName(description);
		logger.info("\n##############################################################\n" +
					"##############################################################\n" +
					"################TEST CLASS " + testClassName+ " STARTS#################" +
					"\n##############################################################\n" +
					"##############################################################\n");
	}

	public static void testClassEnd(Description description) {
		String testClass=description.getClassName();
		String testClassName= testClass.substring(testClass.lastIndexOf(".")+1, testClass.length()).toUpperCase();
		logger.info("\n##############################################################\n" +
				"##############################################################\n" +
				"################TEST CLASS " + testClassName+ " ENDS###############" +
				"\n##############################################################\n" +
				"##############################################################\n");
	}	
		
	public static void testCaseStart(Description description) {
		String testMethodName=description.getMethodName();
		logger.info("\n****************************************************************\n" +
					"**************TEST CASE, " + testMethodName+ ", STARTS**************" 
					/*"\n***********************************************************\n"*/);
	}

	public static void testCaseEnd(Description description) {
		String testCaseName=description.getMethodName();
		logger.info("\n****************************************************************\n" +
					"**************TEST CASE, " + testCaseName+ ", ENDS**************" +
					"\n***********************************************************\n");
	}

	public static void testCaseFailed(Description description) {
		String testCaseName=description.getMethodName().toUpperCase();
		logger.info("\n****************************************************************\n" +
					"**************TEST CASE, " + testCaseName+ " FAILED**************" +
					"\n***********************************************************\n");		
	}
	
	public static void testCaseErrorMessage(Throwable e) {
		logger.info("\n--------------ERROR MESSGE:--------------\n" + e.getMessage() + 
				"\n--------------STACK TRACE:--------------\n"  );
		StringWriter  stringWriter = new StringWriter(); 
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		logger.info(logStackTrace(e));
		 
	}
	
	public static void testClassPostConditionStarts() {
		logger.info("-------------TEST CLASS POSTCONDITION STARTS--------------\n");			
	}
		
	public static void testCasePostConditionStarts() {
		logger.info("-------------TEST CASE POSTCONDITION STARTS--------------\n");
	}
		
	public static void testCasePreConditionEnds() {
		logger.info("-------------TEST CASE PRECONDITION ENDS--------------\n");			
	}

	public static void testCasePreConditionStarts() {
		logger.info("-------------TEST CASE PRECONDITION STARTS--------------\n");
	}
	
	private static String logStackTrace(Throwable e){
		SafLog.debug(e.getMessage());
		StringWriter  stringWriter = new StringWriter(); 
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}
}

