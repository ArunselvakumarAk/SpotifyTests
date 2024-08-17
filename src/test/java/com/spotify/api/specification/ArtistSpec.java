package com.spotify.api.specification;

import java.util.List;
import java.util.StringJoiner;
import com.spotify.api.utils.AuthUtil;
import com.spotify.web.utils.ResourceUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ArtistSpec{
	
	public static RequestSpecification requestSpec() {	

		return new RequestSpecBuilder()
				.setBaseUri(System.getProperty("apiEnv",ResourceUtils.getProperty("apiConfiguration//apiConfig.properties", "baseUrl")))
				.addHeader("Authorization", "Bearer " + AuthUtil.getAuthToken())
				.build();
	}
	
	public static RequestSpecification requestSpecForArtist(List<String> arr) {	
		StringJoiner str= new StringJoiner(",");
		for(String s: arr) {
			str.add(s);
		}
		
		return new RequestSpecBuilder()
				.setBaseUri(System.getProperty("apiEnv",ResourceUtils.getProperty("apiConfiguration//apiConfig.properties", "baseUrl")))
				.addHeader("Authorization", "Bearer " + AuthUtil.getAuthToken())
				.addQueryParam("ids", str.toString())
				.build();
	}
	
	public static ResponseSpecification responseSpec() {
		return new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();
	}
}
