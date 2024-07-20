package com.spotify.web.pageobjects.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.spotify.web.pageobjects.base.BasePage;

public class LibraryPage extends BasePage{
	
	private By library_Btn = By.xpath("//button[@aria-label='Collapse Your Library']");
	private By createFirstPlaylist_Btn = By.xpath("//span[text()='Create playlist']");
	private By createPlaylistOrFolder_btn = By.xpath("//button[@aria-label='Create playlist or folder']//*[local-name()='svg']");
	private By createPlaylist_btn = By.xpath("//div[@id='context-menu']//span[contains(text(),'Create a new playlist')]");
	private By totalPlaylistsLocator = By.xpath("//div[@data-testid='top-sentinel']/following-sibling::div[contains(@style,'transform')]/li");
	private By playlistTitleLocator = By.xpath(".//p/span[contains(@class,'ListRowTitle')]");
//	private By sortAndView_btn = By.xpath("//button[@role='combobox' and @aria-controls='sort-and-view-picker']");

	public LibraryPage(WebDriver driver) {
		super(driver);
	}
	
	public LibraryPage verifyLibraryBtnIsVisible() {
		wait.waitForVisibilityOfElement(library_Btn);
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
		wait.waitForVisibilityOfElement(createPlaylistOrFolder_btn);
		clickCreatePlaylistOrFolderBtn();
		wait.waitForElementToBeClickable(createPlaylist_btn);
		clickCreatePlaylistBtn();
		return this;
	}
		
	public boolean isPlaylistpresent(String playlistTitle) {
		boolean isPresent = false;
		if(wait.isElementPresent(createFirstPlaylist_Btn)) {
			return isPresent;
		}
		List<WebElement> elements = driver.findElements(totalPlaylistsLocator);
		for(WebElement element: elements) {
			isPresent = element.findElement(playlistTitleLocator).getText().trim().contains(playlistTitle);
			if(isPresent) {
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
				break;
			}
				
		}
		return new PlaylistPage(driver);
	}	

}