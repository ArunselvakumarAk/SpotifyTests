package com.spotify.web.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.base.BaseTest;
import com.spotify.web.pageobjects.common.ContextMenu;
import com.spotify.web.pageobjects.pages.LibraryPage;
import com.spotify.web.pageobjects.pages.PlaylistPage;

public class LibraryTests extends BaseTest{
	
	@Test
	@AuthenticationRequired
	public void createNewPlaylistInLibraryTest() throws InterruptedException {
		LibraryPage libraryPage = new LibraryPage(driver);
		libraryPage
			.verifyLibraryBtnIsVisible()
			.createNewPlaylist();
	}
	
	@Test(dataProvider="changePlaylistNameTestData")
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
		}else {
			System.out.println("Playlist Not found");
		}
	}
	
	@Test(dataProvider = "deletePlaylistTestData")
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
		}
	}
	
	 @DataProvider(name = "changePlaylistNameTestData")
	 public Object[][] changePlaylistNameTestData() {
		 return new Object[][] {
	           {"My Playlist #1", "Updated Playlist #1"},
	        };
	    }
	
	@DataProvider
	public Object[] deletePlaylistTestData() {
		return new Object[] {"Updated Playlist"};
	}

}
