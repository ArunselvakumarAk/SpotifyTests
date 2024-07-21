package com.spotify.web.pageobjects.base;

import org.openqa.selenium.WebDriver;
import com.spotify.web.utils.ResourceUtils;
import com.spotify.web.utils.WebElementUtils;

public class BasePage{
	protected WebDriver driver;
	protected WebElementUtils utils;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		utils = new WebElementUtils(driver, 10);
	}
	
	public BasePage openPage(String endPoint) {
		driver.get(ResourceUtils.getProperty("configuration//web_config.properties", "baseUrl") + endPoint);
		return this;
	}
	
}
