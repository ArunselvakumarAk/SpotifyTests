package com.spotify.web.base;

import com.spotify.web.pageobjects.pages.LoginPage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.driver.Driver;
import com.spotify.web.driver.WebDriverManager;
import com.spotify.web.utils.ResourceUtils;

import io.qameta.allure.Allure;

public class BaseTest {
	
	public WebDriver driver; 
	
	@BeforeMethod
	public void setUp(Method method) {
		
		ResourceUtils.log.info("Setting up the test: " + method.getName());
		
		String browserName = ResourceUtils.getProperty("configuration//browser_config.properties", "browserName");
		String headlessValue = ResourceUtils.getProperty("configuration//browser_config.properties", "headlessEnabled");
		boolean isHeadlessEnabled = Boolean.parseBoolean(headlessValue);
		
		ResourceUtils.log.info("Browser name from properties: " + browserName);
		
		driver = WebDriverManager.getInstance(Driver.getDriverByName(System.getProperty("browser", browserName)), isHeadlessEnabled).getDriver();
		ResourceUtils.log.info("Driver instantiated: " + driver.toString());
		
		driver.manage().window().maximize();
		ResourceUtils.log.info("Browser window maximized");
		
		if(method.isAnnotationPresent(AuthenticationRequired.class)) {
			ResourceUtils.log.info("Authentication required for the test: " + method.getName());
			login();
		}
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		ResourceUtils.log.info("Quitting the driver");
		
		if(result.getStatus()==ITestResult.FAILURE) {
			File screenshotFile = takeScreenshot(result.getMethod().getMethodName());
			
            try {
				Allure.attachment("Page Screenshot", FileUtils.openInputStream(screenshotFile));
			} catch (IOException e) {
				ResourceUtils.log.info("Failed to add Screenshot to the report");
			}
            
			ResourceUtils.log.info("Screenshot has been taken for the failed test case.");
		}
		
		WebDriverManager.quitDriver();
		ResourceUtils.log.info("Driver quitted successfully");
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
	
	public File takeScreenshot(String methodName) {
		
		System.out.println(driver);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
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
