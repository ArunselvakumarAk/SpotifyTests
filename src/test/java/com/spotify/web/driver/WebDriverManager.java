package com.spotify.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
	
	public static volatile WebDriverManager instance;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	private WebDriverManager() {}
	
	public void createDriver(Driver browser) {
        switch (browser) {
            case CHROME:
                driver.set(new ChromeDriver());
                break;
            case FIREFOX:
                driver.set(new FirefoxDriver());
                break;
            case EDGE:
                driver.set(new EdgeDriver());
                break;
            default:
            	driver.set(new ChromeDriver());
        }
    }
	
	public static WebDriverManager getInstance(Driver browser) {
		if(instance==null) {
			synchronized(WebDriverManager.class){
				if(instance==null) {
					instance = new WebDriverManager();
				}
			}
		}
		if(driver.get()==null) {
			instance.createDriver(browser);
		}
		return instance;
	}
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	public static void quitDriver() {
		if(driver.get()!=null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
