package com.spotify.web.pageobjects.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.spotify.web.pageobjects.base.BasePage;
import com.spotify.web.utils.EventLogger;
import com.spotify.web.utils.ResourceUtils;

public class AlbumPage extends BasePage{
	
	public AlbumPage(WebDriver driver) {
		super(driver);
	}
	
	public AlbumPage openPage(String albumID) {
		EventLogger.info("Opening Album page with "+albumID);
		driver.get(ResourceUtils.getProperty("configuration//web_config.properties", "baseUrl") +"/album/" + albumID);
		return this;
	}
	
	public void verifyAlbumPageIsOpened(String albumName) {
		Assert.assertTrue(driver.getTitle().contains(albumName));
	}
	
}
