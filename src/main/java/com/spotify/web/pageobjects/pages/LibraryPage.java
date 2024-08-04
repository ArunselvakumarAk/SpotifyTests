package com.spotify.web.pageobjects.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.spotify.web.pageobjects.base.BasePage;
import com.spotify.web.utils.EventLogger;

public class LibraryPage extends BasePage{
	
	private By library_Btn = By.xpath("//button[@aria-label='Collapse Your Library']");
	private By createFirstPlaylist_Btn = By.xpath("//span[text()='Create playlist']");
	private By createPlaylistOrFolder_btn = By.xpath("//button[@aria-label='Create playlist or folder']//*[local-name()='svg']");
	private By createPlaylist_btn = By.xpath("//div[@id='context-menu']//span[contains(text(),'Create a new playlist')]");
	private By totalPlaylistsLocator = By.xpath("//div[@data-testid='top-sentinel']/following-sibling::div[contains(@style,'transform')]/li");
	private By playlistTitleLocator = By.xpath(".//p/span[contains(@class,'ListRowTitle')]");

	public LibraryPage(WebDriver driver) {
		super(driver);
	}
	
	public LibraryPage verifyLibraryBtnIsVisible() {
		utils.waitForVisibilityOfElement(library_Btn);
		Assert.assertTrue(driver.findElement(library_Btn).isDisplayed());
		return this;
	}
	
	private LibraryPage clickCreatePlaylistOrFolderBtn() {
		driver.findElement(createPlaylistOrFolder_btn).click();
		return this;
	}
	
	private LibraryPage clickCreatePlaylistBtn() {
		driver.findElement(createPlaylist_btn).click();
		return this;
	}
	
	public LibraryPage createNewPlaylist(){
		utils.waitForVisibilityOfElement(createPlaylistOrFolder_btn);
		clickCreatePlaylistOrFolderBtn();
		utils.waitForElementToBeClickable(createPlaylist_btn);
		clickCreatePlaylistBtn();
		EventLogger.info("Created new playlist in the library");
		return this;
	}
		
	public boolean isPlaylistpresent(String playlistTitle) {
		boolean isPresent = false;
		if(utils.isElementPresent(createFirstPlaylist_Btn)) {
			return isPresent;
		}
		List<WebElement> elements = driver.findElements(totalPlaylistsLocator);
		EventLogger.info("Locating "+playlistTitle+" in the library");
		for(WebElement element: elements) {
			isPresent = element.findElement(playlistTitleLocator).getText().trim().contains(playlistTitle);
			if(isPresent) {
				EventLogger.info(playlistTitle+" found in the library");
				break;
			}	
		}
		return isPresent;
	}
	
	public PlaylistPage openPlaylist(String playlistTitle) {
		List<WebElement> elements = driver.findElements(totalPlaylistsLocator);
		boolean isPresent = false;
		for(WebElement element: elements) {
			isPresent = element.findElement(playlistTitleLocator).getText().trim().contains(playlistTitle);
			if(isPresent) {
				element.click();
				EventLogger.info("Opening "+playlistTitle);
				break;
			}
				
		}
		return new PlaylistPage(driver);
	}	

}
