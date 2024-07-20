package com.spotify.web.tests;

import org.testng.annotations.Test;

import com.spotify.web.base.BaseTest;
import com.spotify.web.dataproviders.SearchTestsDataProviders;
import com.spotify.web.pageobjects.pages.SearchPage;


public class SearchTests extends BaseTest{
	
	@Test(dataProvider="getTracksTestData", dataProviderClass=SearchTestsDataProviders.class)
	public void searchedTrackInResultsTest(String song, String artist) {	
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.openSearchPage()
			.enterSearchText(song, artist)
			.clickFilterBtn("Songs")
			.assertSongInTopResults(song, artist);
	}
	
	@Test(dataProvider = "getPlaylistsTestData", dataProviderClass=SearchTestsDataProviders.class)
	public void searchedPlaylistsInResultsTest(String playlistTitle) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.openSearchPage()
			.enterSearchText(playlistTitle)
			.clickFilterBtn("Playlists")
			.assertInTopResults(playlistTitle);
	}
	
	@Test(dataProvider="getAlbumsTestData", dataProviderClass=SearchTestsDataProviders.class)
	public void searchedAlbumnInResultsTest(String albumnName) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.openSearchPage()
			.enterSearchText(albumnName)
			.clickFilterBtn("Albums")
			.assertInTopResults(albumnName);
	}
	
	@Test(dataProvider="getartistsTestData", dataProviderClass=SearchTestsDataProviders.class)
	public void searchedArtistInResultsTest(String artistName) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.openSearchPage()
			.enterSearchText(artistName)
			.clickFilterBtn("Artists")
			.assertInTopResults(artistName);
	}
}
