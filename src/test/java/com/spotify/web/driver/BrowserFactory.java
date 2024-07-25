package com.spotify.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.spotify.web.utils.ResourceUtils;

public class BrowserFactory {
	
	public WebDriver createDriver(Driver browser, boolean isHeadlessEnabled) {
		
		WebDriver driver =null;
		
        switch (browser) {
            case CHROME:
				if (isHeadlessEnabled) {
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless");
					options.addArguments("window-size=1920x1080");
					driver = new ChromeDriver(options);
					ResourceUtils.log.info("New {} driver created in headless mode", browser);
				} else {
					driver = new ChromeDriver();
					ResourceUtils.log.info("New {} driver created", browser);
				}
                break;
                
            case FIREFOX:
				if (isHeadlessEnabled) {
					FirefoxOptions options = new FirefoxOptions();
					options.addArguments("-headless");
					driver = new FirefoxDriver(options);
					ResourceUtils.log.info("New {} driver created in headless mode", browser);
				} else {
					driver = new FirefoxDriver();
					ResourceUtils.log.info("New {} driver created", browser);
				}
				break;
				
            case EDGE:
            	if (isHeadlessEnabled) {
            		EdgeOptions options = new EdgeOptions();
            		options.addArguments("-headless");
            		driver = new EdgeDriver(options);
					ResourceUtils.log.info("New {} driver created in headless mode", browser);
				}
                driver = new EdgeDriver();
                ResourceUtils.log.info("New {} driver created", browser);
                break;
                
            default:
            	throw new IllegalArgumentException("Unsupported or invalid browser name provided");
        }
        
        return driver;
    }
}
