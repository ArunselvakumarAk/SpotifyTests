package com.spotify.web.dataproviders;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.DataProvider;
import com.spotify.web.pojo.Album;
import com.spotify.web.utils.ResourceUtils;

public class AlbumTestsDataProviders {
	@DataProvider
	public Object[] getAlbumsTestData(){
		List<Album> albums = Arrays.asList(ResourceUtils.getJsonData("albums.json", Album[].class));		
		return albums.stream()
				.map(album -> new Object[] { album.getAlbumId(), album.getAlbumName() })
				.toArray(Object[][]::new);
				
	}
}
