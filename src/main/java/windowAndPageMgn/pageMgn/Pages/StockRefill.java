package windowAndPageMgn.pageMgn.Pages;

import configMgn.ConfigureSaf;
import windowAndPageMgn.pageMgn.CommonWindowsAndPages;
import logMgn.SafLog;

public class StockRefill extends CommonWindowsAndPages{
	
	String FIELD_PURCHASING_ORGANISATION_ID="dijit_form_FilteringSelect_0";
	String FIELD_DESCRIPTION_ID="dijit_form_TextBox_0";
	String BUTTON_NEW_STOCK_REFILL_ID="newButton_defaultStockRefillWorkspaceWidget_StockRefillWidget_label";
	String FIELD_LANGUAGE_ID="dijit_form_FilteringSelect_1";
	String FIELD_CURRENCY_ID="dijit_form_FilteringSelect_2";
	String BUTTON_CREATE_ID="createButton_stockRefillCreateWidget_defaultStockRefillWorkspaceWidget_StockRefillWidget_label";
	
	
	
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
		operateOnWebDriverElement.InsertInToFieldById(FIELD_PURCHASING_ORGANISATION_ID, textToInsert);
	}
	
	public void fieldDescriptionInsert(String textToInsert) {
		SafLog.testStep(textToInsert);
		operateOnWebDriverElement.InsertInToFieldById(FIELD_DESCRIPTION_ID, textToInsert);
	}
	
	public void fieldLanguageInsert(String textToInsert) {
		SafLog.testStep(textToInsert);
		operateOnWebDriverElement.InsertInToFieldById(FIELD_LANGUAGE_ID, textToInsert);
	}
	
	public void fieldCurrencyInsert(String textToInsert) {
		SafLog.testStep(textToInsert);
		operateOnWebDriverElement.InsertInToFieldById(FIELD_CURRENCY_ID, textToInsert);
	}
	

	public void buttonCreateClick() {
		SafLog.testStep();
		operateOnWebDriverElement.clickAnElementById(BUTTON_CREATE_ID);		
	}
	
}
