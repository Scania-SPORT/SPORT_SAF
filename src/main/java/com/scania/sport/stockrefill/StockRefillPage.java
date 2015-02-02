package com.scania.sport.stockrefill;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.scania.sport.AbstractPage;

public class StockRefillPage extends AbstractPage {

	public StockRefillPage(WebDriver driver) {
		super(driver, "https://acc-salesportal.scania.com/wps/myportal/Home/stockrefill/");
	}
	
	public void createNewRefillOrder() {
		waitClickXpath("//*[@id=\"stockRefillWidget_defaultStockRefillWorkspaceWidget\"]/div[3]/div/div[1]/div/span[1]/span");
		WebElement element = waitElement(By.id("dijit_form_FilteringSelect_0"), DEFAULT_TIMEOUT);
		element.clear();
		element.sendKeys("Vilnius, UAB Scania Lietuva (LT10)");
		waitClickXpath("//*[@id=\"dijit_TooltipDialog_0\"]/div[1]/div/div/div[2]/span[1]/span");
	}
	
	public void setQty(int qty) {
		WebElement textBox = driver.findElement(By.id("sport/extensions/SportNumberTextBox_0"));
		tick();
		waitClear(textBox);
		tick();
		textBox.sendKeys(Integer.toString(qty));
		sleep();
	}

	public void insert() {
		waitClickXpath("//*[@id=\"dijit_form_ComboButton_0\"]/tbody/tr/td[1]");
	}

	public void details() {
		waitClickXpath("//*[@id=\"dijit_form_Button_23_label\"]/span");
	}

	public EcmFramePage configure() {
		waitClickXpath("//*[@id=\"configurationWidget_form_defaultStockRefillWorkspaceWidget_StockRefillDetailsWidget_ConfigurationWidget\"]/div[2]/span[1]/span");
		return new EcmFramePage(driver);
	}

	public void order() {
		WebElement orderButton = waitElement(By.xpath("//*[@id=\"productDetailsWidget_defaultStockRefillWorkspaceWidget\"]/div[1]/div[2]/div/div[2]/div/div/div/div[4]/div[6]/div/span"), SLEEP);
		waitAttributeNotContains(orderButton, "class", "dijitButtonDisabled", DEFAULT_TIMEOUT);
		orderButton.click();
	}

	public void toggleAllOrderCheckboxes() {
		waitClickXpath("//*[@id=\"dgrid_6-header\"]/tr/th[1]");
	}

	public void toggleOrderCheckbox(int index) {
		waitClickXpath("//*[@id=\"dgrid_6\"]/div[2]/div/div["+(index+2)+"]/table/tr/td[1]");
	}
	
	public void waitOrderLoad() {
		WebElement element = waitElement(By.xpath("//*[@id=\"sport_extensions_SportStandby_4\"]/div[1]"), DEFAULT_TIMEOUT);
		waitAttributeNotContains(element, "style", "z-index: 1042", DEFAULT_TIMEOUT);
	}

	public void configureItems(int qty) {
		createNewRefillOrder();
		sleep(1500);
		setQty(qty);
		insert();
		details();
		EcmFramePage ecm = configure();
		ecm.lockModel();
		sleep();
		ecm.details();
		sleep();
		ecm.searchAndSelectFupDevice();
		ecm.done();
		order();
	}

	public void setDeliveryAddress(String deliveryAddress) {
		WebElement element = waitElement(By.id("dijit_form_ValidationTextBox_2"), DEFAULT_TIMEOUT);
		element.clear();
		element.sendKeys(deliveryAddress);
		applyByChangingFocusClick();
		sleep();
	}

	private void applyByChangingFocusClick() {
		waitClickXpath("//*[@id=\"dijit_form_Form_3\"]/table/thead/tr[1]/th[2]");
	}
	
	public void updateSelected() {
		waitClickId("dijit_form_Button_17_label");
		sleep();
	}

	public void setDueDeliveryDate(String dueDeliveryDate) {
		//*[@id="sport/extensions/SportDateTextBox_0"]
		waitElement(By.id("sport/extensions/SportDateTextBox_0"), DEFAULT_TIMEOUT).sendKeys(dueDeliveryDate);
	}

	public void sendOrder() {
		waitClickId("dijit_form_Button_19_label");
		waitClickId("dijit_form_Button_29_label"); //confirm
	}

	public List<WebElement> getOrderSentItems() {
		waitElement(By.id("dgrid_7-row-0"), DEFAULT_TIMEOUT);
		return driver.findElements(By.xpath("//*[@id=\"dgrid_7\"]/div[2]/div/div"));		
	}
	
}