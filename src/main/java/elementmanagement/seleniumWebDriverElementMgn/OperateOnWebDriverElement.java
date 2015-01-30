package elementmanagement.seleniumWebDriverElementMgn;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import commonFuncMgn.OtherFunctionality;
import commonFuncMgn.XPathFunctionality;
import configMgn.ConfigureSaf;

import logMgn.SafLog;

public class OperateOnWebDriverElement {
	
	 FindWebDriverElement findWebDriverElement;
	public JavaScriptCalls javaScriptCalls;
    
     public FindWebDriverElement getFindWebDriverElement() {
		return findWebDriverElement;
	}

	public void setFindWebDriverElement(FindWebDriverElement findWebDriverElement) {
		this.findWebDriverElement = findWebDriverElement;
	}

	public JavaScriptCalls getJavaScriptCalls() {
		return javaScriptCalls;
	}

	public void setJavaScriptCalls(JavaScriptCalls javaScriptCalls) {
		this.javaScriptCalls = javaScriptCalls;
	}



     /// <summary>
     /// Insert into a text field. Without frame or window switch
     /// The text field is identified by the element name.
     /// </summary>
     /// <param name="elementId"></param>
     /// <param name="textToInsert"></param>
     public void InsertInToFieldById(String elementId, String textToInsert)
     {
         InsertInToFieldById(null, elementId, textToInsert);
     }

     /// <summary>
     /// Insert into a text field. including frame switch, no window switch
     /// The text field is identified by the element name
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="elementId"></param>
     /// <param name="textToInsert"></param>
     public void InsertInToFieldById(String frameNameToSwitchTo, String elementId, String textToInsert)
     {
         InsertInToFieldById(null, frameNameToSwitchTo, elementId, textToInsert);
     }


