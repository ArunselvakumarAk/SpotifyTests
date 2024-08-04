package com.spotify.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.spotify.web.utils.EventLogger;
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
					EventLogger.info("New "+ browser +" driver created in headless mode");
				} else {
					driver = new ChromeDriver();
					EventLogger.info("New "+browser+" driver created");
				}
                break;
                
            case FIREFOX:
				if (isHeadlessEnabled) {
					FirefoxOptions options = new FirefoxOptions();
					options.addArguments("-headless");
					driver = new FirefoxDriver(options);
					EventLogger.info("New "+ browser +" driver created in headless mode");
				} else {
					driver = new FirefoxDriver();
					EventLogger.info("New "+browser+" driver created");
				}
				break;
				
            case EDGE:
            	if (isHeadlessEnabled) {
            		EdgeOptions options = new EdgeOptions();
            		options.addArguments("-headless");
            		driver = new EdgeDriver(options);
            		EventLogger.info("New "+ browser +" driver created in headless mode");
				}else {
					driver = new EdgeDriver();
					EventLogger.info("New "+browser+" driver created");
				}
            	break;
                
            default:
            	throw new IllegalArgumentException("Unsupported or invalid browser name provided");
        }
        
        return driver;
    }
}
