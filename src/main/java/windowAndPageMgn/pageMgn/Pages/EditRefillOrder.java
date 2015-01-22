package windowAndPageMgn.pageMgn.Pages;

import commonFuncMgn.OtherFunctionality;

import windowAndPageMgn.pageMgn.CommonWindowsAndPages;
import logMgn.SafLog;

public class EditRefillOrder extends CommonWindowsAndPages{

	String FIELD_NUMBER_ID="stockOrderNumber_defaultStockRefillWorkspaceWidget_StockRefillDetailsWidget";
	String FIELD_CREATED_DATE_ID="stockOrderCreationDate_defaultStockRefillWorkspaceWidget_StockRefillDetailsWidget";
			
	public void fieldNumberAssertNotEmpty(){
		SafLog.testStep();
		operateOnWebDriverElement.assertFieldByIDNotEmpty(FIELD_NUMBER_ID);
	}

	public void fieldCreatedDateTodayAssert(){
		SafLog.testStep();
		String date=OtherFunctionality.getDate_yyyy_mm_dd();
		operateOnWebDriverElement.assertFieldByTagAndNameContainsText(FIELD_CREATED_DATE_ID,date);
	}

	public void fieldPurchasingOrganizationTextAssert(String string) {
		SafLog.testStep();
		String date=OtherFunctionality.getDate_yyyy_mm_dd();
		operateOnWebDriverElement.assertFieldByClass(FIELD_CREATED_DATE_ID,date);
		
	}
}
