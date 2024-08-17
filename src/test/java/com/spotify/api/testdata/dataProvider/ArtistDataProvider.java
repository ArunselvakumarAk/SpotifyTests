package com.spotify.api.testdata.dataProvider;

import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.DataProvider;
import com.spotify.api.pojo.responsemodel.artist.Artist;
import com.spotify.api.pojo.responsemodel.artist.GetArtistsResponseModel;
import com.spotify.web.utils.ResourceUtils;

public class ArtistDataProvider {
	
	@DataProvider
	public Object[][] artistIds(){
		
		GetArtistsResponseModel artistResponse = ResourceUtils.getJsonData("artistsConfig.json", GetArtistsResponseModel.class);

        List<String> artistIds = artistResponse.getArtists().stream().map(Artist::getId).collect(Collectors.toList());

        return artistIds.stream()
                        .map(id -> new Object[]{id})
                        .toArray(Object[][]::new);
    }
	
	@DataProvider
	public Object[] multipleArtistIds() {

		GetArtistsResponseModel artistResponse = ResourceUtils.getJsonData("artistsConfig.json",GetArtistsResponseModel.class);

		List<String> artistIds = artistResponse.getArtists().stream().map(Artist::getId).collect(Collectors.toList());

		return new Object[] {artistIds};
	}
}
