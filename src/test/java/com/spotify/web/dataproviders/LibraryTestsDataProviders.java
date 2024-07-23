package com.spotify.web.dataproviders;

import org.testng.annotations.DataProvider;

public class LibraryTestsDataProviders {
	
	 @DataProvider(name = "changePlaylistNameTestData")
	 public Object[][] changePlaylistNameTestData() {
		 return new Object[][] {
	           {"My Playlist #1", "Updated Playlist"},
	        };
	    }
	
	@DataProvider
	public Object[] deletePlaylistTestData() {
		return new Object[] {"Updated Playlist"};
	}
}
