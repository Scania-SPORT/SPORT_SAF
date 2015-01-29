package windowAndPageMgn.pageMgn.Pages;

import java.util.Hashtable;

import parametermanagement.OtherParameters;

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
			
	Hashtable<String,String> tableColumnValueClassName = new Hashtable<String, String>();
	private String DROPDOWN_SELECT_ID ="dijit_form_DropDownButton_";
	String TABLE_PRODUCT_LIST_MAIN_CLASSNAME="dgrid-row-table";
	
	String DROPDOWN_ITEM_CLASS="dijitReset dijitMenuItem";
	
	public EditRefillOrderPage(){
		tableColumnValueClassName.put(OtherParameters.ORDERED, "label label-info");
		tableColumnValueClassName.put(OtherParameters.TYPE, "dgrid-cell dgrid-cell-padding dgrid-column-productTypeId field-productTypeId");
		tableColumnValueClassName.put(OtherParameters.QUANTITY, "dgrid-cell dgrid-cell-padding dgrid-column-quantity field-quantity");
	}
	
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
		operateOnWebDriverElement.selectFromDropdownListByIdAndValue(null, null, FIELD_PURCHASING_ORGANISATION_ID, DROPDOWN_ITEM_CLASS, textToSelect);		
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
		OtherParameters.IS_PRODUCT_ADDED_PRODUCT_LIST=true;
		operateOnWebDriverElement.clickAnElementById(BUTTON_INSERT_ID);		
	}
	
	public void dropdownTypeSelect(String textToSelect) {
		SafLog.testStep(textToSelect);
		operateOnWebDriverElement.selectFromDropdownListByIdAndValue(null, null, DROPDOWN_TYPE_ID, DROPDOWN_ITEM_CLASS, textToSelect);		
	}
	
	public void assertTextOnTableProductList(String textToAssert, String columnName, String rowIndex){
		SafLog.testStep(textToAssert,columnName, rowIndex);		
		operateOnWebDriverElement.assertTextOnTableByClassNameAndIndex(null, null,TABLE_PRODUCT_LIST_MAIN_CLASSNAME, tableColumnValueClassName.get(columnName), rowIndex, textToAssert);		
	
	}

	public void dropdownToolSelect(String textToSelect, String rowNumber) {
		SafLog.testStep(textToSelect);
		String rowNumberTemp=rowNumber;
		if(OtherParameters.IS_PRODUCT_ADDED_PRODUCT_LIST){
			rowNumberTemp=OtherFunctionality.adjustedIndexMinusOne(rowNumber);
		}
		operateOnWebDriverElement.selectFromDropdownListByIdAndValue(null, null, DROPDOWN_SELECT_ID+rowNumberTemp, "dijitReset dijitMenuItemLabel", textToSelect);
	}
	
}
