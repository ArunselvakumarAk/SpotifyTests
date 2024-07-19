package com.spotify.web.dataproviders;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.spotify.web.pojo.Track;
import com.spotify.web.utils.ResourceUtils;

public class SongDataProvider {
	
	@DataProvider
	public Object[][] getSongTestData(){
		List<Track> tracks = Arrays.asList(ResourceUtils.getJsonData("Track.json", Track[].class));		
		return tracks.stream()
                .map(track -> new Object[] { track.getSongTitle(), track.getArtist() })
                .toArray(Object[][]::new);
	}
	

}
