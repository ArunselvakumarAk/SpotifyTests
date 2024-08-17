package com.spotify.api.testdata.dataProvider;

import org.testng.annotations.DataProvider;
import com.spotify.api.pojo.entities.Playlists;
import com.spotify.web.utils.ResourceUtils;

public class PlaylistDataProvider {
	@DataProvider
	public Object[][] playlistIds(){
		
		Playlists playlistsIds = ResourceUtils.getJsonData("playlistsConfig.json", Playlists.class);

        return playlistsIds.getPlaylists().stream()
                        .map(id -> new Object[]{id})
                        .toArray(Object[][]::new);
    }
	
	@DataProvider
	public Object[] playlistIdForChangeDetails() {
		return new Object[] {"1IiPoqtRuyDvpheP2t7wFB"};
	}
}
