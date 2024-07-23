package com.spotify.web.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.base.BaseTest;
import com.spotify.web.dataproviders.LibraryTestsDataProviders;
import com.spotify.web.pageobjects.common.ContextMenu;
import com.spotify.web.pageobjects.pages.LibraryPage;
import com.spotify.web.pageobjects.pages.PlaylistPage;
import com.spotify.web.utils.ResourceUtils;
import io.qameta.allure.Description;

public class LibraryTests extends BaseTest{
	
	@Description("This test checks the functionality of creating a new playlist in the library")
	@Test(groups= {"regression", "smoke"})
	@AuthenticationRequired
	public void createNewPlaylistInLibraryTest() throws InterruptedException {
		LibraryPage libraryPage = new LibraryPage(driver);
		ContextMenu contextMenu = new ContextMenu(driver);
		libraryPage
			.verifyLibraryBtnIsVisible()
			.createNewPlaylist();
		
		Assert.assertTrue(contextMenu.verifyAddedToLibrarySuccessMsg());
	}
	
	@Description("This test verifies that the playlist name in the library can be successfully edited")
	@Test(dataProvider="changePlaylistNameTestData", dataProviderClass=LibraryTestsDataProviders.class, groups= {"regression"})
	@AuthenticationRequired
	public void changePlaylistNameinLibraryTest(String originalName, String newName) {
		LibraryPage libraryPage = new LibraryPage(driver);
		ContextMenu contextMenu = new ContextMenu(driver);
		PlaylistPage playlistPage = new PlaylistPage(driver);
		
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
			ResourceUtils.log.info("{} is not found in your library");
		}
	}
	
	@Description("This test confirms that the playlist can be deleted")
	@Test(dataProvider = "deletePlaylistTestData", dataProviderClass=LibraryTestsDataProviders.class, groups= {"regression"})
	@AuthenticationRequired
	public void deletePlaylistInLibraryTest(String playlistTitle) {
		LibraryPage libraryPage = new LibraryPage(driver);
		ContextMenu contextMenu = new ContextMenu(driver);
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
			ResourceUtils.log.info("{} is not found in your library");
		}
	}
}
