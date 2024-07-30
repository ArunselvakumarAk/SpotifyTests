package com.spotify.web.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.driver.BrowserFactory;
import com.spotify.web.driver.Driver;
import com.spotify.web.driver.DriverManager;
import com.spotify.web.pageobjects.pages.LoginPage;
import com.spotify.web.utils.ResourceUtils;


public class BaseTest {
		
	@BeforeMethod
	public void setUp(Method method) {
		
		ResourceUtils.log.info("Setting up the test: " + method.getName());
		
		String browserNameFromConfig = ResourceUtils.getProperty("configuration//browser_config.properties", "browserName");
		String headlessValueFromCongif = ResourceUtils.getProperty("configuration//browser_config.properties", "headlessEnabled");
		
		Driver browserName = Driver.getDriverByName(System.getProperty("browser", browserNameFromConfig));
		boolean isHeadlessEnabled = Boolean.parseBoolean(System.getProperty("isHeadlessEnabled", headlessValueFromCongif));
		
		DriverManager.getInstance().setDriver(new BrowserFactory().createDriver(browserName, isHeadlessEnabled));
		
		ResourceUtils.log.info("Driver instantiated: " + DriverManager.getInstance().getDriver().toString());
		
		DriverManager.getInstance().getDriver().manage().window().maximize();
		ResourceUtils.log.info("Browser window maximized");
		
		if(method.isAnnotationPresent(AuthenticationRequired.class)) {
			ResourceUtils.log.info("Authentication required for the test: " + method.getName());
			login();
		}
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		ResourceUtils.log.info("Quitting the driver");
				
		DriverManager.getInstance().quitDriver();
		ResourceUtils.log.info("Driver quitted successfully");
	}
	
	public void login() {
		ResourceUtils.log.info("Performing login");
		
		LoginPage login = new LoginPage(DriverManager.getInstance().getDriver());
		ResourceUtils.log.info("Navigating to Login Page");
		
		String username = ResourceUtils.getProperty("configuration//authentication_config.properties", "username");
		String password = ResourceUtils.getProperty("configuration//authentication_config.properties", "password");
		
		login
			.navigateToLoginPage()
			.handleLogin(username, password);
		
		ResourceUtils.log.info("Login handled successfully");
	}
	
	public File takeScreenshot(String methodName) {
		
		System.out.println(DriverManager.getInstance().getDriver());
        File scrFile = ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        String filePath = "target"+ File.separator+"screenshots"+File.separator + methodName + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(filePath));
            ResourceUtils.log.info("Screenshot saved: " + filePath);
        } catch (IOException e) {
            ResourceUtils.log.error("Failed to save screenshot: ", e);
        }
        return scrFile;
    }
}
