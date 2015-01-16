package windowAndPageMgn.pageMgn.Pages;

import commonFuncMgn.XPathFunctionality;

import configMgn.ConfigureSaf;
import windowAndPageMgn.pageMgn.CommonWindowsAndPages;
import logMgn.SafLog;

public class LogInPage extends CommonWindowsAndPages{
	
	String BUTTON_LOG_ON_ID="login.button.login";	
	String FIELD_USER_ID_ID="userID";
	String FIELD_PASSWORD_ID="password";
	
	public void buttonLogOnClick() {
		SafLog.testStep();
		operateOnWebDriverElement.clickAnElementById(BUTTON_LOG_ON_ID);
		if(ConfigureSaf.SAF_SITE_URL.contains("proj")){
			operateOnWebDriverElement.InsertInToFieldByName("ivUser", ConfigureSaf.SAF_USER_NAME);
			operateOnWebDriverElement.clickAnElementByXPath(XPathFunctionality.xPathByTagAndType("input","submit"));
		}
	}
	
	public void fieldUserIDInsert(String textToInsert) {
		SafLog.testStep(textToInsert);
		operateOnWebDriverElement.InsertInToFieldById(FIELD_USER_ID_ID, textToInsert);
	}
	
	public void fieldPasswordInsert(String textToInsert) {
		SafLog.testStep("******");
		operateOnWebDriverElement.InsertInToFieldById(FIELD_PASSWORD_ID, textToInsert);
	}
	
	
}
