package com.scania.sport;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

	private static String signInUrlPrefix = "https://wamport.scania.com/pteai/logindmz?targeturl=";
	static Logger logger = Logger.getRootLogger();

	private final int CLIENT_CODE_STACK_INDEX;
	
	public static final long SLEEP = 500;
	public static final long DEFAULT_TIMEOUT = 10000;
	protected WebDriver driver;
	protected String url;

	
	public AbstractPage(WebDriver driver, String url) {
		this.driver = driver;
		this.url = url;
		// Finds out the index of "this code" in the returned stack trace - funny but it differs in JDK 1.5 and 1.6
	    int i = 0;
	    for(StackTraceElement ste : Thread.currentThread().getStackTrace()) {
	        i++;
	        if(ste.getClassName().equals(getClass().getName()))
	            break;
	    }
	    CLIENT_CODE_STACK_INDEX = i;

	    URL log4jConfig = getClass().getClassLoader().getResource("log4jConfiguration.xml");
		DOMConfigurator.configure(new File(log4jConfig.getFile()).getPath());
	}

	public void signIn(String username, String password) {
		driver.get(signInUrlPrefix+url);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("authenticateDMZ_userLoginData_password")).sendKeys(password);
		driver.findElement(By.id("authenticateDMZ_0")).click();
	}

	public void log(Object ... parameters) {
		StringBuilder logBuilder = new StringBuilder(getClass().getSimpleName()+"."+getCallerMethodName());
		if(parameters != null && parameters.length > 0) {
			StringBuilder parameterBuilder = new StringBuilder(": ");
			for(Object parameter : parameters) {
				if(parameterBuilder.length() > 0)
					parameterBuilder.append(", ");
				parameterBuilder.append(parameter.toString());
			}
			logBuilder.append(parameterBuilder.toString());
		}
		logger.info(logBuilder.toString());
	}

	public void log() {
		log(null);
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

	public String getCurrentMethodName() {
	    return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX].getMethodName();
	}
	
	public String getCallerMethodName() {
	    return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX+1].getMethodName();
	}
	
}
