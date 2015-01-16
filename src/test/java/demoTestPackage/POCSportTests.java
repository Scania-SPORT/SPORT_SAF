package demoTestPackage;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import commontestmanagement.CommonTestSteps;
import configMgn.ConfigureSaf;
import testrulesmanagement.TestRuleTestCaseLevel;
import testrulesmanagement.TestRuleTestClassLevel;


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
	}
	


}
