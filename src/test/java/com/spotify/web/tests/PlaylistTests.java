package com.spotify.web.tests;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.spotify.web.annotations.AuthenticationRequired;
import com.spotify.web.base.BaseTest;
import com.spotify.web.pageobjects.common.NavigationMenu;
import com.spotify.web.pojo.Playlist;
import com.spotify.web.utils.ResourceUtils;

public class PlaylistTests extends BaseTest{
	
	@Test
	@AuthenticationRequired
	public void createNewPlaylistTest() {
		NavigationMenu navigation = new NavigationMenu(driver);
		navigation.createNewPlaylist();
	}
	
	@Test(dataProvider="editPlaylistTestData")
	@AuthenticationRequired
	public void editPlaylistName(String originalName, String newName) {
		NavigationMenu navigation = new NavigationMenu(driver);
		boolean isPresent = navigation.isPlaylistpresent(originalName);
		if(isPresent) {
			navigation
				.openPlaylist(originalName)
				.clickMoreOptionsBtn()
				.clickEditDetailsBtn()
				.editPlaylistName(newName)
				.clickSaveBtn();
		}else {
			System.out.println("Playlist Not found");
		}
	}
	
	@Test(dataProvider = "deletePlaylistTestData", priority=1)
	@AuthenticationRequired
	public void deletePlaylistTest(String playlistTitle) {
		NavigationMenu navigation = new NavigationMenu(driver);
		boolean isPresent = navigation.isPlaylistpresent(playlistTitle);
		if(isPresent) {
			navigation
				.openPlaylist(playlistTitle)
				.clickMoreOptionsBtn()
				.clickDeleteBtn()
				.clickConfirmationDeleteBtn();
		}
	}
	
//	@Test
//	@AuthenticationRequired
//	public void AssertIfPlaylistPresent() {
//		NavigationMenu navigation = new NavigationMenu(driver);
//		Assert.assertTrue(navigation.isPlaylistpresent("Updated One"));
//		
//	}
	
	@DataProvider
	public Object[][] deletePlaylistTestData() {
		List<Playlist> playlists = Arrays.asList(ResourceUtils.getJsonData("Playlist.json", Playlist[].class));		
		return playlists.stream()
                .map(playlist -> new Object[] {playlist.getPlaylistTitle()})
                .toArray(Object[][]::new);
	}
	
	@DataProvider
	public Object[][] editPlaylistTestData() {
		return new Object[][] {{"My Playlist #1", "Updated Playlist"}};
	}

}
