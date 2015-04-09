package com.scania.saf;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	public static final long SLEEP = 500;
	public static final long DEFAULT_TIMEOUT = 30000;
	protected WebDriver driver;
	protected String path;
	private WebDriverWait wait;
	
	public Page(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10000);
	}

	public void waitUntil(ExpectedCondition<?> condition) {
		Log.debug(condition);
		wait.until(condition);
	}

	public WebElement click(By by) {
		Log.debug(by);
		waitUntil(ExpectedConditions.elementToBeClickable(by));
		WebElement element = driver.findElement(by);
		element.click();
		return element;
	}

	public WebElement setValue(By by, String value) {
		Log.debug(value);
		WebElement element = click(by);
		element.clear();
		element.sendKeys(value);
		return element;
	}

	public void switchFrame(By by) {
		Log.debug(by);
		waitUntil(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}

	public String getText(By by) {
		Log.debug(by);
		waitUntil(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.findElement(by).getText();
	}

	public WebElement setElementValue(By by, String value) {
		Log.debug(by);
		WebElement element = waitClick(by);
		element.clear();
		element.sendKeys(value);
		return element;
	}
	
	public WebElement clickElement(By by) {
		Log.debug(by);
		WebElement element = driver.findElement(by);
		element.click();
		return element;
	}

	public void waitAsync() {
        String readyState = readyState();
        while(!"complete".equals(readyState)) {
        	sleep();
        	readyState = readyState();
        }
	}

	private String readyState() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
		return (String)executor.executeScript("return document.readyState");
	}
	
	public WebElement waitElement(By by) {
		return waitElement(by, DEFAULT_TIMEOUT);
	}

	public WebElement waitElement(By findBy, long timeout) {
		Log.debug(findBy);
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

	public WebElement waitClick(By by, long timeout) {
		Log.debug(by);
		WebElement element = null;
		Exception lastException = null;
		for(int i=0; i<timeout/SLEEP; i++)
			try {
				element = driver.findElement(by);
				element.click();
				return element;
			} catch(WebDriverException e) {
				lastException = e;
				sleep();
			}
		if(element == null && lastException != null)
			lastException.printStackTrace();
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

	public void waitAttributeEquals(WebElement element, String attribute, String value) {
		for(int i=0; i<DEFAULT_TIMEOUT/SLEEP; i++) {
			if(element.getAttribute(attribute).equals(value))
				return;
			sleep();
		}
	}

	public void waitAttributeNotEquals(WebElement element, String attribute, String value) {
		for(int i=0; i<DEFAULT_TIMEOUT/SLEEP; i++) {
			if(!element.getAttribute(attribute).contains(value))
				return;
			sleep();
		}
	}

	public void waitAttributeContains(WebElement element, String attribute, String value) {
		for(int i=0; i<DEFAULT_TIMEOUT/SLEEP; i++) {
			if(element.getAttribute(attribute).contains(value))
				return;
			sleep();
		}
	}

	public void waitAttributeNotContains(WebElement element, String attribute, String value) {
		for(int i=0; i<DEFAULT_TIMEOUT/SLEEP; i++) {
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
		Log.debug(scrollableBy, clickableBy);
		WebElement scrollable = waitElement(scrollableBy);
		int scrollableHeight = scrollable.getSize().getHeight();
		WebElement clickable = null;
		//boolean scrolled = false;
		while(true) {
			try {
				clickable = driver.findElement(clickableBy);
				clickable.click();
				break;
			} catch(Exception e) {
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollTop += arguments[1];", scrollable, scrollableHeight);
				//scrolled = true;
			}
		}
		//if(scrolled && clickable != null) // click might miss while scrolling
		//	clickable.click();
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
