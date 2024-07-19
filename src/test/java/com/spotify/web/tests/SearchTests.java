package com.spotify.web.tests;

import org.testng.annotations.Test;
import com.spotify.web.base.BaseTest;
import com.spotify.web.dataproviders.PlaylistDataProvider;
import com.spotify.web.dataproviders.SongDataProvider;
import com.spotify.web.pageobjects.pages.SearchPage;


public class SearchTests extends BaseTest{
	
	@Test(dataProvider="getSongTestData", dataProviderClass=SongDataProvider.class)
	public void searchedSongInResultsTest(String song, String artist) {	
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.navigateToSearchPage()
			.enterSearchText(song, artist)
			.clickSongsFilterBtn()
			.assertSongInTopResults(song, artist);
	}
	
	@Test(dataProvider = "getPlaylistTestData", dataProviderClass=PlaylistDataProvider.class)
	public void searchPlaylistInResultsTest(String playlistTitle) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.navigateToSearchPage()
			.enterSearchText(playlistTitle)
			.clickPlaylistsFilterBtn()
			.assertPlaylistInTopResults(playlistTitle);
	}
		
}
