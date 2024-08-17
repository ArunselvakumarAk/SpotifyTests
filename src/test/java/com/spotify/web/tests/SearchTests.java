package com.spotify.web.tests;

import org.testng.annotations.Test;
import com.spotify.web.base.BaseTest;
import com.spotify.web.dataproviders.SearchTestsDataProviders;
import com.spotify.web.driver.DriverManager;
import com.spotify.web.pageobjects.pages.SearchPage;


public class SearchTests extends BaseTest{
	
	@Test(dataProvider="getTracksTestData", dataProviderClass=SearchTestsDataProviders.class, groups= {"web"})
	public void verifyTrackIsDisplayedInSearchResults_WhenTrackIsSearched(String song, String artist) {	
		SearchPage searchPage = new SearchPage(DriverManager.getInstance().getDriver());
		searchPage
			.openPage()
			.enterSearchText(song, artist)
			.clickFilterBtn("Songs")
			.assertSongInTopResults(song, artist);
	}
	
	@Test(dataProvider = "getPlaylistsTestData", dataProviderClass=SearchTestsDataProviders.class, groups= {"web"})
	public void verifyPlaylistIsDisplayedInSearchResults_WhenPlaylistIsSearched(String playlistTitle) {
		SearchPage searchPage = new SearchPage(DriverManager.getInstance().getDriver());
		searchPage
			.openPage()
			.enterSearchText(playlistTitle)
			.clickFilterBtn("Playlists")
			.assertInTopResults(playlistTitle);
	}
	
	@Test(dataProvider="getAlbumsTestData", dataProviderClass=SearchTestsDataProviders.class, groups= {"web"})
	public void verifyAlbumIsDisplayedInSearchResults_WhenAlbumIsSearched(String albumnName) {
		SearchPage searchPage = new SearchPage(DriverManager.getInstance().getDriver());
		searchPage
			.openPage()
			.enterSearchText(albumnName)
			.clickFilterBtn("Albums")
			.assertInTopResults(albumnName);
	}
	
	@Test(dataProvider="getartistsTestData", dataProviderClass=SearchTestsDataProviders.class, groups= {"web"})
	public void verifyArtistIsDisplayedInSearchResults_WhenArtistIsSearched(String artistName) {
		SearchPage searchPage = new SearchPage(DriverManager.getInstance().getDriver());
		searchPage
			.openPage()
			.enterSearchText(artistName)
			.clickFilterBtn("Artists")
			.assertInTopResults(artistName);
	}
}
