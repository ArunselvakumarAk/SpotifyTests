package com.spotify.web.base;

import java.lang.reflect.Method;
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
		String browserName = ResourceUtils.getProperty("configuration//browser_config.properties", "browserName");
		driver = WebDriverManager.getInstance(Driver.getDriverByName(System.getProperty("browser", browserName))).getDriver();
		driver.manage().window().maximize();
		if(method.isAnnotationPresent(AuthenticationRequired.class)) {
			login();
		}
	}
	
	@AfterMethod
	public void tearDown() {
		WebDriverManager.quitDriver();
	}
	
	public void login() {
		LoginPage login = new LoginPage(driver);
		String username = ResourceUtils.getProperty("configuration//authentication_config.properties", "username");
		String password = ResourceUtils.getProperty("configuration//authentication_config.properties", "password");
		
		login
			.navigateToLoginPage()
			.handleLogin(username, password);
	}

}
