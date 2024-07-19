package com.spotify.web.pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.spotify.web.pageobjects.base.BasePage;

public class ContextMenu extends BasePage{
	
	private By delete_btn = By.xpath("//span[normalize-space()='Delete']");
	private By editDetails_btn = By.xpath("//span[normalize-space()='Edit details']");
	private By editPlaylistName_txt = By.xpath("//input[@data-testid='playlist-edit-details-name-input']");
	private By save_btn = By.xpath("//span[text()='Save']");
	private By confirmationDelete_btn = By.xpath("//button[contains(@aria-label,'Delete')]/span[normalize-space()='Delete']");
	
	public ContextMenu(WebDriver driver) {
		super(driver);
	}
	
	public ContextMenu clickDeleteBtn() {
		wait.waitForVisibilityOfElement(delete_btn);
		driver.findElement(delete_btn).click();
		return this;
	}
	
	public ContextMenu clickEditDetailsBtn() {
		wait.waitForVisibilityOfElement(editDetails_btn);
		driver.findElement(editDetails_btn).click();
		return this;
	}
	
	public ContextMenu editPlaylistName(String playlistName) {
		wait.waitForVisibilityOfElement(editPlaylistName_txt);
		driver.findElement(editPlaylistName_txt).clear();
		driver.findElement(editPlaylistName_txt).sendKeys(playlistName);;
		return this;
	}
	
	public ContextMenu clickConfirmationDeleteBtn() {
		wait.waitForVisibilityOfElement(confirmationDelete_btn);
		driver.findElement(confirmationDelete_btn).click();
		return this;
	}
	
	public ContextMenu clickSaveBtn() {
		driver.findElement(save_btn).click();
		return this;
	}
}
