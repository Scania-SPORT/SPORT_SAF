package demoTestPackage;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import parametermanagement.OtherParameters;

import testrulesmanagement.TestRuleTestCaseLevel;
import testrulesmanagement.TestRuleTestClassLevel;

import commontestmanagement.CommonTestSteps;
import configMgn.ConfigureSaf;
import elementmanagement.sikuliMgn.SikuliKeyboardMgn;


public class POCSportTests extends CommonTestSteps{

	@ClassRule
	public static TestRuleTestClassLevel safClassLevelTestRules=new TestRuleTestClassLevel();
		
	@Rule
	public TestRuleTestCaseLevel safMethodLevelTestRules= new TestRuleTestCaseLevel();
	
	@Before
	public void preCondtion(){
		if(!isPreviousTestCaseSucceeded()){
			StartTheWebApplication(ConfigureSaf.SAF_SITE_URL);
		}
		login();
	}
	
	@After
	public void testCasePostCondtion(){
	}

	/**
	 * login logout for several user, with different account 
	 * Open the order page	
	 */	
	@Test
	public void startSportOrder(){	
		salesPortMainPage.tabStockRefillClick();
		stockRefill.buttonNewStockRefillClick();
		stockRefill.fieldPurchasingOrganisationInsert("Vilnius, UAB Scania Lietuva (LT10)");
		stockRefill.fieldDescriptionInsert("Lithuanian Desc");
		stockRefill.fieldLanguageInsert("Lithuanian");
		stockRefill.fieldCurrencyInsert("EUR");
		stockRefill.buttonCreateClick();
		//editRefillOrder.fieldNumberAssertNotEmpty();
		
		editRefillOrder.assertImageNotOnScreenUsingSikuli("INPUTFIELD_NUMBER_EMPTY.png");
		//editRefillOrder.assertImageOnScreenUsingSikuli("DROPDOWN_PURCHASING_ORGANIZATION_LT10.png");
		editRefillOrder.fieldCreatedDateTodayAssert();
		editRefillOrder.fieldLanguageAssert("lt");
		editRefillOrder.fieldCurrencyAssert("EUR");
		editRefillOrder.fieldPurchasingOrganisationInsert("SRO BUCHAREST BRANCH");
		
		editRefillOrder.buttonSaveClick();
		
		////ADD A PRODUCT////////
		editRefillOrder.dropdownTypeSelect("Truck");
		editRefillOrder.buttonInsertClick();
		editRefillOrder.assertTextOnTableProductList(OtherParameters.TRUCK,OtherParameters.TYPE,"1");
		editRefillOrder.assertTextOnTableProductList(OtherParameters.NO,OtherParameters.ORDERED,"1");
		editRefillOrder.assertTextOnTableProductList("1",OtherParameters.QUANTITY,"1");
		editRefillOrder.dropdownToolSelect("Copy", "1");
		editRefillOrder.dropdownToolSelect("Remove", "2");
	}
	


}
