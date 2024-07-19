package com.spotify.web.dataproviders;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.DataProvider;
import com.spotify.web.pojo.Playlist;
import com.spotify.web.utils.ResourceUtils;

public class PlaylistDataProvider {
	@DataProvider
	public Object[][] getPlaylistTestData(){
		List<Playlist> playlists = Arrays.asList(ResourceUtils.getJsonData("Playlist.json", Playlist[].class));		
		return playlists.stream()
                .map(playlist -> new Object[] {playlist.getPlaylistTitle()})
                .toArray(Object[][]::new);
	}
}
