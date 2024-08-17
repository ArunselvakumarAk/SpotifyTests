package com.spotify.api.testdata.artistAssertions;

import java.util.Optional;
import com.spotify.api.pojo.responsemodel.artist.Artist;
import com.spotify.api.pojo.responsemodel.artist.GetArtistsResponseModel;
import com.spotify.web.utils.ResourceUtils;

public class ArtistAssertionData {
	public static Artist ExpectedAssertionData(String id) {
		 GetArtistsResponseModel artistsResponse = ResourceUtils.getJsonData("artistsConfig.json", GetArtistsResponseModel.class);
		 
		 Optional<Artist> artist = artistsResponse.getArtists().stream()
		            .filter(a -> a.getId().equals(id))
		            .findFirst();
		 
		 return artist.orElse(null);
	}
	
	public static GetArtistsResponseModel MultipleArtistExpectedAssertionData() {
		return ResourceUtils.getJsonData("artistsConfig.json", GetArtistsResponseModel.class);
		 
	}
	
}
