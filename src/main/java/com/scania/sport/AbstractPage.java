package com.scania.sport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

	public static final long SLEEP = 500;
	public static final long DEFAULT_TIMEOUT = 10000;
	protected WebDriver driver;
	protected String url;
	private static String signInUrlPrefix = "https://wamport.scania.com/pteai/logindmz?targeturl=";
	
	public AbstractPage(WebDriver driver, String url) {
		this.driver = driver;
		this.url = url;
	}

	public void signIn(String username, String password) {
		driver.get(signInUrlPrefix+url);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("authenticateDMZ_userLoginData_password")).sendKeys(password);
		driver.findElement(By.id("authenticateDMZ_0")).click();
	}
	
	public void close() {
		driver.close();
	}
	
	public String getUrl() {
		return url;
	}

	public void waitClickXpath(String xpath) {
		waitClick(By.xpath(xpath), DEFAULT_TIMEOUT);
	}

	public void waitClickId(String id) {
		waitClick(By.id(id), DEFAULT_TIMEOUT);
	}
	
	public void waitClick(By findBy, long timeout) {
		WebElement element = waitElement(findBy, timeout);
		for(int i=0; i<timeout/SLEEP; i++)
			try {
				element.click();
				return;
			} catch(WebDriverException e) {
				sleep();
			}
	}
	
	public void sleep() {
		sleep(SLEEP);
	}
	
	public void sleep(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			//
		}
	}
	
	public WebElement waitElement(By findBy, long timeout) {
		WebElement element = null;
		Exception lastException = null;
		for(int i=0; i<timeout/SLEEP; i++) {
			try {
				return driver.findElement(findBy);
			} catch(org.openqa.selenium.NoSuchElementException e) {
				lastException = e;
				sleep();
			}
		}
		if(element == null && lastException != null)
			lastException.printStackTrace();
		return element;
	}

	public void waitClear(WebElement element) {
		for(int i=0; i<DEFAULT_TIMEOUT/SLEEP; i++)
			try {
				element.clear();
				return;
			} catch(WebDriverException e) {
				sleep();
			}
	}

	public void waitAttributeEquals(WebElement element, String attribute, String value, long timeout) {
		for(int i=0; i<timeout/SLEEP; i++) {
			if(element.getAttribute(attribute).equals(value))
				return;
			sleep();
		}
	}

	public void waitAttributeNotEquals(WebElement element, String attribute, String value, long timeout) {
		for(int i=0; i<timeout/SLEEP; i++) {
			if(!element.getAttribute(attribute).contains(value))
				return;
			sleep();
		}
	}

	public void waitAttributeContains(WebElement element, String attribute, String value, long timeout) {
		for(int i=0; i<timeout/SLEEP; i++) {
			if(element.getAttribute(attribute).contains(value))
				return;
			sleep();
		}
	}

	public void waitAttributeNotContains(WebElement element, String attribute, String value, long timeout) {
		for(int i=0; i<timeout/SLEEP; i++) {
			if(!element.getAttribute(attribute).contains(value))
				return;
			sleep();
		}
	}
	
	public void tick() {
		sleep(1);
	}
	
}
