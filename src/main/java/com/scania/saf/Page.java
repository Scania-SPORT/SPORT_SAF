package com.scania.saf;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class Page {

	public static final long SLEEP = 500;
	public static final long DEFAULT_TIMEOUT = 10000;
	protected WebDriver driver;
	protected String path;
	
	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public void setElementValue(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
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

	public WebElement waitElement(By by) {
		return waitElement(by, DEFAULT_TIMEOUT);
	}

	public WebElement waitClick(By by, long timeout) {
		for(int i=0; i<timeout/SLEEP; i++)
			try {
				WebElement element = driver.findElement(by);
				element.click();
				return element;
			} catch(WebDriverException e) {
				sleep();
			}
		return null;
	}

	public WebElement waitClick(By findBy) {
		return waitClick(findBy, DEFAULT_TIMEOUT);
	}
	
	public void waitClear(WebElement element) {
		for(int i=0; i<DEFAULT_TIMEOUT/SLEEP; i++)
			try {
				element.click();
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

	public void waitClickContentContains(String string) {
		WebElement element = waitElement(By.xpath(".//*[contains(text(), '"+string+"')]"));
		element.click();
	}

	public WebElement scrollClick(By scrollableBy, By clickableBy) {
		WebElement scrollable = waitElement(scrollableBy);
		int scrollableHeight = scrollable.getSize().getHeight();
		WebElement clickable = null;
		while(true) {
			try {
				clickable = driver.findElement(clickableBy);
				clickable.click();
				break;
			} catch(Exception e) {
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollTop += arguments[1];", scrollable, scrollableHeight);
			}
		}
		if(clickable != null)
			clickable.click();
		return clickable;
	}

	public void sleep(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			//
		}
	}

	public void sleep() {
		sleep(SLEEP);
	}

	public void tick() {
		sleep(1);
	}

	public void close() {
		Log.info(getClass().getSimpleName());
		driver.close();
	}

}
