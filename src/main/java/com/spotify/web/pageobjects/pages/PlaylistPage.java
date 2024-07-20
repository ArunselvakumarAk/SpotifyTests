package com.spotify.web.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.spotify.web.pageobjects.base.BasePage;
import com.spotify.web.pageobjects.common.ContextMenu;

public class PlaylistPage extends BasePage{
	
	private By editPlaylistName_txt = By.xpath("//input[@data-testid='playlist-edit-details-name-input']");
	private By save_btn = By.xpath("//span[text()='Save']");
	private By confirmationDelete_btn = By.xpath("//button[contains(@aria-label,'Delete')]/span[normalize-space()='Delete']");

	public PlaylistPage(WebDriver driver) {
		super(driver);
	}
	
	public PlaylistPage verifyConfirmationDeleteBtn() {
		wait.waitForVisibilityOfElement(confirmationDelete_btn);
		Assert.assertTrue(driver.findElement(confirmationDelete_btn).isDisplayed());
		return this;
	}
	
	public PlaylistPage clickConfirmationDeleteBtn() {
		driver.findElement(confirmationDelete_btn).click();
		return this;
	}
	
	public PlaylistPage editPlaylistName(String playlistName) {
		wait.waitForVisibilityOfElement(editPlaylistName_txt);
		driver.findElement(editPlaylistName_txt).clear();
		driver.findElement(editPlaylistName_txt).sendKeys(playlistName);;
		return this;
	}

	public PlaylistPage clickSaveBtn() {
		driver.findElement(save_btn).click();
		return this;
	}

}
