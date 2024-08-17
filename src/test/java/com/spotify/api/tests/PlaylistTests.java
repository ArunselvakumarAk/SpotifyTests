package com.spotify.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.spotify.api.client.PlaylistClient;
import com.spotify.api.testdata.dataProvider.PlaylistDataProvider;

public class PlaylistTests{
	
	@Test(dataProvider="playlistIds", dataProviderClass=PlaylistDataProvider.class, groups= {"api"})
	public void addTracksToPlaylistTest(String playlistId) {
		String snapshotId = PlaylistClient.addTracksToPlaylist(playlistId).getSnapshot_id(); 
		Assert.assertNotNull(snapshotId);
	}
	
	@Test(dataProvider="playlistIds", dataProviderClass=PlaylistDataProvider.class, groups= {"api"})
	public void removeTracksFromPlaylistTest(String playlistId) {
		String snapshotId = PlaylistClient.removeTracksFromPlaylist(playlistId).getSnapshot_id();
		Assert.assertNotNull(snapshotId);
	}
	
	@Test(dataProvider="playlistIdForChangeDetails", dataProviderClass=PlaylistDataProvider.class, groups= {"api"})
	public void changePlaylistDetailsTest(String playlistId) {
		PlaylistClient.changePlaylistDetails(playlistId);
	}
	
}
