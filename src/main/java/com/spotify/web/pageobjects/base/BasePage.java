package com.spotify.web.pageobjects.base;

import org.openqa.selenium.WebDriver;

import com.spotify.web.utils.WebElementUtils;
import com.spotify.web.utils.ResourceUtils;

public class BasePage{
	protected WebDriver driver;
	protected WebElementUtils wait;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		wait = new WebElementUtils(driver, 10);
	}
	
	public void navigateTo(String endPoint) {
		driver.get(ResourceUtils.getProperty("configuration//web_config.properties", "baseUrl") + endPoint);
	}
		
}
