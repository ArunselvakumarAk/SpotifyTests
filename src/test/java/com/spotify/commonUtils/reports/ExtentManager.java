package com.spotify.commonUtils.reports;

import java.util.List;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.spotify.web.driver.DriverManager;

import io.restassured.http.Header;

public class ExtentManager {
	public static volatile ExtentManager instance;
	public ThreadLocal<ExtentTest> extent = new ThreadLocal<>();
	
	private ExtentManager() {}
	
	public static ExtentManager getInstance() {
		if(instance==null) {
			synchronized(DriverManager.class){
				if(instance==null) {
					instance = new ExtentManager();
				}
			}
		}
		return instance;
	}
	
	public void setExtent(ExtentTest object) {
		this.extent.set(object);
	}
	
	public ExtentTest getExtent() {
		return extent.get();
	}
	
	public void closeExtent() {
		extent.remove();
	}
	
	public void logPassDetails(String log) {
		extent.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}
	
	public void logFailureDetails(String log) {
		extent.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
	}
	
	public void logExceptionDetails(String stackTrace) {
		stackTrace = stackTrace.replaceAll(",", "<br>");
		String formattedStackTrace = "<details>\r\n"
				+ "  <summary>Detailed Exception Logs</summary>\r\n"
				+ "  "+stackTrace+"\r\n"
				+ "</details>";
		extent.get().info(formattedStackTrace);
	}
	
	public void logInfo(String infoLog) {
		extent.get().info(MarkupHelper.createLabel(infoLog, ExtentColor.GREY));
	}
	
	public void logSkippedDetails(String infoLog) {
		extent.get().skip(MarkupHelper.createLabel(infoLog, ExtentColor.GREY));
	}
	
	public void logJson(String json) {
		extent.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
	}
	
	public void logHeaders(List<Header> headers) {
		String[][] Headersarray = headers
				.stream()
				.map(header -> new String[] {header.getName(), header.getValue()})
				.toArray(String[][]::new);
		extent.get().info(MarkupHelper.createTable(Headersarray));
	}
}
