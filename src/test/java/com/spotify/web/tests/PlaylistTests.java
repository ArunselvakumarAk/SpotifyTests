package com.spotify.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.web.base.BaseTest;
import com.spotify.web.pageobjects.common.NavigationMenu;
import com.spotify.web.pageobjects.pages.PlaylistPage;

public class PlaylistTests extends BaseTest{
	
	@Test(enabled=true)
	public void createNewPlaylistTest() {
		NavigationMenu navigation = new NavigationMenu(driver);
		navigation.createNewPlaylist();
	}
	
	@Test(enabled=true, dependsOnMethods = "createNewPlaylistTest")
	public void editPlaylistName() {
		NavigationMenu navigation = new NavigationMenu(driver);
		boolean isPresent = navigation.isPlaylistpresent("My Playlist #1");
		if(isPresent) {
			navigation
				.openPlaylist("My Playlist #1")
				.clickMoreOptionsBtn()
				.clickeditDetailsBtn()
				.editPlaylistName("Updated One")
				.clickSaveBtn();
		}
	}
	
	@Test(enabled = true,  dependsOnMethods = "editPlaylistName")
	public void deletePlaylistTest() {
		NavigationMenu navigation = new NavigationMenu(driver);
		boolean isPresent = navigation.isPlaylistpresent("Updated One");
		if(isPresent) {
			navigation
				.openPlaylist("My Playlist #2")
				.clickMoreOptionsBtn()
				.clickDeleteBtn()
				.clickConfirmationDeleteBtn();
		}
	}
	
	@Test(enabled=true, dependsOnMethods = "deletePlaylistTest")
	public void AssertIfPlaylistPresent() {
		NavigationMenu navigation = new NavigationMenu(driver);
		Assert.assertTrue(navigation.isPlaylistpresent("Updated One"));
	}

}
