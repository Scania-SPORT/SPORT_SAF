package com.scania.sport.stockrefill;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.scania.sport.AbstractPage;

public class EcmFramePage extends AbstractPage {

	public EcmFramePage(WebDriver driver) {
		super(driver, null);
		driver.switchTo().frame(waitElement(By.xpath("//*[@id=\"tCSiteWidget_form_defaultStockRefillWorkspaceWidget_StockRefillDetailsWidget_TCSiteWidget\"]/div/iframe"), DEFAULT_TIMEOUT));
	}
	
	public void lockModel() {
		waitClickId("parameter-F59998Field-F59998A-radio");
	}

	public void details() {
		waitClickXpath("//*[@id=\"configure-steps\"]/div/div[1]/table/tbody/tr/td[2]/div");
	}
	
	public void done() {
		driver.switchTo().defaultContent();
		waitClickId("dijit_form_Button_25_label");
	}

	public void searchAndSelectFupDevice() {
		waitClickId("search_input");
		WebElement element = waitElement(By.id("search_input"), DEFAULT_TIMEOUT);
		element.sendKeys("2398");
		waitClickId("search_result_index_2");
		waitClickId("parameter-F02398Field-F02398B-radio");
		waitClickId("close_button");
	}

}
