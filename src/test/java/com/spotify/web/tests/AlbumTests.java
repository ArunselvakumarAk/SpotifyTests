package com.spotify.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.base.BaseTest;
import com.spotify.web.dataproviders.AlbumTestsDataProviders;
import com.spotify.web.pageobjects.common.ContextMenu;
import com.spotify.web.pageobjects.pages.AlbumPage;
import io.qameta.allure.Description;

public class AlbumTests extends BaseTest{
	
	@Description("This test checks the functionality of adding an album to the library")
	@Test(dataProvider="getAlbumsTestData", dataProviderClass=AlbumTestsDataProviders.class)
	@AuthenticationRequired
	public void addAlbumToLibraryTest(String albumId, String albumName){
		AlbumPage albumPage = new AlbumPage(driver);
		ContextMenu contextMenu = new ContextMenu(driver);
		
		albumPage
			.openPage(albumId)
			.verifyAlbumPageIsOpened(albumName);
		
		contextMenu
			.verifycontextMenuBtn()
			.clickContextMenuBtn()
			.verifyAddToLibraryBtn()
			.clickAddToLibraryBtn();
		
		Assert.assertTrue(contextMenu.verifyAddedToLibrarySuccessMsg());
	}
	
	@Description("This test verifies that the album can be successfully removed from the library")
	@Test(dataProvider="getAlbumsTestData", dataProviderClass=AlbumTestsDataProviders.class)
	@AuthenticationRequired
	public void removeAlbumFromLibraryTest(String albumId, String albumName){
		AlbumPage albumPage = new AlbumPage(driver);
		ContextMenu contextMenu = new ContextMenu(driver);
		
		albumPage
			.openPage(albumId)
			.verifyAlbumPageIsOpened(albumName);
		
		contextMenu
			.verifycontextMenuBtn()
			.clickContextMenuBtn()
			.verifyRemoveFromLibraryBtn()
			.clickRemoveFromLibraryBtn();
	
		Assert.assertTrue(contextMenu.verifyRemovedFromLibrarySuccessMsg());	
	}
}
