package com.spotify.web.driver;

import java.util.Arrays;

public enum Driver {
	CHROME("chrome"),
	FIREFOX("firefox"),
	EDGE("edge");
	
	private String name;
 
	Driver(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public static Driver getDriverByName(String browserName) {
	        return Arrays.stream(Driver.values())
	                .filter(browser -> browser.getName().equals(browserName))
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException
	                		("Couldn't find a browser by the provided parameter value"));
	}

}
