package commonFuncMgn;

import logMgn.SafLog;

public class XPathFunctionality {
	
	//should be excluded and replaced by XPathPath1
	static private String XpathBlockDynamicID(String id){
		String xPathValue="substring(@id, 64, string-length(\'" + id + "\')) = \'" + id + "\'";
		SafLog.debug("XpathBlockDynamicID: " + xPathValue);
		return xPathValue;
	}

	/**
	 * Create an XPath string 
	 * @param id
	 * @return
	 */
	static public String XPathPath1(String id ){
		String xPathPath1="//*[substring(@id, string-length(@id) - string-length(\'" + id + "\') +1) = \'" + id + "\']| //*[@id=\"" + id + "\"]";	
		SafLog.debug("XPathPath1: " + xPathPath1);
		return xPathPath1;
		}
	
	static public String XPathDynamicId(String id ){
		String xPathDynamicId="//*[" + XpathBlockDynamicID(id) + "]"; 
		SafLog.debug("XPathDynamicId: " + xPathDynamicId);
		return 	xPathDynamicId;
	}

	//Depreciated
	static public String XPathPath3(String id, String value ){
		String xPathPath3="//*[substring(@id, string-length(@id) - string-length(\'" + id + "\') +1) = \'" +   id + "\' and contains(@value,\"" + value + "\")]| //*[@id=\"" + id + "\" and contains(@value,\"" + value + "\")]";	
		SafLog.debug("XPathPath3: " + xPathPath3);
		return xPathPath3;
	}
		
    // Find pattern as <input type="radio" value="2_se.linkon.stina.select.domain.model.impl.standard.LssJourneyAdviceHolderStandard@63fe750e" id="8924_1309256129282_qwerty_outbound_itineraries" name="8924_1309256129282_qwerty_outbound_itineraries" tabindex="-1">
	static public String XPathRadioButton(String id, String value ){
		String xPathRadioButton="//*[substring(@id, 1, string-length(\'" + id + "\') ) = \'" +  id + "\' and substring(@value,1,1) = \"" + value + "\"]";  //"//*[contains("+id+")\" and substring(@value,1,1) = \"" + value + "\"]";
		SafLog.debug("XPathSelectOption: " + xPathRadioButton);
		return xPathRadioButton;
		}
	
    // Find pattern as //a[@title="SHIFT <> Å"]	
	static public String XPathEquals(String element, String attribute, String value ){
		String xPathEquals= "//" + element + "[@" + attribute +  "=\"" + value + "\"]";
		SafLog.debug("xPathEquals: " + xPathEquals);
		return xPathEquals;
		}
	
    // Find pattern as //a[@class="fancyButton windowListButton windowListCurrentOrder "]	
	static public String XPathContains(String element, String attribute, String value ){
		String xPathEquals= "//" + element + "[contains(@" + attribute + " ,\"" + value + "\")]";
		SafLog.debug("xPathEquals: " + xPathEquals);
		return xPathEquals;
		}

	// Find pattern as "//id('LssItinerarySearchView_2.advancedSearchOptionsContainer')//a[2][@ class='mainLink']";
		static public String XPathSelectIdPlusEquals(String id, String element, String attribute, String value ){
			String xPathSelectIdPlusEquals="//*[" + XpathBlockDynamicID(id) + "]" + XPathEquals(element, attribute, value);
			SafLog.debug("XPathSelectOption: " + xPathSelectIdPlusEquals);
			return  xPathSelectIdPlusEquals;
		}	
		
	// Find pattern as //*[@id="6913_1309163737927_qwerty__itineraryPartPriceGroup"]/option[@value="0_SJ11R"]	
	static public String XPathSelectOption(String id, String value ){
		String xPathSelectOption="//*[" + XpathBlockDynamicID(id) + "]/option[@value=\"" + value + "\"]";
		SafLog.debug("XPathSelectOption: " + xPathSelectOption);
		return  xPathSelectOption;
	}	
	
