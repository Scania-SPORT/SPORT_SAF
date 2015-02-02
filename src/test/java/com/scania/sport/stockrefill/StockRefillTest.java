package com.scania.sport.stockrefill;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scania.WebDrivers;

public class StockRefillTest {

	private StockRefillPage stockRefill;
	
	@BeforeTest
	public void setUp() {
		WebDriver driver = WebDrivers.getChrome();
		stockRefill = new StockRefillPage(driver);
	}
	
	@Test //http://jira/browse/SPORT-4685
	public void test_SPORT4685_stockRefillQty6Split() throws InterruptedException {
		stockRefill.signIn("xdstest1", "Sport123");
		stockRefill.configureItems(6);
		stockRefill.waitOrderLoad();
		changeSplitAddresses(stockRefill);
		changeSplitDDDs(stockRefill);
		stockRefill.sendOrder();
		
		List<WebElement> sentItems = stockRefill.getOrderSentItems();
		Assert.assertEquals(sentItems.size(), 2);
	}

	@Test //http://jira/browse/SPORT-4686
	public void test_SPORT4686_stockRefillQty1() throws InterruptedException {
		stockRefill.signIn("xdstest1", "Sport123");
		stockRefill.configureItems(1);
		stockRefill.waitOrderLoad();
		stockRefill.setDeliveryAddress("AUTOEGA");
		stockRefill.updateSelected();
		stockRefill.sendOrder();
		
		List<WebElement> sentItems = stockRefill.getOrderSentItems();
		Assert.assertEquals(sentItems.size(), 1);
	}

	private void changeSplitAddresses(StockRefillPage stockRefill) {
		stockRefill.setDeliveryAddress("AUTOEGA");
		stockRefill.updateSelected();
		stockRefill.toggleAllOrderCheckboxes();
		stockRefill.toggleOrderCheckbox(0);
		stockRefill.toggleOrderCheckbox(1);
		stockRefill.setDeliveryAddress("ALUCAR");
		stockRefill.updateSelected();
	}

	private void changeSplitDDDs(StockRefillPage stockRefill) {
		stockRefill.toggleOrderCheckbox(0);
		stockRefill.toggleOrderCheckbox(1);
		stockRefill.toggleOrderCheckbox(2);
		stockRefill.toggleOrderCheckbox(3);
		stockRefill.setDueDeliveryDate("2015-06-15");
		stockRefill.updateSelected();
	}
	
	@AfterTest
	public void tearDown() {
		stockRefill.close();
	}

}
