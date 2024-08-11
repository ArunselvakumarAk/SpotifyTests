package com.spotify.web.pageobjects.pages;

import org.openqa.selenium.WebDriver;

import com.spotify.web.pageobjects.base.BasePage;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public HomePage openPage() {
		openPage();
		return this;
	}
}
