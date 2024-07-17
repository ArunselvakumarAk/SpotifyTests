package com.spotify.web.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.spotify.web.base.BaseTest;
import com.spotify.web.pageobjects.pages.SearchPage;
import com.spotify.web.pojo.Track;
import com.spotify.web.utils.TestdataUtils;


public class SearchTests extends BaseTest{
	
	@Test(dataProvider="searchSongTestData")
	public void searchedSongInResultsTest(String song, String artist) {	
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.navigateToSearchPage()
			.enterSearchText(song, artist)
			.clickSongsFilterBtn()
			.assertSongInTopResults(song, artist);
	}
	
	@Test(enabled=false)
	public void searchPlaylistInResultsTest() {
		SearchPage searchPage = new SearchPage(driver);
		searchPage
			.navigateToSearchPage()
			.enterSearchText("Jailer")
			.clickPlaylistsFilterBtn()
			.assertPlaylistInTopResults("Jailer");
	}
	
	@DataProvider
	public Object[][] searchSongTestData(){
		List<Track> tracks = Arrays.asList(TestdataUtils.getJsonData("com//spotify//web//testdata//searchSong.json", Track[].class));
		
		return tracks.stream()
                .map(track -> new Object[] { track.getSongTitle(), track.getArtist() })
                .toArray(Object[][]::new);
	}
}
