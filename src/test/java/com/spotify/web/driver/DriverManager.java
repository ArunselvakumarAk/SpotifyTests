package com.spotify.web.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	public static volatile DriverManager instance;
	public ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	private DriverManager() {}
	
	public static DriverManager getInstance() {
		if(instance==null) {
			synchronized(DriverManager.class){
				if(instance==null) {
					instance = new DriverManager();
				}
			}
		}
		return instance;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	public void quitDriver() {
		if(driver.get()!=null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
