package com.scania.saf;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.scania.saf.WebDrivers;

public class WebDriversTest {

	@Test(groups="webDrivers")
	public void testWebDrivers() {
		WebDriver driver = WebDrivers.getChrome();
		driver.close();
	}
	
}
