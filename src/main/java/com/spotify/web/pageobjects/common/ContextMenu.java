package com.spotify.web.pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.spotify.web.pageobjects.base.BasePage;
import com.spotify.web.utils.ResourceUtils;

public class ContextMenu extends BasePage{
	
	private By addToLibrary_btn = By.xpath("//div[@id='context-menu']//span[normalize-space()='Add to Your Library']");
	private By removeFromLibrary_btn = By.xpath("//div[@id='context-menu']//span[normalize-space()='Remove from Your Library']");
	private By contextMenu_btn = By.xpath("//button[@aria-haspopup='menu']//*[local-name()='svg' and contains(@class,'Svg-sc-ytk21e-0 cqasRA')]");
	private By delete_btn = By.xpath("//span[normalize-space()='Delete']");
	private By editDetails_btn = By.xpath("//span[normalize-space()='Edit details']");	
	private By confirmationDelete_btn = By.xpath("//button[contains(@aria-label,'Delete')]/span[normalize-space()='Delete']");
	private By addedToLibrarySuccess_msg = By.xpath("//div[@class='r4Hbxvv02KfOVeZ_v335']/span[contains(text(),'Added to Your Library.')]");
	private By removedFromLibrarySuccess_msg = By.xpath("//div[@class='r4Hbxvv02KfOVeZ_v335']/span[contains(text(),'Removed from Your Library.')]");

	public ContextMenu(WebDriver driver) {
		super(driver);
	}
	
	public ContextMenu verifycontextMenuBtn() {
		utils.waitForVisibilityOfElement(contextMenu_btn);
		Assert.assertTrue(driver.findElement(contextMenu_btn).isDisplayed() && driver.findElement(contextMenu_btn).isDisplayed());
		return this;
	}
	
	public ContextMenu verifyAddToLibraryBtn() {
		try {
			utils.waitForVisibilityOfElement(addToLibrary_btn);
			Assert.assertTrue(driver.findElement(addToLibrary_btn).isDisplayed());
		}catch(NoSuchElementException | TimeoutException e) {
			if(driver.findElement(removeFromLibrary_btn).isDisplayed()) {
				ResourceUtils.log.info("Album is already present in your library");
			}
			Assert.assertTrue(false);
		}
		return this;
	}
	
	public ContextMenu verifyRemoveFromLibraryBtn() {
		try {
			utils.waitForVisibilityOfElement(removeFromLibrary_btn);
			Assert.assertTrue(driver.findElement(removeFromLibrary_btn).isDisplayed());
		}catch(NoSuchElementException | TimeoutException e) {
			if(driver.findElement(addToLibrary_btn).isDisplayed()) {
				ResourceUtils.log.info("Album is not yet added to your library");
			}
			Assert.assertTrue(false);
		}
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
		utils.waitForVisibilityOfElement(delete_btn);
		driver.findElement(delete_btn).click();
		return this;
	}
	
	public ContextMenu clickEditDetailsBtn() {
		utils.waitForVisibilityOfElement(editDetails_btn);
		driver.findElement(editDetails_btn).click();
		return this;
	}
	
	public ContextMenu clickConfirmationDeleteBtn() {
		utils.waitForVisibilityOfElement(confirmationDelete_btn);
		driver.findElement(confirmationDelete_btn).click();
		return this;
	}
	
	public boolean verifyAddedToLibrarySuccessMsg() {
		try {
			utils.waitForVisibilityOfElement(addedToLibrarySuccess_msg);
			return driver.findElement(addedToLibrarySuccess_msg).isDisplayed();
		}catch(NoSuchElementException | TimeoutException e) {
			return false;
		}
	}
	
	public boolean verifyRemovedFromLibrarySuccessMsg() {
		try {
			utils.waitForVisibilityOfElement(removedFromLibrarySuccess_msg);
			return driver.findElement(removedFromLibrarySuccess_msg).isDisplayed();
		}catch(NoSuchElementException | TimeoutException e) {
			return false;
		}		
		
	}
}
