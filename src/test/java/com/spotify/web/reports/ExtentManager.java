package com.spotify.web.reports;

import com.aventstack.extentreports.ExtentTest;
import com.spotify.web.driver.DriverManager;

public class ExtentManager {
	public static volatile ExtentManager instance;
	public ThreadLocal<ExtentTest> extent = new ThreadLocal<>();
	
	private ExtentManager() {}
	
	public static ExtentManager getInstance() {
		if(instance==null) {
			synchronized(DriverManager.class){
				if(instance==null) {
					instance = new ExtentManager();
				}
			}
		}
		return instance;
	}
	
	public void setExtent(ExtentTest object) {
		this.extent.set(object);
	}
	
	public ExtentTest getExtent() {
		return extent.get();
	}
	
	public void closeExtent() {
		extent.remove();
	}
}
