package com.scania;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import commonFuncMgn.OtherFunctionality;

public class WebDrivers {

	public static WebDriver getFirefox() {
		ProfilesIni profilesIni = new ProfilesIni();
		FirefoxProfile profile = profilesIni.getProfile("default");
		return new FirefoxDriver(profile);
	}
	
	public static WebDriver getChrome() {
		String path = OtherFunctionality.getFullPath("src\\main\\resources\\externaltools\\selenium") + "chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");  
        options.addArguments("start-maximized");
		return new ChromeDriver(options);
	}

	public static WebDriver getInternetExplorer() {
		String path = OtherFunctionality.getFullPath("src\\main\\resources\\externaltools\\selenium") + "IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", path);
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();   
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("ACCEPT_SSL_CERTS", true);
		return new InternetExplorerDriver(capabilities);	
	}
	
}
