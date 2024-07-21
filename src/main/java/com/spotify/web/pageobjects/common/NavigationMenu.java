package com.spotify.web.pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.spotify.web.pageobjects.base.BasePage;

public class NavigationMenu extends BasePage{
	
	private By home_btn = By.xpath("//a[@aria-label='Home']//span[text()='Home']");
	private By search_btn = By.xpath("//span[contains(text(),'Search')]");
	
	public NavigationMenu(WebDriver driver) {
		super(driver);
	}
	
	public NavigationMenu clickSearchBtn() {
		driver.findElement(search_btn).click();
		return this;
	}
	
	public NavigationMenu clickHomeBtn() {
		driver.findElement(home_btn).click();
		return this;
	}
}
