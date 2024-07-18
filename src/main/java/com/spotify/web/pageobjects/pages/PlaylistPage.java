package com.spotify.web.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.spotify.web.pageobjects.base.BasePage;
import com.spotify.web.pageobjects.common.ContextMenu;

public class PlaylistPage extends BasePage{
	
	private By moreOptions_btn = By.xpath("//*[local-name()='svg' and contains(@class,'Svg-sc-ytk21e-0 cqasRA')]");
	private By delete_btn = By.xpath("//span[normalize-space()='Delete']");
	private By confirmationDelete_btn = By.xpath("//button[contains(@aria-label,'Delete')]/span[normalize-space()='Delete']");

	public PlaylistPage(WebDriver driver) {
		super(driver);
	}
	
	public ContextMenu clickMoreOptionsBtn() {
		waitForPresenceOfElement(moreOptions_btn);
		driver.findElement(moreOptions_btn).click();
		return new ContextMenu(driver);
	}
	
	private PlaylistPage clickDeleteBtn() {
		driver.findElement(delete_btn).click();
		return this;
	}
	
	private PlaylistPage clickConfirmationDeleteBtn() {
		driver.findElement(confirmationDelete_btn).click();
		return this;
	}
	
	public void deletePlaylist(){
		clickMoreOptionsBtn().clickDeleteBtn().clickConfirmationDeleteBtn();
	}

}
