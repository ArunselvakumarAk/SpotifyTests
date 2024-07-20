package com.spotify.web.base;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.driver.Driver;
import com.spotify.web.driver.WebDriverManager;
import com.spotify.web.pageobjects.pages.LoginPage;
import com.spotify.web.utils.ResourceUtils;

public class BaseTest {
	
	public WebDriver driver; 
	
	@BeforeMethod
	public void setUp(Method method) {
		
		ResourceUtils.log.info("Setting up the test: " + method.getName());
		
		String browserName = ResourceUtils.getProperty("configuration//browser_config.properties", "browserName");
		ResourceUtils.log.info("Browser name from properties: " + browserName);
		
		driver = WebDriverManager.getInstance(Driver.getDriverByName(System.getProperty("browser", browserName))).getDriver();
		ResourceUtils.log.info("Driver instantiated: " + driver.toString());
		
		driver.manage().window().maximize();
		ResourceUtils.log.info("Browser window maximized");
		
		if(method.isAnnotationPresent(AuthenticationRequired.class)) {
			ResourceUtils.log.info("Authentication required for the test: " + method.getName());
			login();
		}
	}
	
	@AfterMethod
	public void tearDown() {
		ResourceUtils.log.info("Quitting the driver");
		WebDriverManager.quitDriver();
		ResourceUtils.log.info("Driver quited successfully");
	}
	
	public void login() {
		ResourceUtils.log.info("Performing login");
		
		LoginPage login = new LoginPage(driver);
		ResourceUtils.log.info("Navigating to Login Page");
		
		String username = ResourceUtils.getProperty("configuration//authentication_config.properties", "username");
		String password = ResourceUtils.getProperty("configuration//authentication_config.properties", "password");
		
		login
			.navigateToLoginPage()
			.handleLogin(username, password);
		
		ResourceUtils.log.info("Login handled successfully");
	}

}
