package windowAndPageMgn.pageMgn.Pages;

import commontestmanagement.CommonTestSteps;

import elementmanagement.seleniumWebDriverElementMgn.OperateOnWebDriverElement;
import logMgn.SafLog;

public class QuotationsPage extends CommonTestSteps{
	
	String FIELD_NAME_QUOTATION_NUMBER_ID="....";
	String FIELD_NAME_PRODUCT_DESCRIPTION_ID="....";
	
	
	public void fieldQuotationNumberInsert(String quotationToInset){
		SafLog.testStep(quotationToInset);
		operateOnWebDriverElement.InsertInToFieldById(FIELD_NAME_QUOTATION_NUMBER_ID, quotationToInset);
	}
	
	public void fieldProductDescriptionInsert(String quotationToInset){
		SafLog.testStep(quotationToInset);
		operateOnWebDriverElement.InsertInToFieldById(FIELD_NAME_PRODUCT_DESCRIPTION_ID, quotationToInset);
	}

}
