package com.scania.salesportal.stockrefill;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import testrulesmanagement.TestRuleTestCaseLevel;
import testrulesmanagement.TestRuleTestClassLevel;

import appMgn.StartWebDriver;

import com.scania.salesportal.stockrefill.StockRefillPage;
import commontestmanagement.CommonTestSteps;
import configMgn.ConfigureSaf;

public class StockRefillTest extends CommonTestSteps {

	@ClassRule
	public static TestRuleTestClassLevel safClassLevelTestRules = new TestRuleTestClassLevel();
		
	@Rule
	public TestRuleTestCaseLevel safMethodLevelTestRules = new TestRuleTestCaseLevel();
	
	private WebDriver driver;
	
	@Before
	public void preCondtion(){
		if(!isPreviousTestCaseSucceeded()){
			StartTheWebApplication(ConfigureSaf.SAF_SITE_URL);
		}
		login();
		driver = startWebDriver.getDriver();
	}
	
	@After
	public void testCasePostCondtion(){
	}

	@Test
	public void debugConfigure() {
		StockRefillPage stockRefill = new StockRefillPage(driver);
		stockRefill.signIn("xdstest1", "Sport123");
		stockRefill.configureItems(6);
	}
	
	@Test
	public void testStockRefill() throws InterruptedException {
		StockRefillPage stockRefill = new StockRefillPage(driver);
		stockRefill.signIn("xdstest1", "Sport123");
		stockRefill.configureItems(6);
		stockRefill.waitOrderLoad();
		changeAddress(stockRefill);
		changeDDD(stockRefill);
		stockRefill.sendOrder();
	}

	private void changeAddress(StockRefillPage stockRefill) {
		stockRefill.uncheckAllOrderCheckboxes();
		stockRefill.checkOrderCheckbox(0);
		stockRefill.checkOrderCheckbox(1);
		stockRefill.setDeliveryAddress("ALUCAR");
		stockRefill.updateSelected();
	}

	private void changeDDD(StockRefillPage stockRefill) {
		stockRefill.uncheckAllOrderCheckboxes();
		stockRefill.checkOrderCheckbox(2);
		stockRefill.checkOrderCheckbox(3);
		stockRefill.setDueDeliveryDate("2015-06-15");
		stockRefill.updateSelected();
	}

}
