package commonFuncMgn;

import logMgn.SafLog;

import org.openqa.selenium.JavascriptExecutor;

import elementmanagement.seleniumWebDriverElementMgn.FindWebDriverElement;


/**
 * A sub class to ReadInputDataFromFile. The class includes other common functionalities used by the sub classes, 
 * such as the page management classes and driver management classes
 * Such functionalities include thread sleep, running java scripts 
 * @author George
 *
 */
public abstract class OtherFunctionality extends ReadInputDataFromFile{
	
	
	/**Force the thread to pause for, mSecToSleep, milliseconds.
	 * The method could be used as a dummy delay as a last solution 
	 * @param mSecToSleep
	 */
	public static void threadSleepInMSec(int mSecToSleep) 	{
		SafLog.debug();
		try {
			Thread.sleep(mSecToSleep);			
		} catch (Exception e) {
			SafLog.debug("Thread sleep error " + e);
		}
	}

	/**Execute the java script, scriptToExecute, on the site driver, e.g. to simulate an action that cannot be achieved by a webdriver action.
	 * Such actions could be as forcing the browser to show hidden elements, get browser information, etc.
	 * The java script execution should be used with caution since it not an exact simulation of the user behavior. 
	 * @param scriptToExecute
	 * @return an object
	 */
//	public Object executeJavaScript(String scriptToExecute) {
//		SafLog.debug("scriptToExecute: " + scriptToExecute);
//		JavascriptExecutor javaScriptExecutor= (JavascriptExecutor) siteDriver;
//		Object object= javaScriptExecutor.executeScript(scriptToExecute);
//		SafLog.debug("object: " + object);
//		return object;		
//	}	
	
	/**Same as {@methodref executeJavaScript} but with a WaitForAjaxCallToFinish before executing the java script*/
//	public  Object executeJavaScriptWithWaitForAjaxCallToFinish(String scriptToExecute) {
//		SafLog.debug("scriptToExecute: " + scriptToExecute);
//		((FindWebDriverElement) this).waitForAjaxCallToFinish();
//		return executeJavaScript(scriptToExecute);		
//	}	
}
