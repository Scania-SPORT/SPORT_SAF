package windowAndPageMgn.pageMgn.Pages;

import commonFuncMgn.OtherFunctionality;
import commonFuncMgn.XPathFunctionality;

import windowAndPageMgn.pageMgn.CommonWindowsAndPages;
import logMgn.SafLog;

public class SalesPortMainPage  extends CommonWindowsAndPages{
	
	String TAB_STOCK_REFILL_XPATH="/x:html/x:body/x:div[1]/x:div/x:header/x:div[3]/x:div/x:div/x:div[1]/x:nav/x:ul/x:li[4]/x:a/x:span";
	
	public void tabStockRefillClick() {
		SafLog.testStep();
		//operateOnWebDriverElement.clickAnElementByXPath(TAB_STOCK_REFILL_XPATH);
		//OtherFunctionality.threadSleepInMSec(1000);
		javaScriptCalls.WaitForAjaxCallToFinish();
		startWebDriver.GoToSite("http://proj.salesportal.scania.com:10039/wps/myportal/Home/stockrefill/");
	}
	
}
