package com.scania.saf.todo.common;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ScreenRegion;

import com.scania.saf.app.StartDesktopApp;
import com.scania.saf.app.StartWebDriver;
import com.scania.saf.config.ConfigureSaf;
import com.scania.saf.log.SafLog;
import com.scania.saf.selenium.FindWebDriverElement;
import com.scania.saf.selenium.JavaScriptCalls;
import com.scania.saf.selenium.OperateOnWebDriverElement;
import com.scania.saf.sikuli.SikuliKeyboardMgn;


public class CommonUtilities {

	public static ConfigureSaf configureSaf;
	public static StartDesktopApp startDesktopApplication;
	public static StartWebDriver startWebDriver;
	public static OperateOnWebDriverElement operateOnWebDriverElement;
	public static FindWebDriverElement findWebDriverElement;
	public static JavaScriptCalls javaScriptCalls;
	public static SikuliKeyboardMgn sikuliKeyboardMgn;
	public static ScreenRegion screenRegion;
	
	public static void quitDriver() {
		try {
			startWebDriver.getDriver().quit();
		} catch (Exception e) {
			//startWebDriver.getDriver().quit();
		}
	}

	public void loadConfigProperties() {
		/** Initiate log4j configuration and instantiate ConfigureSaf. */
		ClassLoader classLoader = getClass().getClassLoader();
		/** Get the language Excel file from class */
		File log4jConfigFile = new File(classLoader.getResource(
				"log4jConfiguration.xml").getFile());
		String log4jConfigFilePath = log4jConfigFile.getPath();
		DOMConfigurator.configure(log4jConfigFilePath);
	}

	/**
	 * Create an instance of the site driver by calling createDriver and
	 * createSite method the
	 */
	public void initiateSiteDriverAndPageInstance() {
		configureSaf = new ConfigureSaf();
		configureSaf.loadPropery();
		configureSaf.assignProperty();
		startDesktopApplication = new StartDesktopApp();
		startWebDriver = new StartWebDriver();
		findWebDriverElement = new FindWebDriverElement();
		operateOnWebDriverElement = new OperateOnWebDriverElement();
		operateOnWebDriverElement.setFindWebDriverElement(findWebDriverElement);
		javaScriptCalls = new JavaScriptCalls();
		operateOnWebDriverElement.setJavaScriptCalls(javaScriptCalls);
		sikuliKeyboardMgn = new SikuliKeyboardMgn();
		sikuliKeyboardMgn.setJavaScriptCalls(javaScriptCalls);
		screenRegion= new DesktopScreenRegion();
		sikuliKeyboardMgn.setScreenRegion(screenRegion);
		
		// findWhiteElements = new FindWhiteElements();
		// whiteKeybaordManagement = new WhiteKeybaordMgn();
		// sikuliKeyboardMgn = new SikuliKeyboardMgn();
	}

	public void StartTheWebApplication(String webApplicationURL) {
        SafLog.testStep(webApplicationURL);
        startWebDriver.createWebDriver();
        startWebDriver.GoToSite(webApplicationURL);
        findWebDriverElement.setDriver(startWebDriver.getDriver());
        findWebDriverElement.setJavaScriptCalls(javaScriptCalls);
        operateOnWebDriverElement.setFindWebDriverElement(findWebDriverElement);
        operateOnWebDriverElement.setJavaScriptCalls(javaScriptCalls);
        javaScriptCalls.setDriver(startWebDriver.getDriver());
    }

	public void logout() {
		SafLog.testStep();
	}

}