     /// Insert into a text field. including frame switch, and window switch
     /// The text field is identified by the element name
     public void InsertInToFieldById(String pageNameToSwitchTo, String frameNameToSwitchTo, String elementId, String textToInsert)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementById(pageNameToSwitchTo, frameNameToSwitchTo, elementId);
         webelement.clear();
         webelement.sendKeys(textToInsert);
      }
     
     
     /// Insert into a text field. including frame switch, and window switch
     /// The text field is identified by the element name
     public void InsertInToFieldByIdUsingJavaScript(String pageNameToSwitchTo, String frameNameToSwitchTo, String elementId, String textToInsert)
     {
         SafLog.debug();
         javaScriptCalls.ExecuteJavaScript(pageNameToSwitchTo, frameNameToSwitchTo, "document.getElementById('"+elementId+"').value='"+textToInsert+"'");
     }

     
     public void selectFromDropdownListByIdAndValue(String pageNameToSwitchTo, String frameNameToSwitchTo, String elementId, String className, String valueToSelect){
    	 WebElement webelement =findWebDriverElement.waitForElementById(pageNameToSwitchTo, frameNameToSwitchTo, elementId);
    	 webelement.sendKeys(Keys.DOWN);
    	 OtherFunctionality.threadSleepInMSec(500);
    	 webelement.sendKeys(Keys.DOWN);
    	 List<WebElement> webElements=findWebDriverElement.getDriver().findElements(By.xpath("//*[@class='"+className+"']"));
    	 String idForOptionElement=null;
    	 for (WebElement elementInList : webElements) {
        	 OtherFunctionality.threadSleepInMSec(500);
    		 String t=elementInList.getText();
			if(elementInList.getText().replace(" ","").trim().toLowerCase().equals(valueToSelect.replace(" ","").trim().toLowerCase())){
				idForOptionElement=elementInList.getAttribute("id");
				javaScriptCalls.ExecuteJavaScript(pageNameToSwitchTo, frameNameToSwitchTo, "document.getElementById('"+idForOptionElement+"').click()");
				break ;
			}
		} 
     }
     
     
     
     
     /// <summary>
     /// Insert into a text field. Without frame or window switch
     /// The text field is identified by the element name.
     /// </summary>
     /// <param name="elementName"></param>
     /// <param name="textToInsert"></param>
     public void InsertInToFieldByName(String elementName, String textToInsert)
     {
         InsertInToFieldByName(null, elementName, textToInsert);
     }

     /// <summary>
     /// Insert into a text field. including frame switch, no window switch
     /// The text field is identified by the element name
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="elementName"></param>
     /// <param name="textToInsert"></param>
     public void InsertInToFieldByName(String frameNameToSwitchTo, String elementName, String textToInsert)
     {
         InsertInToFieldByName(null, frameNameToSwitchTo, elementName, textToInsert);
     }


     /// Insert into a text field. including frame switch, and window switch
     /// The text field is identified by the element name
     public void InsertInToFieldByName(String pageNameToSwitchTo, String frameNameToSwitchTo, String elementName, String textToInsert)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementByName(pageNameToSwitchTo, frameNameToSwitchTo, elementName);
         webelement.clear();
         webelement.sendKeys(textToInsert);
     }

     /// <summary>
     /// Insert into a text field. no frame switch, no window switch
     /// The text field is identified by the xPath of the element 
     /// </summary>
     /// <param name="elementName"></param>
     /// <param name="textToInsert"></param>
     public void InsertInToFieldByXPath(String elementName, String textToInsert)
     {
         InsertInToFieldByXPath(null, elementName, textToInsert);
     }

     /// <summary>
     /// Insert into a text field. including frame switch, no window switch
     /// The text field is identified by the xPath of the element 
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="elementName"></param>
     /// <param name="textToInsert"></param>
     public void InsertInToFieldByXPath(String frameNameToSwitchTo, String elementName, String textToInsert)
     {
         InsertInToFieldByXPath(null, frameNameToSwitchTo, elementName, textToInsert);
     }

     /// <summary>
     /// Insert into a text field. including frame switch, and window switch
     /// The text field is identified by the xPath of the element 
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="elementName"></param>
     /// <param name="textToInsert"></param>
     public void InsertInToFieldByXPath(String pageNameToSwitchTo, String frameNameToSwitchTo, String elementName, String textToInsert)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementByXPath(pageNameToSwitchTo, frameNameToSwitchTo, elementName);
         webelement.clear();
         webelement.sendKeys(textToInsert);
     }


     ///////////////////////////////CLICK AN ELEMENT/////////////////
     /// <summary>
     /// Click an element. no frame switch, no window switch
     /// The element is identified by the Id of the element 
     /// </summary>
     /// <param name="idForElementToClick"></param>
     public void clickAnElementById(String idForElementToClick)
     {
         clickAnElementById(null, idForElementToClick);
     }

     /// <summary>
     /// Click an element. includes frame switch, no window switch
     /// The element is identified by the Id of the element
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="idForElementToClick"></param>
     public void clickAnElementById(String frameNameToSwitchTo, String idForElementToClick)
     {
         clickAnElementById(null, frameNameToSwitchTo, idForElementToClick);
     }

     /// <summary>
     /// Click an element. includes frame switch, and window switch
     /// The element is identified by the Id of the element
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="idForElementToClick"></param>
     public void clickAnElementById(String pageNameToSwitchTo, String frameNameToSwitchTo, String idForElementToClick)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementById(pageNameToSwitchTo, frameNameToSwitchTo, idForElementToClick);
         try
         {
             webelement.click();
         }
         catch (Exception e)
         {
             SafLog.info("FAILED TO CLICK THE FIRST TIME. MAKE A SECOND TRY");
             webelement = findWebDriverElement.waitForElementById(pageNameToSwitchTo, frameNameToSwitchTo, idForElementToClick);
             webelement.click();
         }
     }

     /// <summary>
     /// Click an element. no frame switch, no window switch
     /// The element is identified by the xPath of the element
     /// </summary>
     /// <param name="xPathForElementToClick"></param>
     public void clickAnElementByXPath(String xPathForElementToClick)
     {
         clickAnElementByXPath(null, xPathForElementToClick);
     }

     /// <summary>
     /// Click an element. Includes frame switch, no window switch
     /// The element is identified by the xPath of the element
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="xPathForElementToClick"></param>
     public void clickAnElementByXPath(String frameNameToSwitchTo, String xPathForElementToClick)
     {
         clickAnElementByXPath(null, frameNameToSwitchTo, xPathForElementToClick);
     }

     /// <summary>
     /// Click an element. includes frame switch, and window switch
     /// The element is identified by the xpath of the element
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="xPathForElementToClick"></param>
     public void clickAnElementByXPath(String pageNameToSwitchTo, String frameNameToSwitchTo, String xPathForElementToClick)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementByXPath(pageNameToSwitchTo, frameNameToSwitchTo, xPathForElementToClick);
         try
         {
             webelement.click();
         }
         catch (Exception e)
         {
             SafLog.info("FAILED TO CLICK THE FIRST TIME. MAKE A SECOND TRY" + e.toString());
             webelement = findWebDriverElement.waitForElementByXPath(pageNameToSwitchTo, frameNameToSwitchTo, xPathForElementToClick);
             webelement.click();
         }
     }

     /// <summary>
     /// Click an element. Includes frame switch, no window switch
     /// The element is identified by the name of the element
     /// </summary>
     /// <param name="nameForElementToClick"></param>
     public void ClickAnElementByName(String nameForElementToClick)
     {
         ClickAnElementByName(null, nameForElementToClick);
     }

     /// <summary>
     /// Click an element. includes frame switch, no window switch
     /// The element is identified by the name of the element
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForElementToClick"></param>
     public void ClickAnElementByName(String frameNameToSwitchTo, String nameForElementToClick)
     {
         ClickAnElementByName(null, frameNameToSwitchTo, nameForElementToClick);
     }

     /// <summary>
     /// Click an element. includes frame switch, and window switch
     /// The element is identified by the name of the element
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForElementToClick"></param>
     public void ClickAnElementByName(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForElementToClick)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementByName(pageNameToSwitchTo, frameNameToSwitchTo, nameForElementToClick);
         webelement.click();
     }

     /// <summary>
     /// Click an element. no frame switch, no window switch
     /// The element is identified by the tag name and name of the element
     /// This method is used when element with the same name exists on the page
     /// </summary>
     /// <param name="nameForElementToClick"></param>
     /// <param name="tagNameForElementToClick"></param>
     public void ClickAnElementByNameAndTagName(String nameForElementToClick, String tagNameForElementToClick)
     {
         ClickAnElementByNameAndTagName(null, nameForElementToClick, tagNameForElementToClick);
     }

     /// <summary>
     /// Click an element. no frame switch, no window switch
     /// The element is identified by the tag name and name of the element
     /// This method is used when element with the same name exists on the page
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForElementToClick"></param>
     /// <param name="tagNameForElementToClick"></param>
     public void ClickAnElementByNameAndTagName(String frameNameToSwitchTo, String nameForElementToClick, String tagNameForElementToClick)
     {
         ClickAnElementByNameAndTagName(null, frameNameToSwitchTo, nameForElementToClick, tagNameForElementToClick);
     }

     /// <summary>
     /// Click an element. includes frame switch, and window switch
     /// The element is identified by the tag name and name of the element
     /// This method is used when element with the same name exists on the page
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForElementToClick"></param>
     /// <param name="tagNameForElementToClick"></param>
     public void ClickAnElementByNameAndTagName(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForElementToClick, String tagNameForElementToClick)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementByNameAndTagName(pageNameToSwitchTo, frameNameToSwitchTo, nameForElementToClick, tagNameForElementToClick);
         webelement.click();
     }

     /// <summary>
     /// Click en element using JavaScrip if IE specially version 9 do not react on click
     /// No frame switch, no window switch
     /// The element is identified by the Id of the element
     /// </summary>
     /// <param name="idForElementToClick"></param>
     public void clickAnElementByIdUsingJavaScript(String idForElementToClick)
     {
         clickAnElementByIdUsingJavaScript(null, idForElementToClick);
     }

     /// <summary>
     /// Click en element using JavaScrip if IE specially version 9 do not react on click
     /// Includes frame switch, no window switch
     /// The element is identified by the Id of the element
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="idForElementToClick"></param>
     public void clickAnElementByIdUsingJavaScript(String frameNameToSwitchTo, String idForElementToClick)
     {
         clickAnElementByIdUsingJavaScript(null, frameNameToSwitchTo, idForElementToClick);
     }

     /// <summary>
     /// Click en element using JavaScrip if IE specially version 9 do not react on click
     /// Includes frame switch, and window switch
     /// The element is identified by the Id of the element
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="idForElementToClick"></param>
     public void clickAnElementByIdUsingJavaScript(String pageNameToSwitchTo, String frameNameToSwitchTo, String idForElementToClick)
     {
         String scriptToExecute = "document.getElementById('" + idForElementToClick + "').click()";
         SafLog.debug("clickAWebElementByIdUsingJavaScript, scriptToExecute: " + scriptToExecute);
         javaScriptCalls.executeJavaScriptWithWaitForAjaxCallToFinish(pageNameToSwitchTo, frameNameToSwitchTo, scriptToExecute);
     }

     /// <summary>
     /// Click en element using JavaScrip if IE specially version 9 do not react on click
     /// No frame switch, no window switch
     /// The element is identified by the name of the element
     /// </summary>
     /// <param name="nameForElementToClick"></param>
     public void clickAnElementByNameUsingJavaScript(String nameForElementToClick)
     {
         clickAnElementByNameUsingJavaScript(null, nameForElementToClick);
     }

     /// <summary>
     /// Click en element using JavaScrip if IE specially version 9 do not react on click
     /// No frame switch, no window switch
     /// The element is identified by the name of the element
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForElementToClick"></param>
     public void clickAnElementByNameUsingJavaScript(String frameNameToSwitchTo, String nameForElementToClick)
     {
         clickAnElementByIdUsingJavaScript(null, frameNameToSwitchTo, nameForElementToClick);
     }

     /// <summary>
     /// Click en element using JavaScrip if IE specially version 9 do not react on click
     /// No frame switch, no window switch
     /// The element is identified by the name of the element
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForElementToClick"></param>
     public void clickAnElementByNameUsingJavaScript(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForElementToClick)
     {
         String scriptToExecute = "document.getElementsByName('" + nameForElementToClick + "')[0].click()";
         SafLog.debug( "clickAWebElementNameIdUsingJavaScript, scriptToExecute: " + scriptToExecute);
         javaScriptCalls.executeJavaScriptWithWaitForAjaxCallToFinish(pageNameToSwitchTo, frameNameToSwitchTo, scriptToExecute);
     }

     /// <summary>
     /// Click en element using JavaScrip if IE specially version 9 do not react on click
     /// No frame switch, no window switch
     /// The element is identified by the name of the element and an index.
     /// The mehod is used when several element exists on a page. The index of the element is idenified as the order of the element where it appears on the page from top
     /// </summary>
     /// <param name="nameForElementToClick"></param>
     /// <param name="indexForElementToClick"></param>
     public void ClickAnElementByNameAndIndexUsingJavaScript(String nameForElementToClick, String indexForElementToClick)
     {
         ClickAnElementByNameAndIndexUsingJavaScript(null, nameForElementToClick, indexForElementToClick);
     }

     /// <summary>
     /// Click en element using JavaScrip if IE specially version 9 do not react on click
     /// No frame switch, no window switch
     /// The element is identified by the name of the element and an index.
     /// The mehod is used when several element exists on a page. The index of the element is idenified as the order of the element where it appears on the page from top
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForElementToClick"></param>
     /// <param name="indexForElementToClick"></param>
     public void ClickAnElementByNameAndIndexUsingJavaScript(String frameNameToSwitchTo, String nameForElementToClick, String indexForElementToClick)
     {
         ClickAnElementByNameAndIndexUsingJavaScript(null, frameNameToSwitchTo, nameForElementToClick, indexForElementToClick);
     }

     /// <summary>
     /// Click en element using JavaScrip if IE specially version 9 do not react on click
     /// Includes frame switch, and window switch
     /// The element is identified by the name of the element and an index.
     /// The mehod is used when several element exists on a page. The index of the element is idenified as the order of the element where it appears on the page from top
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForElementToClick"></param>
     /// <param name="indexForElementToClick"></param>
     public void ClickAnElementByNameAndIndexUsingJavaScript(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForElementToClick, String indexForElementToClick)
     {
         String scriptToExecute = "document.getElementsByName('" + nameForElementToClick + "')[" + Integer.parseInt(indexForElementToClick) + "].click()";
         SafLog.debug( "clickAWebElementNameIdUsingJavaScript, scriptToExecute: " + scriptToExecute);
         javaScriptCalls.executeJavaScriptWithWaitForAjaxCallToFinish(pageNameToSwitchTo, frameNameToSwitchTo, scriptToExecute);
     }

     /// <summary>
     /// Select a checkbox if not selected.
     /// the element is identified by the ID
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="elementId"></param>
     public void SelectCheckBoxElementById(String pageNameToSwitchTo, String frameNameToSwitchTo, String elementId)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementById(pageNameToSwitchTo, frameNameToSwitchTo, elementId);
         if (!webelement.isSelected())
         {
             webelement.click();
         } 

     }

     /// <summary>
     /// Select a value in a dropdown list, based on a value
     /// </summary>
     /// <param name="nameForComboBoxToFind">Name of the combobox a top level element</param>
     /// <param name="indexForElementToFind">Index of the web element within the combobox</param>
     /// <param name="textValue">The value in the dropdown list to be selected</param>
     public void SelectTypeValueComboBoxByNameAndIndexAndValue(String nameForComboBoxToFind, String indexForElementToFind, String textValue)
     {
         SelectTypeValueComboBoxByNameAndIndexAndValue(null, null, nameForComboBoxToFind, indexForElementToFind, textValue);
     }

     /// <summary>
     /// Select a value in a dropdown list, based on a value
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForComboBoxToFind">Name of the combobox a top level element</param>
     /// <param name="indexForElementToFind">Index of the web element within the combobox</param>
     /// <param name="textValue">The value in the dropdown list to be selected</param>
     public void SelectTypeValueComboBoxByNameAndIndexAndValue(String frameNameToSwitchTo, String nameForComboBoxToFind, String indexForElementToFind, String textValue)
     {
         SelectTypeValueComboBoxByNameAndIndexAndValue(null, frameNameToSwitchTo, nameForComboBoxToFind, indexForElementToFind, textValue);
     }

     /// <summary>
     /// Select a value in a dropdown list, based on a value
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForComboBoxToFind">Name of the combobox a top level element</param>
     /// <param name="indexForElementToFind">Index of the web element within the combobox</param>
     /// <param name="textValue">The value in the dropdown list to be selected</param>
     public void SelectTypeValueComboBoxByNameAndIndexAndValue(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForComboBoxToFind, String indexForElementToFind, String textValue)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementByNameAndIndex(pageNameToSwitchTo, frameNameToSwitchTo, nameForComboBoxToFind, indexForElementToFind);
         //webelement.Click();
         List<WebElement> elementTrList = webelement.findElements(By.tagName("tr"));
         boolean isValueFound=false;
         for (WebElement elementTr : elementTrList) {
             List<WebElement> elementTdList = elementTr.findElements(By.tagName("td"));
             for (WebElement elementTd : elementTdList) {
                 String elementText = elementTd.getText().replace(" ", "").toLowerCase();
                 if (elementText.contains(textValue.replace(" ", "").toLowerCase()))
                 {
                         elementTd.click();
                         return;
                 }
             }
         }
         if (!isValueFound)
         {
             throw new RuntimeException("Value: " + textValue + " wsa not found");
         }
     }



     /// <param name="nameForComboBoxToFind"> Name of the combobox a top level element</param>
     /// <param name="textValue">The value in the dropdown list to be selected</param>
     /// <param name="nameForComboBoxToFind"> Name of the combobox a top level element</param>
     /// <param name="textValue">The value in the dropdown list to be selected</param>
     public void SelectTypeValueComboBoxByNameAndValue(String nameForComboBoxToFind, String textValue)
     {
         SelectTypeValueComboBoxByNameAndValue(null, null, nameForComboBoxToFind, textValue);
     }
     
     /// <param name="nameForComboBoxToFind"> Name of the combobox a top level element</param>
     /// <param name="textValue">The value in the dropdown list to be selected</param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForComboBoxToFind"> Name of the combobox a top level element</param>
     /// <param name="textValue">The value in the dropdown list to be selected</param>
     public void SelectTypeValueComboBoxByNameAndValue(String frameNameToSwitchTo, String nameForComboBoxToFind, String textValue)
     {
         SelectTypeValueComboBoxByNameAndValue(null, frameNameToSwitchTo, nameForComboBoxToFind, textValue);
     }
     
     /// <param name="nameForComboBoxToFind"> Name of the combobox a top level element</param>
     /// <param name="textValue">The value in the dropdown list to be selected</param>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="nameForComboBoxToFind"> Name of the combobox a top level element</param>
     /// <param name="textValue">The value in the dropdown list to be selected</param>
     public void SelectTypeValueComboBoxByNameAndValue(String pageNameToSwitchTo, String frameNameToSwitchTo, String nameForComboBoxToFind, String textValue)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementByName(pageNameToSwitchTo, frameNameToSwitchTo, nameForComboBoxToFind);
         //webelement.Click();
         List<WebElement> elementTrList = webelement.findElements(By.tagName("tr"));
         boolean isValueFound = false;        
         for (WebElement elementTr : elementTrList)
         {
             List<WebElement> elementTdList = elementTr.findElements(By.tagName("td"));
             for (WebElement elementTd : elementTdList)
             {
                 String elementText = elementTd.getText().replace(" ", "").toLowerCase();
                 if (elementText.equals(textValue.replace(" ", "").toLowerCase()))
                 {
                     elementTd.click();
                     return;
                 }
             }
         }
         if (!isValueFound)
         {
             throw new RuntimeException("Value: " + textValue + " wsa not found");
         }
     }

     ///// <summary>
     ///// 
     ///// </summary>
     ///// <param name="dropDownValue"></param>
     //public void SelectTypeValueComboBoxByName(String dropDownValue)
     //{
     //    SelectTypeValueComboBoxByName(null, dropDownValue);
     //}       

     //public void SelectTypeValueComboBoxByName(String frameNameToSwitchTo, String dropDownValue)
     //{
     //    SelectTypeValueComboBoxByName(null, frameNameToSwitchTo, dropDownValue);
     //}

     //public void SelectTypeValueComboBoxByName(String pageNameToSwitchTo, String frameNameToSwitchTo, String dropDownValue)
     //{
     //    SafLog.debug();
     //    WebElement webelement = findWebDriverElement.waitForElementByName(pageNameToSwitchTo, frameNameToSwitchTo, dropDownValue);
     //    webelement.Click();
     //}


     /// <summary>
     /// Select from a table based on the table ID and a text String in the table
     /// </summary>
     /// <param name="idForTable">Id for the table</param>
     /// <param name="textToIdentifyAndSelect"></param>
     public void SelectFromTableByIdAndText(String idForTable, String textToIdentifyAndSelect)
     {
         SelectFromTableByIdAndText(null, idForTable, textToIdentifyAndSelect);
     }

     /// <summary>
     /// Select from a table based on the table ID and a text String in the table
     /// </summary>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="idForTable">Id for the table</param>
     /// <param name="textToIdentifyAndSelect"></param>
     public void SelectFromTableByIdAndText(String frameNameToSwitchTo, String idForTable, String textToIdentifyAndSelect)
     {
         SelectFromTableByIdAndText(null, frameNameToSwitchTo, idForTable, textToIdentifyAndSelect);
     }

     /// <summary>
     /// Select from a table based on the table ID and a text String in the table
     /// </summary>
     /// <param name="pageNameToSwitchTo"></param>
     /// <param name="frameNameToSwitchTo"></param>
     /// <param name="idForTable">Id for the table</param>
     /// <param name="textToIdentifyAndSelect"></param>
     public void SelectFromTableByIdAndText(String pageNameToSwitchTo, String frameNameToSwitchTo, String idForTable, String... textToIdentifyAndSelect)
     {
         int numberOfTextCriteria = textToIdentifyAndSelect.length;
         int numberOfhits=0;
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementById(pageNameToSwitchTo, frameNameToSwitchTo, idForTable);
         List<WebElement> elementTrList = webelement.findElements(By.tagName("tr"));

         for (WebElement elementTr : elementTrList)
         {
             List<WebElement> elementTdList = elementTr.findElements(By.tagName("td"));
             for (WebElement elementTd : elementTdList)
             {
                 String elementText = elementTd.getText().replace(" ", "").toLowerCase();
                 if (elementText.contains(textToIdentifyAndSelect[0].replace(" ", "").toLowerCase()))
                 {
                     ++numberOfhits;
                     if (numberOfhits == numberOfTextCriteria)
                     {
                         elementTd.click();                           
                         return;
                     }

                 }                    
             }
             numberOfhits=0; //Restart with a new row
         }
     }

     public void SelectFromTableByIdAndTextAndRightClickSelectByText(String idForTable, String rightCLickMenyElementName, String textToIdentifyAndSelect)
     {
         SelectFromTableByIdAndTextAndRightClickSelectByText(null, idForTable,rightCLickMenyElementName, textToIdentifyAndSelect);
     }


     public void SelectFromTableByIdAndTextAndRightClickSelectByText(String frameNameToSwitchTo, String idForTable, String rightCLickMenyElementName, String textToIdentifyAndSelect)
     {
         SelectFromTableByIdAndTextAndRightClickSelectByText(null, frameNameToSwitchTo, idForTable, rightCLickMenyElementName, textToIdentifyAndSelect);
     }

     /**
      * Rightclick on text in a table. Select from the right click menu based on the given text String, textToIdentifyAndSelect
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @param idForTable
      * @param rightCLickMenyElementName
      * @param textToIdentifyAndSelect
      */
     public void SelectFromTableByIdAndTextAndRightClickSelectByText(String pageNameToSwitchTo, String frameNameToSwitchTo, String idForTable, String rightCLickMenyElementName, String... textToIdentifyAndSelect)
     {
         int numberOfTextCriteria = textToIdentifyAndSelect.length;
         int numberOfhits = 0;
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementById(pageNameToSwitchTo, frameNameToSwitchTo, idForTable);
         List<WebElement> elementTrList = webelement.findElements(By.tagName("tr"));
         boolean isElementFound = false;
         for (WebElement elementTr : elementTrList)
         {
             List<WebElement> elementTdList = elementTr.findElements(By.tagName("td"));
             for (WebElement elementTd : elementTdList)
             {
                 String elementText = elementTd.getText().replace(" ", "").toLowerCase();
                 if (elementText.contains(textToIdentifyAndSelect[0].replace(" ", "").toLowerCase()))
                 {
                     ++numberOfhits;
                     if (numberOfhits == numberOfTextCriteria)
                     {
                         elementTd.click();
                         Actions actions = new Actions(findWebDriverElement.getDriver());
                         actions.moveToElement(elementTd);
                         //RightClick on element on table
                         actions.contextClick(elementTd).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
                         isElementFound = true;
                         break;
                                               
                     }
                 }  
             }
             if (isElementFound)
             { 
                 break; 
             }
             numberOfhits = 0; //Restart with a new row
         }
         //Fetch the element from the popup menu
         webelement = findWebDriverElement.waitForElementByName(pageNameToSwitchTo, frameNameToSwitchTo, rightCLickMenyElementName);
         webelement.click();     
     }

     /**
      * Return the text ofn an element, given page name, frame name, element ID
      * @param pageNameToSwitchTo
      * @param frameNameToSwitchTo
      * @param elementId
      * @return
      */
     public String getTextOnElementByID(String pageNameToSwitchTo, String frameNameToSwitchTo, String elementId)
     {
         SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementById(pageNameToSwitchTo, frameNameToSwitchTo,elementId);
         String textOnelement=webelement.getText();
         return textOnelement;
     }

     public String getTextOnTableByTableIDAndRowClassName(String pageNameToSwitchTo, String frameNameToSwitchTo, String tableId, String tableDataClassName, String... rowIndex)
     {
         SafLog.debug();
        
         WebElement webelement = findWebDriverElement.waitForElementById(pageNameToSwitchTo, frameNameToSwitchTo,tableId);
         
         List<WebElement> elementTrList = webelement.findElements(By.tagName(tableDataClassName));
         boolean isElementFound = false;
         int trMaxIndex=rowIndex.length;
         if (rowIndex.length!=0){
        	 trMaxIndex=elementTrList.size();
         }
         String elementText =null;
         int index=0;
         for(int trIndex=0; trIndex<trMaxIndex; trIndex++)
         //for (WebElement elementTr : elementTrList)
         {
        	 WebElement elementTr = elementTrList.get(trIndex);
             WebElement elementTd = elementTr.findElement(By.className(tableDataClassName));
             elementText = elementTd.getText();
//             for (WebElement elementTd : elementTdList)
//             {
//                 String elementText = elementTd.getText().replace(" ", "").toLowerCase();
//                 if(elementText.isEmpty()){
//                	 WebElement  elementTd
//                 }
//                 if (elementText.contains(textToIdentifyAndSelect[0].replace(" ", "").toLowerCase()))
//                 {
//                     ++numberOfhits;
//                     if (numberOfhits == numberOfTextCriteria)
//                     {
//                         elementTd.click();
//                         Actions actions = new Actions(findWebDriverElement.getDriver());
//                         actions.moveToElement(elementTd);
//                         //RightClick on element on table
//                         actions.contextClick(elementTd).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
//                         isElementFound = true;
//                         break;
//                                               
//                     }
//                 }  
//             }
//             if (isElementFound)
//             { 
//                 break; 
//             }
//             numberOfhits = 0; //Restart with a new row
         }
//         String textOnelement=webelement.getText();
         return elementText;
     }
     
     
     public String getTextOnTableByTableClassNameAndRowClassName(String pageNameToSwitchTo, String frameNameToSwitchTo, String tableClassName, String tableDataClassName, String... rowIndex)
     {
         SafLog.debug();
        
         WebElement webelement = findWebDriverElement.waitForElementByClassName(pageNameToSwitchTo, frameNameToSwitchTo,tableClassName);
         
         List<WebElement> elementTrList = webelement.findElements(By.tagName(tableDataClassName));
         boolean isElementFound = false;
         int trMaxIndex=rowIndex.length;
         if (rowIndex.length!=0){
        	 trMaxIndex=elementTrList.size();
         }
         String elementText =null;
         int index=0;
         for(int trIndex=0; trIndex<trMaxIndex; trIndex++)
         //for (WebElement elementTr : elementTrList)
         {
        	 WebElement elementTr = elementTrList.get(trIndex);
             WebElement elementTd = elementTr.findElement(By.className(tableDataClassName));
             elementText = elementTd.getText();
         }
         return elementText;
     }
     
     public String getTextOnTableByClassNameAndRowIndex(String pageNameToSwitchTo, String frameNameToSwitchTo, String mainTableCalssName, String tableDataClassName, String rowIndex)
     {
         SafLog.debug();
        
         //WebElement webelement = findWebDriverElement.waitForElementByClassName(pageNameToSwitchTo, frameNameToSwitchTo,tableDataClassName);
         String elementText=null;
         List<WebElement> mainTableWebElement=findWebDriverElement.getDriver().findElements(By.xpath(XPathFunctionality.xPathByTagClassName("*", "dgrid-content ui-widget-content")));
         List<WebElement> elementTrList = mainTableWebElement.get(Integer.parseInt(rowIndex)).findElements(By.xpath(XPathFunctionality.xPathByTagClassName("td", tableDataClassName)));
         if (elementTrList.size()==0){
        	 elementTrList = mainTableWebElement.get(Integer.parseInt(rowIndex)).findElements(By.xpath(XPathFunctionality.xPathByTagClassName("span", tableDataClassName)));
         }
         if (elementTrList.size()==0){
        	 elementTrList = mainTableWebElement.get(Integer.parseInt(rowIndex)).findElements(By.xpath(XPathFunctionality.xPathByTagClassName("div", tableDataClassName)));
         }
         elementText= elementTrList.get(0).getText();
         return elementText;
     }
     
     public String getNumberOfRowsOnTableByClassName(String pageNameToSwitchTo, String frameNameToSwitchTo, String mainTableCalssName, String rowIndex)
     {
    	 SafLog.debug();
         String elementText=null;
         List<WebElement> mainTableWebElement=null;
         List<WebElement> elementTrList=null;
         long maxTimeToWait=System.currentTimeMillis()+ConfigureSaf.SAF_MAX_TIME_IN_MSEC_TO_WAIT_FOR_ELEMENT;
         while (mainTableWebElement!=null && mainTableWebElement.size()==0 && maxTimeToWait > System.currentTimeMillis())
         {
             try
             {
         mainTableWebElement=findWebDriverElement.getDriver().findElements(By.xpath(XPathFunctionality.xPathByTagClassName("*", "dgrid-content ui-widget-content")));
          elementTrList = mainTableWebElement.get(Integer.parseInt(rowIndex)).findElements(By.xpath(XPathFunctionality.xPathByTagAndRole("div", "row")));
         
             } catch (Exception e) {
				OtherFunctionality.threadSleepInMSec(500);
			}
             elementText = String.valueOf(elementTrList.size()-35);
         
         }
         return elementText;
     }
     
     public void assertTextOnTableByClassNameAndIndex(String pageNameToSwitchTo, String frameNameToSwitchTo, String mainTableCalssName, String tableDataClassName, String rowIndex, String textToAssert) {
 		SafLog.debug();
          String textOnelement=getTextOnTableByClassNameAndRowIndex(pageNameToSwitchTo, frameNameToSwitchTo, mainTableCalssName, tableDataClassName, rowIndex).toLowerCase().replace(" ", "").trim();
          Assert.assertTrue("The text didnt match, " + textOnelement + " : " + textToAssert, textOnelement.contains(textToAssert.toLowerCase().replace(" ", "").trim()));
 	}
     
       public void assertNumberOfRowsOnTableByClassName(String pageNameToSwitchTo, String frameNameToSwitchTo, String mainTableCalssName, String numberOfRowsToAssert) {
 		SafLog.debug();
          String numberOfRowsInTable=getNumberOfRowsOnTableByClassName(pageNameToSwitchTo, frameNameToSwitchTo, mainTableCalssName, "1").trim();
          Assert.assertTrue("The text didnt match, " + numberOfRowsInTable + " : " + numberOfRowsToAssert, numberOfRowsInTable.contains(numberOfRowsToAssert.replace(" ", "").trim()));
 	}
     
  public void assertTextOnTableByTableIDAndRowClassName(String pageNameToSwitchTo, String frameNameToSwitchTo, String tableId, String tableDataClassName, String textToAssert, String... rowIndex) {
		SafLog.debug();
         String textOnelement=getTextOnTableByTableIDAndRowClassName(pageNameToSwitchTo, frameNameToSwitchTo, tableId, tableDataClassName, rowIndex);
         Assert.assertTrue("Field is empty", textOnelement.trim().contains(textToAssert.trim()));
	}
	
  public void assertTextOnTableByTableClassNameAndRowClassName(String pageNameToSwitchTo, String frameNameToSwitchTo, String tableClassName, String tableDataClassName, String textToAssert, String... rowIndex) {
		SafLog.debug();
       String textOnelement=getTextOnTableByTableClassNameAndRowClassName(pageNameToSwitchTo, frameNameToSwitchTo, tableClassName, tableDataClassName, rowIndex);
       Assert.assertTrue("Field is empty", textOnelement.trim().contains(textToAssert.trim()));
	}
     
	public void assertFieldByIDNotEmpty(String elementId) {
		 SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementById(null, null, elementId);
         String textOnelement=webelement.getText();
		Assert.assertTrue("Field is empty", !textOnelement.isEmpty());
	}

	public void assertFieldByTagAndNameContainsText(String elementName, String textToVerify) {
		 SafLog.debug();
         WebElement webelement = findWebDriverElement.waitForElementByName(null, null, elementName);
         String textOnelement=webelement.getAttribute("value");
		Assert.assertTrue("Field is empty", textOnelement.trim().contains(textToVerify.trim()));
	}

	public void assertFieldByClass(String fIELD_CREATED_DATE_ID, String date) {
		SafLog.debug();
        WebElement webelement = findWebDriverElement.waitForElementByClassAndIndex(null, null, "dijitReset dijitInputField dijitInputContainer", "1");
        String textOnelement=webelement.getText();
	}
	
	public void assertFieldByIdContainsText(String iDForElementToFind, String textToAssert) {
		SafLog.debug();
        WebElement webelement = findWebDriverElement.waitForElementById(null, null, iDForElementToFind);
        String textOnelement=webelement.getAttribute("value").trim().toLowerCase().replace(" ", "");
		Assert.assertTrue("The text on the screen, " + textOnelement + " did not match the text to assert, " + textToAssert, textOnelement.trim().toLowerCase().replace(" ", "").contains(textOnelement));
	}
	
	public void assertFieldByNameContainsText(String nameForElementToFind, String textToAssert) {
		SafLog.debug();
        WebElement webelement = findWebDriverElement.waitForElementByName(null, null, nameForElementToFind);
        String textOnelement=webelement.getAttribute("value").trim().toLowerCase().replace(" ", "");
		Assert.assertTrue("The text on the screen, " + textOnelement + " did not match the text to assert, " + textToAssert, textOnelement.trim().toLowerCase().replace(" ", "").contains(textOnelement));
	}
}
