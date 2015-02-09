package com.scania.saf;

import org.openqa.selenium.WebDriver;

public class WebDriversDemo {

	public static void main(String[] args) {
		WebDriver driver = WebDrivers.getChrome();
		driver.close();
	}
	
}
