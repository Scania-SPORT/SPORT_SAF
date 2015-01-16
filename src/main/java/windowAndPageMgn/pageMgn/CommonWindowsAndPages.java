package windowAndPageMgn.pageMgn;

import java.io.File;
import org.apache.log4j.xml.DOMConfigurator;

import windowAndPageMgn.pageMgn.Pages.LogInPage;
import windowAndPageMgn.pageMgn.Pages.QuotationsPage;
import windowAndPageMgn.pageMgn.Pages.SalesPortMainPage;
import windowAndPageMgn.pageMgn.Pages.StockRefill;
import configMgn.ConfigureSaf;
import elementmanagement.seleniumWebDriverElementMgn.FindWebDriverElement;
import elementmanagement.seleniumWebDriverElementMgn.JavaScriptCalls;
import elementmanagement.seleniumWebDriverElementMgn.OperateOnWebDriverElement;
import appMgn.StartDesktopApp;
import appMgn.StartWebDriver;


public class CommonWindowsAndPages {

	public static ConfigureSaf configureSaf;
	public static StartDesktopApp startDesktopApplication;
	public static StartWebDriver startWebDriver;
	public static OperateOnWebDriverElement operateOnWebDriverElement;
	public static FindWebDriverElement findWebDriverElement;
	public static JavaScriptCalls javaScriptCalls;

	
	public static LogInPage logInPage;
	public static SalesPortMainPage salesPortMainPage;
	public static QuotationsPage quotationsPage;
	public static StockRefill stockRefill;
	// public static OperateOnWhiteElements operateOnWhiteElements;
	// private static SikuliKeyboardMgn sikuliKeyboardMgn;
	// private static FindWhiteElements findWhiteElements;
		 
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
		logInPage = new LogInPage();
		// findWhiteElements = new FindWhiteElements();
		// whiteKeybaordManagement = new WhiteKeybaordMgn();
		// sikuliKeyboardMgn = new SikuliKeyboardMgn();
	}

	public void initWindowsAndPages() {
		logInPage = new LogInPage();
		salesPortMainPage = new SalesPortMainPage();
		quotationsPage = new QuotationsPage();
		stockRefill = new StockRefill();

	}
}
