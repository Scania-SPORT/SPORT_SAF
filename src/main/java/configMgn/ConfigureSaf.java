package configMgn;

import java.io.InputStream;
import java.util.Properties;

import logMgn.SafLog;



public class ConfigureSaf {

	static Properties properties =new Properties();

	public void loadPropery(){
		SafLog.debug();
		InputStream propertyConfigurationStream = null;
    	String pathPropertyConfigurationFile="/ConfigureSaf.properties";
    	SafLog.debug("pathPropertyConfigurationFile: " + pathPropertyConfigurationFile);
    	ClassLoader classLoader = getClass().getClassLoader();
     	if(classLoader.getClass().getResourceAsStream(pathPropertyConfigurationFile)!=null){
     		propertyConfigurationStream = classLoader.getClass().getResourceAsStream(pathPropertyConfigurationFile);
        	SafLog.debug("Using configuration file: " + classLoader.getClass().getResource(pathPropertyConfigurationFile).toString());	    
     	}
    	try {
    		/**Load the properties with the keys*/
    		properties.load(propertyConfigurationStream);	
        } catch (Exception ex) {
            throw new RuntimeException("Could not load properties file." + ex);
        }
	}
	
	/**Return the property value according to the given key*/
	public static String getConfigurationProperty(String propertyKey){
		SafLog.debug("propertyKey: " + propertyKey);
		String  propertyValue=properties.getProperty(propertyKey).trim();
		SafLog.debug("propertyValue: " + propertyValue);
		return propertyValue;
	}
	
	
	/**Define the property keys*/
	
	public static String SAF_SITE_URL ;
	public static String SAF_USER_DATA_FILE_NAME ;
	public static String SAF_LANGUAGE ;
	public static String SAF_LANGUAGE_FILE_NAME ;
	public static boolean SAF_GRID ;
	public static String SAF_DRIVER ;
	public static boolean SAF_RERUN_TEST_CASE_IF_FAILED ;
	public static String SAF_USER_NAME ;
	public static String SAF_PASSWORD ;
	public static int SAF_MAX_TIME_IN_MSEC_TO_WAIT_FOR_ELEMENT ;
	public static int SAF_DEMO_DUMMY_DELAY_IN_MSEC ;
	
	
	public static void assignProperty(){
		 SAF_SITE_URL = ConfigureSaf.getConfigurationProperty("saf_site_url");
		 SAF_USER_DATA_FILE_NAME = ConfigureSaf.getConfigurationProperty("saf_user_data_file_name");
		 SAF_LANGUAGE = ConfigureSaf.getConfigurationProperty("saf_language");
		 SAF_LANGUAGE_FILE_NAME = ConfigureSaf.getConfigurationProperty("saf_language_file_name");
		 SAF_GRID = Boolean.parseBoolean(ConfigureSaf.getConfigurationProperty("saf_grid"));
		 SAF_DRIVER = ConfigureSaf.getConfigurationProperty("saf_driver");
		 SAF_RERUN_TEST_CASE_IF_FAILED = Boolean.valueOf(ConfigureSaf.getConfigurationProperty("saf_rerun_test_case_if_failed"));
		 SAF_USER_NAME = ConfigureSaf.getConfigurationProperty("saf_user_name");
		 SAF_PASSWORD = ConfigureSaf.getConfigurationProperty("saf_password");
		SAF_MAX_TIME_IN_MSEC_TO_WAIT_FOR_ELEMENT = Integer.parseInt(ConfigureSaf.getConfigurationProperty("saf_max_time_in_sec_to_wait_for_element"))*1000;
		SAF_DEMO_DUMMY_DELAY_IN_MSEC = Integer.parseInt(ConfigureSaf.getConfigurationProperty("saf_demo_dummy_delay_in_msec"));
		
	}
}
