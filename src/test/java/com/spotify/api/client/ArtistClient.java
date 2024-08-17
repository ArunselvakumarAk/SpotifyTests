package com.spotify.api.client;

import static io.restassured.RestAssured.given;
import java.util.List;
import com.spotify.api.constants.EndPoint;
import com.spotify.api.pojo.responsemodel.artist.Artist;
import com.spotify.api.pojo.responsemodel.artist.GetArtistsResponseModel;
import com.spotify.api.specification.ArtistSpec;
import com.spotify.api.utils.ApiUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class ArtistClient{
	public static Artist getArtist(String artistId) {
		RequestSpecification requestSpec = ArtistSpec.requestSpec();
		ResponseSpecification responseSpec = ArtistSpec.responseSpec();
		ApiUtils.addRequestLogs(requestSpec);
		
		Response response = given()
				.spec(requestSpec)
				.when()
				.get(EndPoint.ARTIST.get() + artistId)
				.then()
				.spec(responseSpec).extract()
				.response();
		
		ApiUtils.addResponseLogs(response);
		return response.as(Artist.class);
		
	}
	
	public static GetArtistsResponseModel getMultipleArtists(List<String> arr) {
		RequestSpecification requestSpec = ArtistSpec.requestSpecForArtist(arr);
		ResponseSpecification responseSpec = ArtistSpec.responseSpec();
		ApiUtils.addRequestLogs(requestSpec);
		
		Response response = given()
				.spec(requestSpec)
				.when()
				.get(EndPoint.ARTIST.get())
				.then()
				.spec(responseSpec)
				.extract()
				.response();
		
		ApiUtils.addResponseLogs(response);
		return response.as(GetArtistsResponseModel.class);
		
	}
}
