package com.spotify.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
	
	public WebDriver createDriver() {
		WebDriver driver = new ChromeDriver();
		return driver;
	}

}
