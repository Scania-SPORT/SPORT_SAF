package com.scania.salesportal.stockrefill;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scania.salesportal.stockrefill.StockRefillPage;

public class StockRefillTest {

	private WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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
