package com.spotify.web.tests;

import org.testng.annotations.Test;
import com.spotify.web.base.BaseTest;
import com.spotify.web.dataproviders.SearchTestsDataProviders;
import com.spotify.web.pageobjects.pages.SearchPage;
import io.qameta.allure.Description;


public class SearchTests extends BaseTest{
	
	@Description("This test checks the functionality of Spotify's song search feature to ensure it works accurately and efficiently.")
	@Test(dataProvider="getTracksTestData", dataProviderClass=SearchTestsDataProviders.class, groups= {"regression", "smoke"})
	public void searchedTrackInResultsTest(String song, String artist) {	
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.openSearchPage()
			.enterSearchText(song, artist)
			.clickFilterBtn("Songs")
			.assertSongInTopResults(song, artist);
	}
	
	@Description("This test checks the functionality of Spotify's playlist search feature to ensure it works accurately and efficiently.")
	@Test(dataProvider = "getPlaylistsTestData", dataProviderClass=SearchTestsDataProviders.class, groups= {"regression"})
	public void searchedPlaylistsInResultsTest(String playlistTitle) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.openSearchPage()
			.enterSearchText(playlistTitle)
			.clickFilterBtn("Playlists")
			.assertInTopResults(playlistTitle);
	}
	
	@Description("This test checks the functionality of Spotify's album search feature to ensure it works accurately and efficiently.")
	@Test(dataProvider="getAlbumsTestData", dataProviderClass=SearchTestsDataProviders.class, groups= {"regression"})
	public void searchedAlbumnInResultsTest(String albumnName) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.openSearchPage()
			.enterSearchText(albumnName)
			.clickFilterBtn("Albums")
			.assertInTopResults(albumnName);
	}
	
	@Description("This test checks the functionality of Spotify's artist search feature to ensure it works accurately and efficiently.")
	@Test(dataProvider="getartistsTestData", dataProviderClass=SearchTestsDataProviders.class, groups= {"regression"})
	public void searchedArtistInResultsTest(String artistName) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.openSearchPage()
			.enterSearchText(artistName)
			.clickFilterBtn("Artists")
			.assertInTopResults(artistName);
	}
}
