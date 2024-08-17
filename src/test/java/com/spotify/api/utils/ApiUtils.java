package com.spotify.api.utils;

import java.util.List;
import java.util.stream.Collectors;
import com.spotify.commonUtils.reports.ExtentManager;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class ApiUtils {
	public static void addRequestLogs(RequestSpecification requestSpec) {
		if (requestSpec != null) {
			
			QueryableRequestSpecification query = SpecificationQuerier.query(requestSpec);
			
			List<Header> filteredHeaders = query.getHeaders().asList().stream()
	                .filter(header -> !header.getName().equalsIgnoreCase("Authorization"))
	                .collect(Collectors.toList());
			
			ExtentManager.getInstance().addInfoLogs("EndPoint : " + query.getBaseUri());
			ExtentManager.getInstance().addInfoLogs("ContentType " + query.getContentType());
			ExtentManager.getInstance().addInfoLogs("Request Headers: " + filteredHeaders.toString());
			ExtentManager.getInstance()
					.addInfoLogs("Request Body: " + (query.getBody() == null ? "No Body" : query.getBody().toString()));
		} else {
			ExtentManager.getInstance().addInfoLogs("Request : Null");
		}
	}
	
	public static void addResponseLogs(Response response) {
		if (response != null) {
			ExtentManager.getInstance().addInfoLogs("Response Status Code: " + response.getStatusCode());
			ExtentManager.getInstance().addInfoLogs("Response Headers: " + response.getHeaders().asList().toString());
			ExtentManager.getInstance().addInfoLogs("Response Body: " + response.asString());
		} else {
			ExtentManager.getInstance().addInfoLogs("Response : Null");
		}
	}
}
