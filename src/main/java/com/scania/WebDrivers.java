package com.scania;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDrivers {

	private static final String FILE_PREFIX = "file:/";
	private static final int CLIENT_CODE_STACK_INDEX;
	
	static {
		// Finds out the index of "this code" in the returned stack trace - funny but it differs in JDK 1.5 and 1.6
	    int i = 0;
	    for(StackTraceElement ste : Thread.currentThread().getStackTrace()) {
	        i++;
	        if(ste.getClassName().equals(WebDrivers.class.getName()))
	            break;
	    }
	    CLIENT_CODE_STACK_INDEX = i;
	}

	public static String getCallerClassName(int i) {
	    return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX+i].getClassName();
	}
	
	public static String initDriver(String driverName) {
		String sourcePath = findSourcePath();
		String targetDirectory = findTargetDirectory(2);
		File sourceFile = new File(sourcePath);
		System.out.println("isDirectory: "+sourceFile.isDirectory());
		if(sourceFile.isDirectory())
			return fileName(sourcePath, driverName);
		return conditionalCopyFromJar(driverName, sourceFile, targetDirectory);
	}

	private static String conditionalCopyFromJar(String driverName, File sourceFile, String targetDirectory) {
		System.out.println("targetDirectory: "+targetDirectory);
		File driverFile = new File(fileName(targetDirectory, driverName));
		if(!driverFile.exists())
			copyDriverFromJar(driverName, sourceFile, targetDirectory);
		return driverFile.getAbsolutePath();
	}

	private static String fileName(String targetDirectory, String driverName) {
		return targetDirectory+driverName;
	}

	public static String findTargetDirectory(int i) {
		try {
			return findClassPath(Class.forName(getCallerClassName(i)));
		} catch(ClassNotFoundException e) {
			return null;
		}
	}

	public static String findSourcePath() {
		return findClassPath(WebDrivers.class);
	}

	public static String findClassPath(Class<?> type) {
		String path = type.getProtectionDomain().getCodeSource().getLocation().toString();
		if(path.startsWith(FILE_PREFIX))
			path = path.substring(FILE_PREFIX.length());
		return path;
	}

	private static void copyDriverFromJar(String driverName, File source, String targetPath) {
		try {
			JarFile jar = new JarFile(source);
			Enumeration<? extends JarEntry> entries = jar.entries();

	        while (entries.hasMoreElements()) {
	            JarEntry entry = entries.nextElement();
	            if (!entry.isDirectory() && entry.getName().equals(driverName)) {
	                BufferedInputStream inputStream = new BufferedInputStream(jar.getInputStream(entry));
	                
	            }
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void copyStream(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[1024]; // Adjust if you want
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1)
			output.write(buffer, 0, bytesRead);
	}

	public static WebDriver getFirefox() {
		ProfilesIni profilesIni = new ProfilesIni();
		FirefoxProfile profile = profilesIni.getProfile("default");
		return new FirefoxDriver(profile);
	}

	public static WebDriver getChrome() {
		String path = initDriver("chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("start-maximized");
		return new ChromeDriver(options);
	}

	public static WebDriver getInternetExplorer() {
		String path = initDriver("IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", path);
		DesiredCapabilities capabilities = DesiredCapabilities
				.internetExplorer();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("ACCEPT_SSL_CERTS", true);
		return new InternetExplorerDriver(capabilities);
	}

}
