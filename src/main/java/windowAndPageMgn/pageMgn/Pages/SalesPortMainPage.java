package windowAndPageMgn.pageMgn.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import commonFuncMgn.OtherFunctionality;
import commonFuncMgn.XPathFunctionality;
import configMgn.ConfigureSaf;

import windowAndPageMgn.pageMgn.CommonWindowsAndPages;
import logMgn.SafLog;

public class SalesPortMainPage  extends CommonWindowsAndPages{
	
	String TAB_STOCK_REFILL_XPATH="/x:html/x:body/x:div[1]/x:div/x:header/x:div[3]/x:div/x:div/x:div[1]/x:nav/x:ul/x:li[4]/x:a/x:span";
	
	public void tabStockRefillClick() {
		SafLog.testStep();
		//operateOnWebDriverElement.clickAnElementByXPath(TAB_STOCK_REFILL_XPATH);
		OtherFunctionality.threadSleepInMSec(1000);
		javaScriptCalls.WaitForAjaxCallToFinish();
		String driverUrl="";
        long maxTimeToWait=System.currentTimeMillis()+ConfigureSaf.SAF_MAX_TIME_IN_MSEC_TO_WAIT_FOR_ELEMENT;
        
        while (driverUrl!="http://proj.salesportal.scania.com:10039/wps/myportal/Home/stockrefill/" && maxTimeToWait > System.currentTimeMillis()){
        	startWebDriver.GoToSite("http://proj.salesportal.scania.com:10039/wps/myportal/Home/stockrefill/");
        	driverUrl=startWebDriver.getDriver().getCurrentUrl();
        	OtherFunctionality.threadSleepInMSec(500);
        }
	}
	
}
