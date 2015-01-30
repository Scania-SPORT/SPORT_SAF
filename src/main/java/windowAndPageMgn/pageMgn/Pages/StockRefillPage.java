package windowAndPageMgn.pageMgn.Pages;

import java.awt.RenderingHints.Key;

import org.openqa.selenium.Keys;

import configMgn.ConfigureSaf;
import windowAndPageMgn.pageMgn.CommonWindowsAndPages;
import logMgn.SafLog;

public class StockRefillPage extends CommonWindowsAndPages{
	
	String FIELD_PURCHASING_ORGANISATION_ID="dijit_form_FilteringSelect_0";//widget_stockOrderPurchasingOrganization_defaultStockRefillWorkspaceWidget_StockRefillDetailsWidget
	String FIELD_DESCRIPTION_ID="dijit_form_TextBox_0";
	String BUTTON_NEW_STOCK_REFILL_ID="newButton_defaultStockRefillWorkspaceWidget_StockRefillWidget_label";
	String FIELD_LANGUAGE_ID="dijit_form_FilteringSelect_1";
	String FIELD_CURRENCY_ID="dijit_form_FilteringSelect_2";
	String BUTTON_CREATE_ID="createButton_stockRefillCreateWidget_defaultStockRefillWorkspaceWidget_StockRefillWidget_label";
	
	String DROPDOWN_ITEM_CLASS="dijitReset dijitMenuItem";
	
	
	public void buttonNewStockRefillClick() {
		SafLog.testStep();
		operateOnWebDriverElement.clickAnElementById(BUTTON_NEW_STOCK_REFILL_ID);		
	}
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////
	//////////////POP NEW REFILL ORDER///////////////////////////////
	/////////////////////////////////////////////////////////
//	public void dropDownPurchasingOrganisationSelect(String valueToSelect){
//		SafLog.testStep(valueToSelect);
//		operateOnWebDriverElement.SelectTypeValueComboBoxByNameAndValue("nameForComboBoxToFind", "textValue");
//	}

	public void fieldPurchasingOrganisationInsert(String textToInsert) {
		SafLog.testStep(textToInsert);
		operateOnWebDriverElement.selectFromDropdownListByIdAndValue(null, null, FIELD_PURCHASING_ORGANISATION_ID, DROPDOWN_ITEM_CLASS, textToInsert);		
	}
	
	public void fieldDescriptionInsert(String textToInsert) {
		SafLog.testStep(textToInsert);
		operateOnWebDriverElement.InsertInToFieldById(null,null,FIELD_DESCRIPTION_ID, textToInsert);
	}
	
	public void fieldLanguageInsert(String textToInsert) {
		SafLog.testStep(textToInsert);
		operateOnWebDriverElement.selectFromDropdownListByIdAndValue(null, null, FIELD_LANGUAGE_ID, DROPDOWN_ITEM_CLASS,textToInsert);
	}
	
	public void fieldCurrencyInsert(String textToInsert) {
		SafLog.testStep(textToInsert);
		operateOnWebDriverElement.selectFromDropdownListByIdAndValue(null, null, FIELD_CURRENCY_ID, DROPDOWN_ITEM_CLASS, "EUR");
		}
	

	public void buttonCreateClick() {
		SafLog.testStep();
		operateOnWebDriverElement.clickAnElementById(BUTTON_CREATE_ID);		
	}
	
}
