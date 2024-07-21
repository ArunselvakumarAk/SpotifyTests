package com.spotify.web.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementUtils {
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private TakesScreenshot screenshot;
	
	public WebElementUtils(WebDriver driver,int timeOutInSeconds ) {
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		this.js = (JavascriptExecutor) driver ;
		this.screenshot= (TakesScreenshot) driver;
	}
	
	public void waitForElementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void waitForVisibilityOfElement(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForVisibilityOfAllElements(By locator) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public boolean isElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		}catch(NoSuchElementException | TimeoutException e) {
			return false;
		}
	}
	
	public void srollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
}
