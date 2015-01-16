package commontestmanagement;

import windowAndPageMgn.pageMgn.CommonWindowsAndPages;
import logMgn.SafLog;
import configMgn.ConfigureSaf;
/**
 * This class includes common test steps that could be used by other test scenarios under src/test/java.
 * Sucg common test steps are login, logout,etc.
 * @author George
 *
 */
public class CommonTestSteps extends CommonWindowsAndPages{
	
private boolean isPreviousTestCaseSucceeded;
	
	public boolean isPreviousTestCaseSucceeded() {
		return isPreviousTestCaseSucceeded;
	}

	public void setPreviousTestCaseSucceeded(boolean isPreviousTestCaseSucceeded) {
		this.isPreviousTestCaseSucceeded = isPreviousTestCaseSucceeded;
	}
	
	
	/**Login to 
	 * 
	 */
	 
	public void login(){
		SafLog.testStep();
		logInPage.fieldUserIDInsert("xdstest1");
		logInPage.fieldPasswordInsert("Sport123");
		logInPage.buttonLogOnClick();
	}

	public void logout(){
		SafLog.testStep();
	}
	
	public void StartTheWebApplication(String webApplicationURL)
    {
        SafLog.testStep(webApplicationURL);
        initWindowsAndPages();
        startWebDriver.createWebDriver();
        startWebDriver.GoToSite(webApplicationURL);
        findWebDriverElement.setDriver(startWebDriver.getDriver());
        findWebDriverElement.setJavaScriptCalls(javaScriptCalls);
        operateOnWebDriverElement.setFindWebDriverElement(findWebDriverElement);
        operateOnWebDriverElement.setJavaScriptCalls(javaScriptCalls);
        javaScriptCalls.setDriver(startWebDriver.getDriver());
//        StartDesktopApp.SetApplicationName(CommonWindowsAndPages.webApplicationProcessName);
//        operateOnWhiteElements.FindWhiteElements = findWhiteElements;
//        FindWhiteElements.RunningApplication = startDesktopApplication.RunningApplication;
//        whiteKeybaordManagement.FindWhiteElements = findWhiteElements;
    }
	
	public static void quiteDriver() {
		try {
			startWebDriver.getDriver().quit();
		} catch (Exception e) {
			//startWebDriver.getDriver().quit();
		}

	}
}
