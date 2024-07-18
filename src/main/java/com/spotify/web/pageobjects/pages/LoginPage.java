package com.spotify.web.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.spotify.web.pageobjects.base.BasePage;

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
		driver.findElement(web_player_btn).click();
		return this;
	}
	
	public LoginPage handleLogin(String username, String password) {
		enterUsername(username).enterPassword(password).clickLoginBtn().clickWebplayerBtn();
		return this;
	}
	
	public LoginPage navigateToLoginPage() {
		driver.get("https://accounts.spotify.com/en/login");
		return this;
	}
}
