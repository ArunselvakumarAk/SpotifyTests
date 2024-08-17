package com.spotify.api.utils;

import static io.restassured.RestAssured.given;
import java.time.Duration;
import java.time.Instant;

import com.spotify.web.utils.ResourceUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthUtil {
	private static volatile String authToken;
	private static volatile Instant responseTime;
	private static final Object lock = new Object();

	public static String getAuthToken() {
		String token = authToken;
		Instant time = responseTime;
		if (token == null || time == null || Duration.between(time, Instant.now()).toMinutes() >= 59) {
			synchronized (lock) {
				token = authToken;
				time = responseTime;
				if (token == null || time == null || Duration.between(time, Instant.now()).toMinutes() >= 59) {
					authToken = generateAuthToken();
				}
			}
		}
		return authToken;
	}

	public static String generateAuthToken() {
		Response response = given().baseUri(ResourceUtils.getProperty("apiConfiguration//auth.properties", "authUrl"))
				.contentType(ContentType.URLENC)
				.header("Authorization", ResourceUtils.getProperty("apiConfiguration//auth.properties", "Authorization"))
				.formParam("grant_type", "refresh_token")
				.formParam("refresh_token", ResourceUtils.getProperty("apiConfiguration//auth.properties", "refresh_token"))
				.when().post("api/token").then().statusCode(200).extract().response();

		responseTime = Instant.now();
		return response.path("access_token");
	}
}
