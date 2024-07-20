package com.spotify.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.base.BaseTest;
import com.spotify.web.pageobjects.common.ContextMenu;
import com.spotify.web.pageobjects.pages.AlbumPage;
import com.spotify.web.pageobjects.pages.LibraryPage;

public class AlbumTests extends BaseTest{
	
	
	@Test
	@AuthenticationRequired
	public void addAlbumToLibraryTest() throws InterruptedException {
		AlbumPage album = new AlbumPage(driver);
		ContextMenu contextMenu = new ContextMenu(driver);
		LibraryPage libraryPage = new LibraryPage(driver);
		album
			.openPage("0zRUzTXH7GtGLxt6uVdARD")
			.verifyAlbumPageIsOpened("Jailer");
		
		contextMenu
			.verifycontextMenuBtn()
			.clickContextMenuBtn()
			.verifyAddToLibraryBtn()
			.clickAddToLibraryBtn();
			Thread.sleep(2000);
		Assert.assertTrue(libraryPage.isPlaylistpresent("Jailer"));
	}
	
	@Test
	@AuthenticationRequired
	public void RemoveAlbumFromLibraryTest() throws InterruptedException {
		AlbumPage album = new AlbumPage(driver);
		ContextMenu contextMenu = new ContextMenu(driver);
		LibraryPage libraryPage = new LibraryPage(driver);
		album
			.openPage("0zRUzTXH7GtGLxt6uVdARD")
			.verifyAlbumPageIsOpened("Jailer");
		
		contextMenu
			.verifycontextMenuBtn()
			.clickContextMenuBtn()
			.verifyRemoveFromLibraryBtn()
			.clickRemoveFromLibraryBtn();
			Thread.sleep(2000);
		Assert.assertFalse(libraryPage.isPlaylistpresent("Jailer"));
	}
}
