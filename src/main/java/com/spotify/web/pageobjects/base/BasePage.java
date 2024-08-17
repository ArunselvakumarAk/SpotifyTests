package com.spotify.web.pageobjects.base;

import org.openqa.selenium.WebDriver;
import com.spotify.web.utils.ResourceUtils;
import com.spotify.web.utils.WebElementUtils;

public class BasePage{
	protected WebDriver driver;
	protected WebElementUtils utils;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		utils = new WebElementUtils(driver, 15);
	}
	
	public BasePage openPage() {
		driver.get(ResourceUtils.getProperty("webConfiguration//web_config.properties", "baseUrl"));
		return this;
	}
	
}
