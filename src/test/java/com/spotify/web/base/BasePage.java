package com.spotify.web.base;

import org.openqa.selenium.WebDriver;

public class BasePage {
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void navigateTo(String endPoint) {
		driver.get("https://open.spotify.com" + endPoint);
	}
	
}
