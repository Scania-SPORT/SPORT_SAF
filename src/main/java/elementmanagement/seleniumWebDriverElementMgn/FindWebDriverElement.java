package elementmanagement.seleniumWebDriverElementMgn;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import logMgn.SafLog;

import org.apache.poi.ss.formula.eval.ConcatEval;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonFuncMgn.OtherFunctionality;
import configMgn.ConfigureSaf;

/**
 * Absract class that handles communication with the Selenium API.
 * The facilitates the search for an element, preferably, by an id.
 * It also facilitates the action that can be done on a web element such as click, send keys, get text, etc.
 *  
 *  
 * The class 
 * @author George
 *
 */
public class FindWebDriverElement{

	private JavaScriptCalls javaScriptCalls;
	private WebDriver driver;
     
	public JavaScriptCalls getJavaScriptCalls() {
		return javaScriptCalls;
	}

	public void setJavaScriptCalls(JavaScriptCalls javaScriptCalls) {
		this.javaScriptCalls = javaScriptCalls;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isWaitForAjax() {
		return waitForAjax;
	}

	boolean waitForAjax; 
	
	public void setWaitForAjax(boolean waitForAjax) {
		this.waitForAjax = waitForAjax;
	}

     /**
      * WebElement, wait and return an element.
      * The web element is identified by name
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @param nameForElementToFind
      * @return
      */
     public WebElement waitForElementByName(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForElementToFind)
     {
         return wiatAndGetElement(pageNameToSwitchTo, frameNameToSwitchTo, By.name(nameForElementToFind));
     }

     /**
      * WebElement, wait and return an element.
      * The web element is identified by name and tag name
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @param nameForElementToFind
      * @param tagNameForElementToFind
      * @return
      */
     public WebElement waitForElementByNameAndTagName(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForElementToFind, String tagNameForElementToFind)
     {
         return wiatAndGetElement(pageNameToSwitchTo, frameNameToSwitchTo, By.name(nameForElementToFind), By.tagName(tagNameForElementToFind));
     }

     /**
      * WebElement, wait and return an element.
      * The web element is identified by id
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @param iDForElementToFind
      * @return
      */
     public WebElement waitForElementById(String pageNameToSwitchTo, String frameNameToSwitchTo, String iDForElementToFind)
     {
         return wiatAndGetElement(pageNameToSwitchTo, frameNameToSwitchTo, By.id(iDForElementToFind));
     }

     public WebElement waitForElementByClassName(String pageNameToSwitchTo, String frameNameToSwitchTo, String classNameForElementToFind)
     {
         return wiatAndGetElement(pageNameToSwitchTo, frameNameToSwitchTo, By.className(classNameForElementToFind));
     }
     /**
      * WebElement, wait and return an element.
      * The web element is identified by xPath
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @param XPathForElementToFind
      * @return
      */
     public WebElement waitForElementByXPath(String pageNameToSwitchTo, String frameNameToSwitchTo, String XPathForElementToFind)
     {
         return wiatAndGetElement(pageNameToSwitchTo, frameNameToSwitchTo, By.xpath(XPathForElementToFind));
     }

     /**
      * WebElement, wait and return an element.
      * The web element is identified by link text
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @param linkTextForElementToFind
      * @return
      */
     public WebElement waitForElementByLinkText(String pageNameToSwitchTo, String frameNameToSwitchTo, String linkTextForElementToFind)
     {
         return wiatAndGetElement(pageNameToSwitchTo, frameNameToSwitchTo, By.linkText(linkTextForElementToFind));
     }

  /**
   *  WebElement, wait and return an element.
   * The web element is identified by tag name
   * @param pageNameToSwitchTo
   * @param frameNameToSwitchTo
   * @param tagNameForElementToFind
   * @return
   */
     public WebElement waitForElementByTagName(String pageNameToSwitchTo, String frameNameToSwitchTo, String tagNameForElementToFind)
     {
         return wiatAndGetElement(pageNameToSwitchTo, frameNameToSwitchTo, By.tagName(tagNameForElementToFind));
     }

     /**
      * WebElement, wait and return an element.
      * The web element is identified by name and an index/order number of the element, considered from the tag element
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @param nameForElementToFind
      * @param indexForElementToFind
      * @return
      */
     public WebElement waitForElementByNameAndIndex(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForElementToFind, String indexForElementToFind)
     {
         return wiatAndGetElement(pageNameToSwitchTo, frameNameToSwitchTo, By.name(nameForElementToFind), By.xpath("#"+indexForElementToFind+"##"));
     }
     
     public WebElement waitForElementByClassAndIndex(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForElementToFind, String indexForElementToFind)
     {
         return wiatAndGetElement(pageNameToSwitchTo, frameNameToSwitchTo, By.className(nameForElementToFind), By.xpath("#"+indexForElementToFind+"##"));
     }

     /**
      * Get the web element according to a given page name, frame name and location(identification method for the element)
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @param by
      * @return
      */
     private WebElement wiatAndGetElement(String pageNameToSwitchTo, String frameNameToSwitchTo, final By... by)
     {
         Exception exception = null;
         SafLog.debug(pageNameToSwitchTo, frameNameToSwitchTo, by.toString());
         String errorMessage = "No Element Was Found by: " + by[0] + "\n EXCEPTION THROWN: ";
         WebElement webElement = null;
         if (waitForAjax)
         {
             //Check the status of the page by executing the javascipt, document.status, to see if the page is fully loaded
             //This check makes the system slower but minimize the risk of failure
             javaScriptCalls.WaitForAjaxCallToFinish();
         }
         //Max time to wait for an element
         long maxTimeToWait=System.currentTimeMillis()+ConfigureSaf.SAF_MAX_TIME_IN_MSEC_TO_WAIT_FOR_ELEMENT; 		
         while (webElement == null && maxTimeToWait > System.currentTimeMillis())
         {
             try
             {
                 //Modify the diver by switching page and frame if needed
                 driver = SwitchToFrameOrWindowByName(driver, pageNameToSwitchTo, frameNameToSwitchTo);
                 if (by.length == 1)
                 {   
                     /////////////////
                     SafLog.debug(by.toString());
             		//threadSleep(SELENIUM_GENERAL_THREAD_SLEEP_TIME_IN_MSEC);//General
             		// sleep time is used to slow down the system, for demo
             		// Create an expected condition that expect a specific element,
             		// expCondition are idempotent
             		ExpectedCondition<WebElement> expCondition = new ExpectedCondition<WebElement>() {
             			public WebElement apply(WebDriver driver) {
             				SafLog.debug("Apply: driver" + driver);
             				int sizeOfWebElementList = 0;
             				WebElement webElement = driver.findElement(by[0]);
             				// Note. findelement() does not react if more than one element
             				// is found. It returns the "first" found element
             				
             				// Throw an exception if more than 1 element is found
             				List<WebElement> webElementList = driver.findElements(by[0]);
             				sizeOfWebElementList = webElementList.size();
//             				if (sizeOfWebElementList > 1) {
//             					for (int i = 0; i < sizeOfWebElementList; i++) {
//             						SafLog.warning(sizeOfWebElementList +" elements were found " +by);
//             						SafLog.warning("Tag name element_" + i + ": "	+ webElementList.get(i).getTagName());
//             						SafLog.warning("Text element_" + i + ": " + webElementList.get(i).getText());
//             					}
//             					throw new RuntimeException();
//             				}
             				return webElement;
             			}
             		};
             		SafLog.debug("WebDriverWait: " + expCondition.toString());
             		// Make the driver wait GLOBAL_TIMEOUT_IN_SEC to search for an element
             		// or until(expCondition) is true.
             		// WebDriverWait: den emiterar en inbygd wait-funktion, returneras som
             		// kan användas i expCondition.
             		try {
             			WebElement webElement1 = new WebDriverWait(driver, 30).until(expCondition);
             			return webElement1;
             		} catch (Exception e) {
             			throw new RuntimeException("No element '" + by.toString() + "' was found\n" , e);
             		}
                     
                     /////////////////////
                 }
                 else
                 {
                     List<WebElement> webElementList = driver.findElements(by[0]);
                     String byLocator = by[1].toString().toLowerCase().replace(" ", "");

                     if (byLocator.contains("by.tagname"))
                     {
                    	 for (WebElement webElementFromList : webElementList) {
						
                                 String webElementTagName = "by.tagname:" + webElementFromList.getTagName().toLowerCase().replace(" ", "");
                                 SafLog.debug( "webElementTagName: " + webElementTagName, "byLocator: " + byLocator);
                                 if (byLocator.contains(webElementTagName))
                                 {
                                     webElement= webElementFromList;
                                     break;
                                 }                          
                             }
                     }
                     else if (byLocator.contains("##"))
                     {
                         int startIndex = by[1].toString().indexOf("#")+1;
                         int endIndex=by[1].toString().indexOf("##")-startIndex;
                         String s = by[1].toString().substring(startIndex, endIndex);
                         int elementIndex=Integer.parseInt((by[1].toString().substring(startIndex,endIndex)));
                         webElement = webElementList.get(elementIndex);
                     }

                 }
             }
             catch (Exception e)
             {
                 exception = e;
                 OtherFunctionality.threadSleepInMSec(500);
             }
         }
     
         if (webElement == null)
         {               
             throw new  RuntimeException(errorMessage + (exception==null? "":exception.toString()));
         }
         return webElement;
     } 
     
//     /**
//      * Get the web element according to a given page name, frame name and location(identification method for the element)
//      * @param pageNameToSwitchTo
//      * @param frameNameToSwitchTo
//      * @param by
//      * @return
//      */
//     private List<WebElement> wiatAndGetElements(String pageNameToSwitchTo, String frameNameToSwitchTo, final By... by)
//     {
//         Exception exception = null;
//         SafLog.debug(pageNameToSwitchTo, frameNameToSwitchTo, by.toString());
//         String errorMessage = "No Element Was Found by: " + by[0] + "\n EXCEPTION THROWN: ";
//         WebElement[] webElements = null;
//         if (waitForAjax)
//         {
//             //Check the status of the page by executing the javascipt, document.status, to see if the page is fully loaded
//             //This check makes the system slower but minimize the risk of failure
//             javaScriptCalls.WaitForAjaxCallToFinish();
//         }
//         //Max time to wait for an element
//         long maxTimeToWait=System.currentTimeMillis()+ConfigureSaf.SAF_MAX_TIME_IN_MSEC_TO_WAIT_FOR_ELEMENT; 		
//         while (webElements == null && maxTimeToWait > System.currentTimeMillis())
//         {
//             try
//             {
//                 //Modify the diver by switching page and frame if needed
//                 driver = SwitchToFrameOrWindowByName(driver, pageNameToSwitchTo, frameNameToSwitchTo);
//                 if (by.length == 1)
//                 {   
//                     /////////////////
//                     SafLog.debug(by.toString());
//             		//threadSleep(SELENIUM_GENERAL_THREAD_SLEEP_TIME_IN_MSEC);//General
//             		// sleep time is used to slow down the system, for demo
//             		// Create an expected condition that expect a specific element,
//             		// expCondition are idempotent
//             		ExpectedCondition<WebElement> expCondition = new ExpectedCondition<WebElement>() {
//             			public WebElement apply(WebDriver driver) {
//             				SafLog.debug("Apply: driver" + driver);
//             				int sizeOfWebElementList = 0;
//             				WebElement webElement = driver.findElement(by[0]);
//             				// Note. findelement() does not react if more than one element
//             				// is found. It returns the "first" found element
//             				
//             				// Throw an exception if more than 1 element is found
//             				List<WebElement> webElementList = driver.findElements(by[0]);
//             				sizeOfWebElementList = webElementList.size();
//             				if (sizeOfWebElementList > 1) {
//             					for (int i = 0; i < sizeOfWebElementList; i++) {
//             						SafLog.warning(sizeOfWebElementList +" elements were found " +by);
//             						SafLog.warning("Tag name element_" + i + ": "	+ webElementList.get(i).getTagName());
//             						SafLog.warning("Text element_" + i + ": " + webElementList.get(i).getText());
//             					}
//             					throw new RuntimeException();
//             				}
//             				return webElement;
//             			}
//             		};
//             		SafLog.debug("WebDriverWait: " + expCondition.toString());
//             		// Make the driver wait GLOBAL_TIMEOUT_IN_SEC to search for an element
//             		// or until(expCondition) is true.
//             		// WebDriverWait: den emiterar en inbygd wait-funktion, returneras som
//             		// kan användas i expCondition.
//             		try {
//             			WebElement webElement1 = new WebDriverWait(driver, 30).until(expCondition);
//             			return webElement1;
//             		} catch (Exception e) {
//             			throw new RuntimeException("No element '" + by.toString() + "' was found\n" , e);
//             		}
//                     
//                     /////////////////////
//                 }
//                 else
//                 {
//                     List<WebElement> webElementList = driver.findElements(by[0]);
//                     String byLocator = by[1].toString().toLowerCase().replace(" ", "");
//
//                     if (byLocator.contains("by.tagname"))
//                     {
//                    	 for (WebElement webElementFromList : webElementList) {
//						
//                                 String webElementTagName = "by.tagname:" + webElementFromList.getTagName().toLowerCase().replace(" ", "");
//                                 SafLog.debug( "webElementTagName: " + webElementTagName, "byLocator: " + byLocator);
//                                 if (byLocator.contains(webElementTagName))
//                                 {
//                                     webElement= webElementFromList;
//                                     break;
//                                 }                          
//                             }
//                     }
//                     else if (byLocator.contains("##"))
//                     {
//                         int startIndex = by[1].toString().indexOf("#")+1;
//                         int endIndex=by[1].toString().indexOf("##")-startIndex;
//                         String s = by[1].toString().substring(startIndex, endIndex);
//                         int elementIndex=Integer.parseInt((by[1].toString().substring(startIndex,endIndex)));
//                         webElement = webElementList.get(elementIndex);
//                     }
//
//                 }
//             }
//             catch (Exception e)
//             {
//                 exception = e;
//                 OtherFunctionality.threadSleepInMSec(500);
//             }
//         }
//     
//         if (webElement == null)
//         {               
//             throw new  RuntimeException(errorMessage + (exception==null? "":exception.toString()));
//         }
//         return webElement;
//     } 
  
     /**
      * Modify the driver if needed, by switching page or frame
      * The switching is needed in case the element is placed in a different frame or page than the defualt ones.
      * @param driver
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @return
      */
     public static WebDriver SwitchToFrameOrWindowByName(WebDriver driver, String pageNameToSwitchTo, String frameNameToSwitchTo) 
     {
         //Go back to default page and frame
//         if (frameNameToSwitchTo == null && pageNameToSwitchTo == null)
//         {
//             try
//             {
//                 driver.switchTo().defaultContent();
//             }
//             catch (Exception e) {
//				SafLog.info(e.toString());
//			}
//         }
//         //only frame switch
//         else if (frameNameToSwitchTo != null && pageNameToSwitchTo == null)
//         {
//             try
//             {
//            	 driver.switchTo().defaultContent();
//             }
//             catch (Exception e) {
//				SafLog.info(e.toString());
//			}
//             driver.switchTo().frame(frameNameToSwitchTo);
//         }
//         //Only Window Switch
//         else if (frameNameToSwitchTo == null && pageNameToSwitchTo != null)
//         {                
//             driver = SwitchWindowByTitle(driver, pageNameToSwitchTo);
//            /// driver.SwitchTo().DefaultContent();
//         }
//         //Both frame and window Switch
//         else if (frameNameToSwitchTo != null && pageNameToSwitchTo != null)
//         {               
//             driver = SwitchWindowByTitle(driver, pageNameToSwitchTo);
//             driver.switchTo().defaultContent();
//             driver.switchTo().frame(frameNameToSwitchTo);
//         }
         
         return driver;
     }

     /**
      * Switch window
      * The new window is identified by name
      * @param driver
      * @param pageNameToSwitchTo
      * @return
      */
     public static WebDriver SwitchWindowByTitle(WebDriver driver, String pageNameToSwitchTo )
     {
         Set<String> windowList = driver.getWindowHandles();
         for (String pageName : windowList) {
		
             String title = driver.switchTo().window(pageName).getTitle();
             if (title.replace(" ", "").toLowerCase().equals(pageNameToSwitchTo.replace(" ", "").toLowerCase()))
             {
                 break;
             }
         }
         return driver;
     }

	
}
