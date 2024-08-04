package com.spotify.web.base;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.driver.BrowserFactory;
import com.spotify.web.driver.Driver;
import com.spotify.web.driver.DriverManager;
import com.spotify.web.pageobjects.pages.LoginPage;
import com.spotify.web.utils.EventLogger;
import com.spotify.web.utils.ResourceUtils;


public class BaseTest {
		
	@BeforeMethod
	public void setUp(Method method) {
		
		EventLogger.startTestCase(method.getName());
		EventLogger.info("Setting up the test: " + method.getName());
		
		String browserNameFromConfig = ResourceUtils.getProperty("configuration//browser_config.properties", "browserName");
		String headlessValueFromCongif = ResourceUtils.getProperty("configuration//browser_config.properties", "headlessEnabled");
		
		Driver browserName = Driver.getDriverByName(System.getProperty("browser", browserNameFromConfig));
		boolean isHeadlessEnabled = Boolean.parseBoolean(System.getProperty("isHeadlessEnabled", headlessValueFromCongif));
		
		DriverManager.getInstance().setDriver(new BrowserFactory().createDriver(browserName, isHeadlessEnabled));
		
		EventLogger.info("Driver instantiated ");
		
		DriverManager.getInstance().getDriver().manage().window().maximize();
		EventLogger.info("Browser window maximized");
		
		if(method.isAnnotationPresent(AuthenticationRequired.class)) {
			EventLogger.info("Authentication required for the test: " + method.getName());
			login();
		}
	}
	
	@AfterMethod
	public void tearDown(ITestResult result, Method method) {
		EventLogger.info("Quitting the driver");		
		DriverManager.getInstance().quitDriver();
		EventLogger.info("Driver has been closed successfully");
		EventLogger.endTestCase(method.getName());
	}
	
	public void login() {
		EventLogger.info("Performing login");
		
		LoginPage login = new LoginPage(DriverManager.getInstance().getDriver());
		EventLogger.info("Navigating to Login Page");
		
		String username = ResourceUtils.getProperty("configuration//authentication_config.properties", "username");
		String password = ResourceUtils.getProperty("configuration//authentication_config.properties", "password");
		
		login
			.navigateToLoginPage()
			.handleLogin(username, password);
		
		EventLogger.info("Login handled successfully");
	}
}
