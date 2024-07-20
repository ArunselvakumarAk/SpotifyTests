package com.spotify.web.pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.spotify.web.pageobjects.base.BasePage;

public class ContextMenu extends BasePage{
	//Remove from Your Library
	private By addToLibrary_btn = By.xpath("//div[@id='context-menu']//span[normalize-space()='Add to Your Library']");
	private By removeFromLibrary_btn = By.xpath("//div[@id='context-menu']//span[normalize-space()='Remove from Your Library']");
	private By contextMenu_btn = By.xpath("//button[@aria-haspopup='menu']//*[local-name()='svg' and contains(@class,'Svg-sc-ytk21e-0 cqasRA')]");
	private By delete_btn = By.xpath("//span[normalize-space()='Delete']");
	private By editDetails_btn = By.xpath("//span[normalize-space()='Edit details']");	
	private By confirmationDelete_btn = By.xpath("//button[contains(@aria-label,'Delete')]/span[normalize-space()='Delete']");
	
	public ContextMenu(WebDriver driver) {
		super(driver);
	}
	
	public ContextMenu verifycontextMenuBtn() {
		wait.waitForVisibilityOfElement(contextMenu_btn);
		Assert.assertTrue(driver.findElement(contextMenu_btn).isDisplayed());
		return this;
	}
	
	public ContextMenu verifyAddToLibraryBtn() {
		wait.waitForVisibilityOfElement(addToLibrary_btn);
		Assert.assertTrue(driver.findElement(addToLibrary_btn).isDisplayed());
		return this;
	}
	
	public ContextMenu verifyRemoveFromLibraryBtn() {
		wait.waitForVisibilityOfElement(removeFromLibrary_btn);
		Assert.assertTrue(driver.findElement(removeFromLibrary_btn).isDisplayed());
		return this;
	}
	
	public ContextMenu clickContextMenuBtn() {
		driver.findElement(contextMenu_btn).click();
		return new ContextMenu(driver);
	}
	
	public ContextMenu clickAddToLibraryBtn() {
		driver.findElement(addToLibrary_btn).click();
		return new ContextMenu(driver);
	}
	
	public ContextMenu clickRemoveFromLibraryBtn() {
		driver.findElement(removeFromLibrary_btn).click();
		return new ContextMenu(driver);
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
	

	
	public ContextMenu clickConfirmationDeleteBtn() {
		wait.waitForVisibilityOfElement(confirmationDelete_btn);
		driver.findElement(confirmationDelete_btn).click();
		return this;
	}
	
}
