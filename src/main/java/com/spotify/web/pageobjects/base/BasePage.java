package com.spotify.web.pageobjects.base;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{
	protected WebDriver driver;
	protected WebDriverWait wait=  null;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void navigateTo(String endPoint) {
		driver.get("https://open.spotify.com" + endPoint);
	}
	
	public void waitForPresenceOfElement(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitForElementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public boolean isElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		}catch(NoSuchElementException | TimeoutException e) {
			return false;
		}
	}
	
}
