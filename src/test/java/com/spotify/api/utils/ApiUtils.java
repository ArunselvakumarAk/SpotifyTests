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
			
			ExtentManager.getInstance().logInfo("EndPoint : " + query.getBaseUri());
			ExtentManager.getInstance().logInfo("ContentType " + query.getContentType());
			ExtentManager.getInstance().logInfo("Request Headers: ");
			ExtentManager.getInstance().logHeaders(filteredHeaders);
			ExtentManager.getInstance().logInfo("Request Body: ");
			ExtentManager.getInstance().logJson((query.getBody() == null ? "No Body" : query.getBody()));
		} else {
			ExtentManager.getInstance().logInfo("Request : Null");
		}
	}
	
	public static void addResponseLogs(Response response) {
		if (response != null) {
			ExtentManager.getInstance().logInfo("Response Status Code: " + response.getStatusCode());
			ExtentManager.getInstance().logInfo("Response Headers: ");
			ExtentManager.getInstance().logHeaders(response.getHeaders().asList());
			ExtentManager.getInstance().logInfo("Response Body: ");
			ExtentManager.getInstance().logJson(response.getBody().asString());
		} else {
			ExtentManager.getInstance().logInfo("Response : Null");
		}
	}
}
