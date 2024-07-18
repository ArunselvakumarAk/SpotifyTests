package com.spotify.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.web.base.BaseTest;
import com.spotify.web.pageobjects.common.NavigationMenu;
import com.spotify.web.pageobjects.pages.PlaylistPage;

public class PlaylistTests extends BaseTest{
	
	@Test(enabled=true, priority =1)
	public void createNewPlaylistTest() {
		NavigationMenu navigation = new NavigationMenu(driver);
		navigation.createNewPlaylist();
	}
	
	@Test(enabled=true, priority = 2)
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
		}else {
			System.out.println("Playlist Not found");
		}
	}
	
	@Test(enabled=true, priority =4)
	public void deletePlaylistTest() {
		NavigationMenu navigation = new NavigationMenu(driver);
		boolean isPresent = navigation.isPlaylistpresent("Updated One");
		if(isPresent) {
			navigation
				.openPlaylist("Updated One")
				.clickMoreOptionsBtn()
				.clickDeleteBtn()
				.clickConfirmationDeleteBtn();
		}
	}
	
	@Test(enabled=true, dependsOnMethods="editPlaylistName",priority=3 )
	public void AssertIfPlaylistPresent() {
		NavigationMenu navigation = new NavigationMenu(driver);
		Assert.assertTrue(navigation.isPlaylistpresent("Updated One"));
		
	}

}
