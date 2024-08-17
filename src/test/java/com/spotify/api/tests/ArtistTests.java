package com.spotify.api.tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.spotify.api.client.ArtistClient;
import com.spotify.api.pojo.responsemodel.artist.Artist;
import com.spotify.api.pojo.responsemodel.artist.GetArtistsResponseModel;
import com.spotify.api.testdata.artistAssertions.ArtistAssertionData;
import com.spotify.api.testdata.dataProvider.ArtistDataProvider;

public class ArtistTests {
	
	@Test(dataProvider = "artistIds", dataProviderClass=ArtistDataProvider.class, groups= {"api"})
	public void getArtistTest(String id) {
		
		Artist expectedArtistResponse = ArtistAssertionData.ExpectedAssertionData(id);
		Artist actualArtistResponse = ArtistClient.getArtist(id);
		
		Assert.assertEquals(expectedArtistResponse.getId(), actualArtistResponse.getId());
		Assert.assertEquals(expectedArtistResponse.getName(), actualArtistResponse.getName());
		Assert.assertEquals(expectedArtistResponse.getHref(), actualArtistResponse.getHref());
		Assert.assertEquals(expectedArtistResponse.getGenres(), actualArtistResponse.getGenres());
		Assert.assertEquals(expectedArtistResponse.getUri(), actualArtistResponse.getUri());
		
	}
	
	@Test(dataProvider = "multipleArtistIds", dataProviderClass=ArtistDataProvider.class, groups= {"api"})
	public void getMultipleArtistsTest(List<String> arr) {
		
		GetArtistsResponseModel ExpectedArtistsResponse = ArtistAssertionData.MultipleArtistExpectedAssertionData();
		GetArtistsResponseModel actualArtistsResponse = ArtistClient.getMultipleArtists(arr);
		
		boolean isSizeEqual= ExpectedArtistsResponse.getArtists().size()==actualArtistsResponse.getArtists().size();
		Assert.assertTrue(isSizeEqual);
		
		if(isSizeEqual) {
			for(int i =0;i<ExpectedArtistsResponse.getArtists().size();i++) {
				Assert.assertEquals(ExpectedArtistsResponse.getArtists().get(i).getId(),actualArtistsResponse.getArtists().get(i).getId());
				Assert.assertEquals(ExpectedArtistsResponse.getArtists().get(i).getName(),actualArtistsResponse.getArtists().get(i).getName());
				Assert.assertEquals(ExpectedArtistsResponse.getArtists().get(i).getHref(),actualArtistsResponse.getArtists().get(i).getHref());
				Assert.assertEquals(ExpectedArtistsResponse.getArtists().get(i).getGenres(),actualArtistsResponse.getArtists().get(i).getGenres());
				Assert.assertEquals(ExpectedArtistsResponse.getArtists().get(i).getUri(),actualArtistsResponse.getArtists().get(i).getUri());
			}
		}
	}
}
