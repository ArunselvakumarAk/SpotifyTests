package com.spotify.web.dataproviders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.DataProvider;

import com.spotify.web.pojo.Album;
import com.spotify.web.pojo.Artist;
import com.spotify.web.pojo.Playlist;
import com.spotify.web.pojo.Track;
import com.spotify.web.utils.ResourceUtils;

public class SearchTestsDataProviders {
	@DataProvider
	public Object[][] getTracksTestData(){
		List<Track> tracks = Arrays.asList(ResourceUtils.getJsonData("tracks.json", Track[].class));		
		return tracks.stream()
                .map(track -> new Object[] { track.getTrackTitle(), track.getArtist() })
                .toArray(Object[][]::new);
	}
	
	@DataProvider
	public Object[] getPlaylistsTestData(){
		List<Playlist> playlists = Arrays.asList(ResourceUtils.getJsonData("playlists.json", Playlist[].class));		
		return playlists.stream()
				.map(Playlist::getPlaylistTitle) 
				.collect(Collectors.toList())
				.toArray();
	}
	
	@DataProvider
	public Object[] getAlbumsTestData(){
		List<Album> albums = Arrays.asList(ResourceUtils.getJsonData("albums.json", Album[].class));		
		return albums.stream()
				.map(Album::getAlbumName)
				.collect(Collectors.toList())
				.toArray();
				
	}
	
	@DataProvider
	public Object[] getartistsTestData(){
		List<Artist> artists = Arrays.asList(ResourceUtils.getJsonData("artists.json", Artist[].class));		
		return artists.stream()
				.map(Artist::getArtistName)
				.collect(Collectors.toList())
				.toArray();
	}
}