	// Find pattern as: <select onchange=";$('_flexibilityHtmlselect_event').value='_flexibilityHtmlselect'; linkon.application.applicationManager.getWindowManager().executeCommand(this, '', null, false);;" tabindex="820" size="3" id="9377_1309265545363_qwerty__flexibilityHtmlselect" name="9377_1309265545363_qwerty__flexibilityHtmlselect"><option selected="" value="2_se.sj.stina.domain.model.impl.standard.SjOptionSpecificationStandard@71bf6389" title="Kan inte ombokas/återbetalas 0,00 kr">Kan inte ombokas/återbetalas 0,00 kr</option>
	// Using XPath like: *[@id="6913_1309163737927_qwerty__itineraryPartPriceGroup"]/option[substring(@value,1,1) = '2']	
	static public String XPathSelectOptionFirstChar(String id, String value ){
		String xPathSelectOptionFirstChar="//*[" + XpathBlockDynamicID(id) + "]/option[substring(@value,1,1) = \"" + value + "\"]";
		SafLog.debug("XPathSelectOptionFirstChar: " + xPathSelectOptionFirstChar);
		return xPathSelectOptionFirstChar;
	}
	
		// depreciated....
	// Find pattern as //*[@id="7538_1309181551518_qwerty__flexibilityHtmlselect"]/option[contains(@value,"2")]
	// where value = "2_se.sj.stina.domain.model.impl.standard.SjOptionSpecificationStandard@56acdb13"
	static public String XPathSelectOptionContains(String id, String value ){
		String xPathSelectOptionContains="//*[" + XpathBlockDynamicID(id) + "]/option[contains(@value,\"" + value + "\")]"; 
		SafLog.debug("XPathSelectOptionContains: " + xPathSelectOptionContains);
		return xPathSelectOptionContains;
	}
	//Find pattern as: <img src="https://docs.linkon.se/q/linkonline/images/icon_booking_error.png">
	//or <img src="https://docs.linkon.se/q/linkonline/images/icon_booking_ok.png">
	static public String XPathImageName(String imageName ){
		return "//img[contains(@src,\"" + imageName + "\")]"; 	
	}
	
	//Find pattern as: <SPAN id=xxx>...<img src="https://docs.linkon.se/q/linkonline/images/icon_booking_error.png">
	//or <SPAN id=xxx>...<img src="https://docs.linkon.se/q/linkonline/images/icon_booking_ok.png">
	static public String XPathIdAndImageName(String id, String imageName ){
		return "//*[@id=\""+id+"\"]//img[contains(@src,\"" + imageName + "\")]"; 	
	}
	
	public String xPathStartsWithId(String tag, String id){
		String xPath="//" +tag+"[starts-with(@id, '"+id+"')]";
		return xPath;
	}
	
	public String xPathEndsWithId(String tag, String endPartOfId){
		String xPath="//"+tag+"[substring(@id,(string-length(@id)-string-length('" + endPartOfId + "')+1))= '"+endPartOfId+"']";
		return xPath;
	}
	
	public String xPathContains(String tag, String endPartOfId){
		String xPath="//"+tag+"[contains(@id,'"+endPartOfId+"')]";
		return xPath;
	}
	
	public String xPathContainsStartAndEndPart(String tag, String startPartOfIdm, String endPartOfId){
		String xPath="//"+tag+"[contains(@id,'"+startPartOfIdm+"') and contains(@id,'"+endPartOfId+"')]";
		return xPath;
	}
	
	public String xPathStartsWithIdAndContains(String tag, String id, String endPartOfId){
		String xPath="//" +tag+"[starts-with(@id, '"+id+"') and contains(@id,'"+endPartOfId+"')]";
		return xPath;
	}
	
	public String xPathExcludeDynamicPart(String tag, String startPartId, String endPartId){
		String xPath="//"+tag+"[substring(@id,(string-length('"+startPartId+"')+1)) = '" +startPartId+"' and substring(@id,(string-length(@id)-string-length('" + endPartId + "')+1))= '"+endPartId+"'])";
		return xPath;
	}

	public static String xPathByTagAndType(String tag, String type){
		String xPath="//"+tag+"[@type='"+type+"']";
		return xPath;
	}
	
	public static String xPathByTagAndText(String tag, String text){
		String xPath="//"+tag+"[@Text='"+text+"']";
		return xPath;
	}
}
