package com.spotify.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.base.BaseTest;
import com.spotify.web.dataproviders.AlbumTestsDataProviders;
import com.spotify.web.driver.DriverManager;
import com.spotify.web.pageobjects.common.ContextMenu;
import com.spotify.web.pageobjects.pages.AlbumPage;
import com.spotify.web.utils.EventLogger;

public class AlbumTests extends BaseTest{
	
	@Test(dataProvider="getAlbumsTestData", dataProviderClass=AlbumTestsDataProviders.class, groups= {"web"})
	@AuthenticationRequired
	public void addAlbumToLibraryTest(String albumId, String albumName){
		AlbumPage albumPage = new AlbumPage(DriverManager.getInstance().getDriver());
		ContextMenu contextMenu = new ContextMenu(DriverManager.getInstance().getDriver());
		
		albumPage
			.openPage(albumId)
			.verifyAlbumPageIsOpened(albumName);
		
		contextMenu
			.verifycontextMenuBtn()
			.clickContextMenuBtn()
			.verifyAddToLibraryBtn()
			.clickAddToLibraryBtn();
		
		Assert.assertTrue(contextMenu.verifyAddedToLibrarySuccessMsg());
		EventLogger.info("Album successfully added to the library");
	}
	
	@Test(dataProvider="getAlbumsTestData", dataProviderClass=AlbumTestsDataProviders.class, groups= {"web"})
	@AuthenticationRequired
	public void removeAlbumFromLibraryTest(String albumId, String albumName){
		AlbumPage albumPage = new AlbumPage(DriverManager.getInstance().getDriver());
		ContextMenu contextMenu = new ContextMenu(DriverManager.getInstance().getDriver());
		
		albumPage
			.openPage(albumId)
			.verifyAlbumPageIsOpened(albumName);
		
		contextMenu
			.verifycontextMenuBtn()
			.clickContextMenuBtn()
			.verifyRemoveFromLibraryBtn()
			.clickRemoveFromLibraryBtn();
	
		Assert.assertTrue(contextMenu.verifyRemovedFromLibrarySuccessMsg());	
		EventLogger.info("Album successfully removed from the library");
	}
}
