package com.spotify.web.pageobjects.pages;

import org.openqa.selenium.WebDriver;

import com.spotify.web.base.BasePage;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public HomePage navigateToHomePage() {
		navigateTo("");
		return this;
	}
}
