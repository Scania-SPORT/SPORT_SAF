package parametermanagement;

import java.io.File;

import logMgn.SafLog;


import org.apache.log4j.xml.DOMConfigurator;

import commonFuncMgn.ReadInputDataFromFile;
import configMgn.ConfigureSaf;

/**
 * Defines constants for the user data parameters in the user data file. These parameters are used as constants throughout the platform. 
 * The aim is to avoid "" whenever a user data has to be read from the user data file.
 * 
 * The prefix, UD_ indicate that the value will be read from the user data file, e.g. resources/userdata/UD_<profile>.csv 
 * 
 */

public class UserdataParamters {
//	public static ConfigureSaf configuresaf;
//	public UserdataParamters (){
//		/**Initiate log4j configuration and instantiate ConfigureSaf.*/ 
//		ClassLoader classLoader = getClass().getClassLoader();
//		/**Get the language Excel file from class*/
//		File log4jConfigFile = new File(classLoader.getResource("log4jConfiguration.xml").getFile());
//		String log4jConfigFilePath=log4jConfigFile.getPath();
//		DOMConfigurator.configure(log4jConfigFilePath);
//		configuresaf =new ConfigureSaf();
//		configuresaf.loadPropery();
//		//readInputDataFromFile = new ReadInputDataFromFile();
//	}
//		
//
//
//	/**USER DATA PARAMTERS*/
//	public static final String UD_1="UD_1";
//	public static final String UD_2="UD_2";
//	public static final String UD_3="UD_3";
//	public static final String UD_4="UD_4";
//	public static final String UD_5="UD_5";
//	public static final String UD_6="UD_6";
//	public static final String UD_7="UD_7";
//	public static final String UD_PASSWORD="UD_PASSWORD";
//	public static final String UD_USERNAME="UD_USERNAME";

}
