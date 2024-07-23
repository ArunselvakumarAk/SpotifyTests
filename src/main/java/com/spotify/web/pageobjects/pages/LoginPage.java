package com.spotify.web.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.spotify.web.pageobjects.base.BasePage;
import com.spotify.web.utils.ResourceUtils;

public class LoginPage extends BasePage{
	
	private By username_txt = By.id("login-username");
	private By password_txt = By.id("login-password");
	private By logInBtn = By.xpath("//span[text()='Log In']");
	private By web_player_btn = By.xpath("//span[text()='Web Player']");
	private By verifyCaptcha_chkbox = By.xpath("//div[@class='recaptcha-checkbox-border']");
	private By confirmCaptcha_btn = By.xpath("//span[text()='Continue']");

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
			ResourceUtils.log.info("Found captcha");
			clickVerifyCaptchaChkbox();
			clickConfirmCaptchaBtn();
			ResourceUtils.log.info("Captcha handled");
		}
		return this;
	}
	
	public LoginPage handleLogin(String username, String password) {
		utils.srollIntoView(driver.findElement(logInBtn));
		enterUsername(username).enterPassword(password).clickLoginBtn();
		clickWebplayerBtn();
		return this;
	}
	
	public LoginPage navigateToLoginPage() {
		driver.get(ResourceUtils.getProperty("configuration//web_config.properties", "accountsUrl"));
		return this;
	}
		
	private LoginPage clickVerifyCaptchaChkbox() {
		driver.findElement(verifyCaptcha_chkbox).click();
		return this;
	}
	private LoginPage clickConfirmCaptchaBtn() {
		driver.findElement(confirmCaptcha_btn).click();
		return this;
	}
}
