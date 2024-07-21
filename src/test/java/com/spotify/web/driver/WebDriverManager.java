package com.spotify.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.spotify.web.utils.ResourceUtils;

public class WebDriverManager {
	
	public static volatile WebDriverManager instance;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	private WebDriverManager() {}
	
	public void createDriver(Driver browser, boolean isHeadlessEnabled) {
        switch (browser) {
            case CHROME:
				if (isHeadlessEnabled) {
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless");
					options.addArguments("window-size=1920x1080");
					driver.set(new ChromeDriver(options));
					ResourceUtils.log.info("New {} driver created in headless mode", browser);
				} else {
					driver.set(new ChromeDriver());
					ResourceUtils.log.info("New {} driver created", browser);
				}
                break;
                
            case FIREFOX:
				if (isHeadlessEnabled) {
					FirefoxOptions options = new FirefoxOptions();
					options.addArguments("-headless");
					driver.set(new FirefoxDriver(options));
					ResourceUtils.log.info("New {} driver created in headless mode", browser);
				} else {
					driver.set(new FirefoxDriver());
					ResourceUtils.log.info("New {} driver created", browser);
				}
				break;
				
            case EDGE:
            	if (isHeadlessEnabled) {
					ResourceUtils.log.info("Headless is currently not supported for edge browser", browser);
				}
                driver.set(new EdgeDriver());
                ResourceUtils.log.info("New {} driver created", browser);
                break;
                
            default:
            	throw new IllegalArgumentException("Unsupported or invalid browser name provided");
        }
    }
	
	public static WebDriverManager getInstance(Driver browser, boolean isHeadlessEnabled) {
		if(instance==null) {
			synchronized(WebDriverManager.class){
				if(instance==null) {
					instance = new WebDriverManager();
				}
			}
		}
		if(driver.get()==null) {
			instance.createDriver(browser, isHeadlessEnabled);
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
