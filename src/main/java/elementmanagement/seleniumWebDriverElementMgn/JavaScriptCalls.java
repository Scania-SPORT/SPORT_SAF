package elementmanagement.seleniumWebDriverElementMgn;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonFuncMgn.OtherFunctionality;

import configMgn.ConfigureSaf;

import logMgn.SafLog;

public class JavaScriptCalls {
	private WebDriver driver;
	
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**Execute the java script, scriptToExecute, on the site driver, e.g. to simulate an action that cannot be achieved by a webdriver action.
     * Such actions could be as forcing the browser to show hidden elements, get browser information, etc.
     * The java script execution should be used with caution since it not an exact simulation of the user behavior. 
     * @param scriptToExecute
     * @return an object
     */
    public Object ExecuteJavaScript(String pageNameToSwitchTo, String frameNameToSwitchTo, String scriptToExecute,  WebElement... webElement)
    {
        SafLog.debug("scriptToExecute: " + scriptToExecute);
        Object obj = null;
		long maxTimeToWait=System.currentTimeMillis()+ConfigureSaf.SAF_MAX_TIME_IN_MSEC_TO_WAIT_FOR_ELEMENT;
        while (obj == null && maxTimeToWait > System.currentTimeMillis())
        {
            try
            {
            	WebDriver driver = FindWebDriverElement.SwitchToFrameOrWindowByName(this.driver, pageNameToSwitchTo, frameNameToSwitchTo);
            	JavascriptExecutor javaScriptExecutor= (JavascriptExecutor) driver;
                {
                    if (webElement.length > 0)
                    {
                        obj = javaScriptExecutor.executeScript(scriptToExecute, webElement[0]);
                        return obj;
                    }
                    else
                    {
                        obj = javaScriptExecutor.executeScript(scriptToExecute);
                        return obj;
                    }
                }
            }
            catch (Exception e)
            {
            	SafLog.debug("object: " + obj);
            }
        }
        return obj;
    }
    
    /// <summary>
    /// Return a list of web element using the javascript to find element
    /// </summary>
    /// <param name="pageNameToSwitchTo"></param>
    /// <param name="frameNameToSwitchTo"></param>
    /// <param name="scriptToExecute"></param>
    /// <returns></returns>
    public List<WebElement> ExecuteJavaScriptIWebElementList(String pageNameToSwitchTo, String frameNameToSwitchTo, String scriptToExecute)
    {
        SafLog.debug("scriptToExecute: " + scriptToExecute);
        WebDriver driver = FindWebDriverElement.SwitchToFrameOrWindowByName(this.driver, pageNameToSwitchTo, frameNameToSwitchTo);
        JavascriptExecutor javaScriptExecutor= (JavascriptExecutor) driver;
        List<WebElement> iWebElementList = (List<WebElement>)javaScriptExecutor.executeScript(scriptToExecute);
        SafLog.debug("iWebElementList: " + iWebElementList);
        return iWebElementList;
    }

    /// <summary>
    /// Same as {@methodref executeJavaScript} but with a WaitForAjaxCallToFinish before executing the java script
    /// </summary>
    /// <param name="pageNameToSwitchTo"></param>
    /// <param name="frameNameToSwitchTo"></param>
    /// <param name="scriptToExecute"></param>
    /// <returns></returns>
    public Object executeJavaScriptWithWaitForAjaxCallToFinish(String pageNameToSwitchTo, String frameNameToSwitchTo, String scriptToExecute)
    {
        SafLog.debug("scriptToExecute: " + scriptToExecute);
        WaitForAjaxCallToFinish();
        Object obj = ExecuteJavaScript(pageNameToSwitchTo, frameNameToSwitchTo, scriptToExecute);
        WaitForAjaxCallToFinish();
        return obj;
    }

    /// <summary>
    /// Used Only wiht JQuery based web runningApplication 
    /// </summary>
    public void WaitForAjaxCallToFinish()
    {
        SafLog.debug();
        try
        {
            AjaxCallToFinish();
        }
        catch(Exception e)
        {
            SafLog.info( "CALLING THE JAVA SCRIPT THE FIST TIME FAILED. TRYING THE ONE MORE TIME; e: " + e.toString());
            AjaxCallToFinish();
        }
    }

    /// <summary>
    /// Wait for a page to loaded on the screen
    /// </summary>
    private void AjaxCallToFinish()
    {
        String documentReadyState = "";
        String documentReadyStateStripped = "";
		long maxTimeToWait=System.currentTimeMillis()+ConfigureSaf.SAF_MAX_TIME_IN_MSEC_TO_WAIT_FOR_ELEMENT;
        while (!documentReadyStateStripped.trim().toLowerCase().equals("complete") && maxTimeToWait > System.currentTimeMillis())
        {
            try
            {
                SafLog.debug();
                String scriptToExecute = "return document.readyState";
                JavascriptExecutor javaScriptExecutor= (JavascriptExecutor) driver;
                documentReadyState = (String)javaScriptExecutor.executeScript(scriptToExecute);
                documentReadyStateStripped = documentReadyState.trim().toLowerCase();
            }
            catch(Exception e)
            {
                SafLog.info( "JAVASCRIPT NOT READY YET");
                OtherFunctionality.threadSleepInMSec(500);
            }
        }
    }

}
