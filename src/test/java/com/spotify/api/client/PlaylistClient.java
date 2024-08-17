package com.spotify.api.client;

import static io.restassured.RestAssured.given;
import com.spotify.api.constants.EndPoint;
import com.spotify.api.pojo.responsemodel.playlist.PlaylistResponseModel;
import com.spotify.api.specification.PlaylistSpec;
import com.spotify.api.utils.ApiUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PlaylistClient {
	
	public static PlaylistResponseModel addTracksToPlaylist(String playlistId) {

		RequestSpecification requestSpec = PlaylistSpec.requestSpecForAddTrack();
		ResponseSpecification responseSpec = PlaylistSpec.responseSpecForCreation();
		ApiUtils.addRequestLogs(requestSpec);
		 Response response = given().spec(requestSpec)
				.when()
				.post(EndPoint.PLAYLIST.get() + playlistId + "/tracks")
				.then()
				.spec(responseSpec)
				.extract()
				.response();
		 
		 ApiUtils.addResponseLogs(response); 
		 return response.as(PlaylistResponseModel.class);
	}
	
	public static PlaylistResponseModel removeTracksFromPlaylist(String playlistId) {

		RequestSpecification requestSpec = PlaylistSpec.requestSpecForRemoveTrack();
		ResponseSpecification responseSpec = PlaylistSpec.responseSpec();
		ApiUtils.addRequestLogs(requestSpec);
		Response response = given()
				.spec(requestSpec)
				.when()
				.delete(EndPoint.PLAYLIST.get() + playlistId + "/tracks")
				.then()
				.spec(responseSpec)
				.extract()
				.response();
		
		ApiUtils.addResponseLogs(response);
		return response.as(PlaylistResponseModel.class);
	}
	
	public static void changePlaylistDetails(String playlistId) {
		
		RequestSpecification requestSpec = PlaylistSpec.requestSpecForChangePlayListDetails();
		ResponseSpecification responseSpec = PlaylistSpec.responseSpec();
		ApiUtils.addRequestLogs(requestSpec);
		
		Response response = given()
				.spec(requestSpec)
				.when()
				.put(EndPoint.PLAYLIST.get() + playlistId)
				.then()
				.spec(responseSpec)
				.extract()
				.response();
		
		ApiUtils.addResponseLogs(response);
	}
	

}
