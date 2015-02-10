package com.scania.saf;

import org.openqa.selenium.By;

public class XPath {

	public static By contentContains(String string) {
		return By.xpath(contentContainsString(string));
	}

	public static String contentContainsString(String string) {
		return ".//*[contains(text(), '"+string+"')]";
	}

}
