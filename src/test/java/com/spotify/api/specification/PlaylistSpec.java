package com.spotify.api.specification;

import java.util.ArrayList;
import java.util.List;
import com.spotify.api.pojo.entities.Tracks;
import com.spotify.api.pojo.requestmodel.playlist.AddItemsToPlaylistModel;
import com.spotify.api.pojo.requestmodel.playlist.RemoveItemsFromPlaylistModel;
import com.spotify.api.pojo.requestmodel.playlist.Track;
import com.spotify.api.utils.AuthUtil;
import com.spotify.web.utils.ResourceUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PlaylistSpec {
	
	public static RequestSpecification requestSpecForChangePlayListDetails() {	
		return new RequestSpecBuilder()
				.setBaseUri(System.getProperty("apiEnv",ResourceUtils.getProperty("apiConfiguration//apiConfig.properties", "baseUrl")))
				.setBody("ChangePlaylistDetailsModel.builder().name(\"Playlist Name\").description(\"Playlist Descripton\").isPublic(false).build()")
				.addHeader("Authorization", "Bearer " + AuthUtil.getAuthToken())
				.build();
	}
	
	public static RequestSpecification requestSpecForAddTrack() {	
		List<String> uris = ResourceUtils.getJsonData("tracksConfig.json", Tracks.class).getTracks();
		return new RequestSpecBuilder()
				.setBaseUri(System.getProperty("apiEnv",ResourceUtils.getProperty("apiConfiguration//apiConfig.properties", "baseUrl")))
				.addHeader("Authorization", "Bearer " + AuthUtil.getAuthToken())
				.setBody(AddItemsToPlaylistModel.builder().uris(uris).position(0).build())
				.build();
	}
	
	public static RequestSpecification requestSpecForRemoveTrack() {	
		List<String> trackIds = ResourceUtils.getJsonData("tracksConfig.json", Tracks.class).getTracks();
		List<Track> tracks = new ArrayList<>();
		for(int i =0;i<trackIds.size();i++) {
			tracks.add(Track.builder().uri(trackIds.get(i)).build());
		}
		
		return new RequestSpecBuilder()
				.setBaseUri(System.getProperty("apiEnv",ResourceUtils.getProperty("apiConfiguration//apiConfig.properties", "baseUrl")))
				.addHeader("Authorization", "Bearer " + AuthUtil.getAuthToken())
				.setBody(RemoveItemsFromPlaylistModel.builder().tracks(tracks).build())
				.build();
	}
	
	public static ResponseSpecification responseSpecForCreation() {
		return new ResponseSpecBuilder()
				.expectStatusCode(201)
				.build();
	}
	
	public static ResponseSpecification responseSpec() {
		return new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
	}
}
