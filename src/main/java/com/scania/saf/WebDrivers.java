package com.scania.saf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

	private static String initDriver(String driverName) {
		String sourcePath = findSourcePath();
		System.out.println("Loading WebDrivers from: "+sourcePath);
		String targetDirectory = findTargetDirectory();
		File sourceFile = new File(sourcePath);
		if(sourceFile.isDirectory())
			return fileName(sourcePath, driverName);
		return conditionalCopyFromJar(driverName, sourceFile, targetDirectory);
	}

	private static String conditionalCopyFromJar(String driverName, File sourceFile, String targetDirectory) {
		System.out.println("Storing WebDrivers in: "+targetDirectory);
		File driverFile = new File(fileName(targetDirectory, driverName));
		if(!driverFile.exists())
			copyDriverFromJar(driverName, sourceFile, targetDirectory);
		return driverFile.getAbsolutePath();
	}

	private static void copyDriverFromJar(String driverName, File source, String targetPath) {
		JarFile jar = null;
		try {
			jar = new JarFile(source);
			Enumeration<? extends JarEntry> entries = jar.entries();
	        while (entries.hasMoreElements()) {
	            JarEntry entry = entries.nextElement();
	            if (!entry.isDirectory() && entry.getName().equals(driverName)) {
	                BufferedInputStream inputStream = new BufferedInputStream(jar.getInputStream(entry));
	                File targetFile = new File(targetPath+driverName);
	                FileOutputStream targetStream = new FileOutputStream(targetFile);
	                BufferedOutputStream outputStream = new BufferedOutputStream(targetStream);
	                copyStream(inputStream, outputStream);
	                inputStream.close();
	                outputStream.close();
	                return;
	            }
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(jar != null)
					jar.close();
			} catch(Exception e) {
				
			}
		}
	}

	private static void copyStream(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[1024]; // Adjust if you want
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1)
			output.write(buffer, 0, bytesRead);
	}

	private static String fileName(String targetDirectory, String driverName) {
		return targetDirectory+driverName;
	}

	private static String findTargetDirectory() {
		try {
			return findClassPath(Class.forName(getCallerClassName()));
		} catch(ClassNotFoundException e) {
			return null;
		}
	}

	private static String getCallerClassName() {
		return Stack.getStackTraceElement(4).getClassName();
	}
	
	private static String findSourcePath() {
		return findClassPath(WebDrivers.class);
	}

	private static String findClassPath(Class<?> type) {
		String path = type.getProtectionDomain().getCodeSource().getLocation().toString();
		if(path.startsWith(FILE_PREFIX))
			path = path.substring(FILE_PREFIX.length());
		return path;
	}

}
