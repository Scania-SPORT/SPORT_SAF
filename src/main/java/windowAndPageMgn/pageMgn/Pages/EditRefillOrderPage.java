package windowAndPageMgn.pageMgn.Pages;

import commonFuncMgn.OtherFunctionality;

import windowAndPageMgn.pageMgn.CommonWindowsAndPages;
import logMgn.SafLog;

public class EditRefillOrderPage extends CommonWindowsAndPages{

	String FIELD_NUMBER_ID="stockOrderNumber_defaultStockRefillWorkspaceWidget_StockRefillDetailsWidget";
	String FIELD_CREATED_DATE_NAME="creationDate";
	String PATH_IMAGES_FOLDER_EDIT_REFILL_ORDER_PAGE="src//main//java//windowAndPageMgn//pageMgn//Pages//Images//EditRefillOrderPage//";
	private String BUTTON_SAVE_ID="saveButton_defaultStockRefillWorkspaceWidget_StockRefillDetailsWidget_label";
	
	String FIELD_PURCHASING_ORGANISATION_ID="stockOrderPurchasingOrganization_defaultStockRefillWorkspaceWidget_StockRefillDetailsWidget";
	private String BUTTON_INSERT_ID="dijit_form_ComboButton_0_label";
	private String DROPDOWN_TYPE_ID="dijit_form_FilteringSelect_3";
			
	public void fieldNumberAssertNotEmpty(){
		SafLog.testStep();
		operateOnWebDriverElement.assertFieldByIDNotEmpty(FIELD_NUMBER_ID);
	}

	
	public void fieldLanguageAssert(String languageCodeToAssert){
		SafLog.testStep();
		operateOnWebDriverElement.assertFieldByNameContainsText("languageISOCode", languageCodeToAssert);
	}
	
	public void fieldCurrencyAssert(String currencyCodeToAssert){
		SafLog.testStep();
		operateOnWebDriverElement.assertFieldByNameContainsText("currencyCode", currencyCodeToAssert);
	}
	
	public void fieldCreatedDateTodayAssert(){
		SafLog.testStep();
		String date=OtherFunctionality.getDate_yyyy_mm_dd();
		operateOnWebDriverElement.assertFieldByTagAndNameContainsText(FIELD_CREATED_DATE_NAME,date);
	}

	public void fieldPurchasingOrganizationTextAssert(String string) {
		SafLog.testStep();
		String date=OtherFunctionality.getDate_yyyy_mm_dd();
		operateOnWebDriverElement.assertFieldByClass("",date);
		
	}
	
	public void assertImageOnScreenUsingSikuli(String imageName){
		String fullImagePath=OtherFunctionality.getFullPath(PATH_IMAGES_FOLDER_EDIT_REFILL_ORDER_PAGE)+imageName;
		SafLog.testStep(fullImagePath);
		sikuliKeyboardMgn.assertImageOnScreen(fullImagePath);
	}
	
	public void assertImageNotOnScreenUsingSikuli(String imageName){
			String fullImagePath=OtherFunctionality.getFullPath(PATH_IMAGES_FOLDER_EDIT_REFILL_ORDER_PAGE)+imageName;
			SafLog.testStep(fullImagePath);
			sikuliKeyboardMgn.assertImageNotOnScreen(fullImagePath);
		}
	
	public void fieldPurchasingOrganisationInsert(String textToSelect) {
		SafLog.testStep(textToSelect);
		operateOnWebDriverElement.selectFromDropdownListByIdAndValue(null, null, FIELD_PURCHASING_ORGANISATION_ID, "", textToSelect);		
	}
	public void buttonSaveClick(){
		SafLog.testStep();
		operateOnWebDriverElement.clickAnElementById(BUTTON_SAVE_ID);
	
	}

	
	////////////////////////////////////////////////////////////////////////////////
	////////////////PRODUCT LIST//////////////////////////
	///////////////////////////////////////////////////////////////

	public void buttonInsertClick() {
		SafLog.testStep();
		operateOnWebDriverElement.clickAnElementById(BUTTON_INSERT_ID);		
	}
	
	public void dropdownTypeSelect(String textToSelect) {
		SafLog.testStep(textToSelect);
		operateOnWebDriverElement.selectFromDropdownListByIdAndValue(null, null, DROPDOWN_TYPE_ID, "", textToSelect);		
	}
	
}
