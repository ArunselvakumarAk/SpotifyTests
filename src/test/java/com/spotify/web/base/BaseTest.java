package com.spotify.web.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.spotify.web.driver.Driver;
import com.spotify.web.driver.DriverManager;
import com.spotify.web.pageobjects.pages.LoginPage;

public class BaseTest {
	protected WebDriver driver;
	
	@BeforeMethod(enabled=true)
	public void setUp() {
		driver = new DriverManager().createDriver(Driver.getDriverByName(System.getProperty("browser")));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		login();		
	}
	
	@AfterMethod(enabled=true)
	public void tearDown() {
		driver.quit();
	}
	
	public void login() {
		LoginPage login = new LoginPage(driver);
		
		login
			.navigateToLoginPage()
			.handleLogin("31c7nnw6yqbzysucb5w45i6g6zjy", "UserPassword008");
	}

}
