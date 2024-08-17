package com.spotify.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.base.BaseTest;
import com.spotify.web.dataproviders.LibraryTestsDataProviders;
import com.spotify.web.driver.DriverManager;
import com.spotify.web.pageobjects.common.ContextMenu;
import com.spotify.web.pageobjects.pages.LibraryPage;
import com.spotify.web.pageobjects.pages.PlaylistPage;
import com.spotify.web.utils.EventLogger;

public class LibraryTests extends BaseTest{
	
	@Test(groups= {"web"})
	@AuthenticationRequired
	public void createNewPlaylistInLibraryTest() throws InterruptedException {
		LibraryPage libraryPage = new LibraryPage(DriverManager.getInstance().getDriver());
		ContextMenu contextMenu = new ContextMenu(DriverManager.getInstance().getDriver());
		libraryPage
			.verifyLibraryBtnIsVisible()
			.createNewPlaylist();
		
		Assert.assertTrue(contextMenu.verifyAddedToLibrarySuccessMsg());
	}
	
	@Test(dataProvider="changePlaylistNameTestData", dataProviderClass=LibraryTestsDataProviders.class, groups= {"web"})
	@AuthenticationRequired
	public void changePlaylistNameinLibraryTest(String originalName, String newName) {
		LibraryPage libraryPage = new LibraryPage(DriverManager.getInstance().getDriver());
		ContextMenu contextMenu = new ContextMenu(DriverManager.getInstance().getDriver());
		PlaylistPage playlistPage = new PlaylistPage(DriverManager.getInstance().getDriver());
		
		boolean isPresent = libraryPage.isPlaylistpresent(originalName);
		if(isPresent) {
			libraryPage
				.openPlaylist(originalName);
			contextMenu
				.verifycontextMenuBtn()
				.clickContextMenuBtn()
				.clickEditDetailsBtn();
			playlistPage
				.editPlaylistName(newName)
				.clickSaveBtn();
			Assert.assertTrue(libraryPage.isPlaylistpresent(newName));
		}else {
			EventLogger.info(originalName+" is not found in your library");
		}
	}
	
	@Test(dataProvider = "deletePlaylistTestData", dataProviderClass=LibraryTestsDataProviders.class, groups= {"web"})
	@AuthenticationRequired
	public void deletePlaylistInLibraryTest(String playlistTitle) {
		LibraryPage libraryPage = new LibraryPage(DriverManager.getInstance().getDriver());
		ContextMenu contextMenu = new ContextMenu(DriverManager.getInstance().getDriver());
		boolean isPresent = libraryPage.isPlaylistpresent(playlistTitle);
		if(isPresent) {
			libraryPage
				.openPlaylist(playlistTitle);
			contextMenu
				.verifycontextMenuBtn()
				.clickContextMenuBtn()
				.clickDeleteBtn()
				.clickConfirmationDeleteBtn();
			Assert.assertTrue(contextMenu.verifyRemovedFromLibrarySuccessMsg());
		}else {
			EventLogger.info(playlistTitle+" is not found in your library");
		}
	}
}
