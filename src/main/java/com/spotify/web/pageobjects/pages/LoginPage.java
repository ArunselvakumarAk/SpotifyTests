package com.spotify.web.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import com.spotify.web.pageobjects.base.BasePage;
import com.spotify.web.utils.EventLogger;
import com.spotify.web.utils.ResourceUtils;

public class LoginPage extends BasePage{
	
	private By username_txt = By.id("login-username");
	private By password_txt = By.id("login-password");
	private By logInBtn = By.xpath("//span[text()='Log In']");
	private By web_player_btn = By.xpath("//span[text()='Web Player']");

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage enterUsername(String username) {
		driver.findElement(username_txt).sendKeys(username);
		return this;
	}
	
	public LoginPage enterPassword(String username) {
		driver.findElement(password_txt).sendKeys(username);
		return this;
	}
	
	public LoginPage clickLoginBtn() {
		driver.findElement(logInBtn).click();
		return this;
	}
	
	public LoginPage clickWebplayerBtn() {
		try {
			utils.waitForVisibilityOfElement(web_player_btn);
			driver.findElement(web_player_btn).click();
		}catch(NoSuchElementException | TimeoutException e) {
			EventLogger.info("Found captcha");
			EventLogger.info("Failing test intentionally");
			throw new IllegalStateException("Human authentication is required");
		}
		return this;
	}
	
	public LoginPage handleLogin(String username, String password) {
		utils.srollIntoView(driver.findElement(logInBtn));
		enterUsername(username).enterPassword(password).clickLoginBtn();
		clickWebplayerBtn();
		return this;
	}
	
	public LoginPage openPage() {
		driver.get(ResourceUtils.getProperty("webConfiguration//web_config.properties", "accountsUrl"));
		return this;
	}
		
}
