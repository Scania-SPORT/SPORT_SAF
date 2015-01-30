package appMgn;


import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import logMgn.SafLog;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import commonFuncMgn.OtherFunctionality;
import commonFuncMgn.ScreenShot;

import configMgn.ConfigureSaf;

import parametermanagement.OtherParameters;

/**
 * Provide functionality to create a driver, of type IE, FireFox or HTMLUNIT and navigate to a site for a given url.
 * @author GKHEKP
 *
 */
public class StartWebDriver{

private WebDriver driver;
	
/**
 *Create a webdriver of type IE, FireFox or HTMLUNIT, depending on the ConfigureSaf.SAF_DRIVER value.
 *
 */
	public void createWebDriver() {	
		
		//////////////////////////////////////////////////////
		//////////////////FIREFOX DRIVER//////////////////////
		if (ConfigureSaf.SAF_DRIVER.contentEquals(OtherParameters.FIREFOXDRIVER)) {
			ProfilesIni profilesIni = new ProfilesIni();
			FirefoxProfile profile = profilesIni.getProfile("default");
			// driver= new FirefoxDriver(profile);
			if (ConfigureSaf.SAF_GRID) {

				try {
					driver = new RemoteWebDriver(new URL("http://ip-0a31350c:4444/wd/hub"),	DesiredCapabilities.firefox());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else
				driver = new FirefoxDriver(profile);
		}
		
		// ////////////////////////////////////////////////////
		// ////////////////CHROME DRIVER//////////////////////
		if (ConfigureSaf.SAF_DRIVER.contentEquals(OtherParameters.CHROMEDRIVER)) {
			// driver= new FirefoxDriver(profile);
			if (ConfigureSaf.SAF_GRID) {

				try {
					driver = new RemoteWebDriver(new URL("http://ip-0a31350c:4444/wd/hub"),DesiredCapabilities.chrome());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else{
				String path=OtherFunctionality.getFullPath("src\\main\\resources\\externaltools\\selenium");
			
			String chromeDriverExeFilePath=path +"chromedriver.exe";
			
			System.setProperty("webdriver.chrome.driver",chromeDriverExeFilePath);
			ChromeOptions options = new ChromeOptions();
	        options.addArguments("--test-type");  
	        options.addArguments("start-maximized");
				driver = new ChromeDriver(options);
			}
		}
		
		//////////////////////////////////////////////////////
		//////////////////INTERNET EXPLORER DRIVER//////////////////////
		if (ConfigureSaf.SAF_DRIVER.contentEquals(OtherParameters.INTERNETEXPLORERDRIVER)) {
			// driver= new FirefoxDriver(profile);
			if (ConfigureSaf.SAF_GRID) {

				try {
					driver = new RemoteWebDriver(new URL("http://ip-0a31350c:4444/wd/hub"),	DesiredCapabilities.internetExplorer());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else{
				String path=OtherFunctionality.getFullPath("src\\main\\resources\\externaltools\\selenium");
				
				String iEDriverExeFilePath=path +"IEDriverServer.exe";
				
				System.setProperty("webdriver.ie.driver",iEDriverExeFilePath);
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();   
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability("ACCEPT_SSL_CERTS", true);
				driver = new InternetExplorerDriver(capabilities);	
			}
		}
		
		//////////////////////////////////////////////////////
		//////////////////INTERNET EXPLORER DRIVER//////////////////////
		if (ConfigureSaf.SAF_DRIVER.contentEquals(OtherParameters.HTMLUNITDRIVER)) {
		// driver= new FirefoxDriver(profile);
		if (ConfigureSaf.SAF_GRID) {
		
				try {
					driver = new RemoteWebDriver(new URL("http://ip-0a31350c:4444/wd/hub"),DesiredCapabilities.htmlUnit());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else {
				driver = new HtmlUnitDriver();

			}
		}	

		//////////////////////////////////////////////////////
		//////////////////FIREFOX DRIVER//////////////////////
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenResolution = new Dimension((int) toolkit.getScreenSize().getWidth(), (int) toolkit.getScreenSize().getHeight());
		//driver.manage().window().setPosition(new Point(0, 0));
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * Navigate to a site for a given URL 
	 * @param uRLForSiteToOpen
	 */
	public void GoToSite(String uRLForSiteToOpen)
    {
        SafLog.debug(uRLForSiteToOpen);
        driver.get(uRLForSiteToOpen);
    }
}

