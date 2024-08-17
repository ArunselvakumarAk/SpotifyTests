package com.spotify.commonUtils.reports;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
	public static ExtentReports extentReport;
	
	public static ExtentReports setupExtentReport(ITestContext context) {
		
		LocalDateTime dateAndTime = LocalDateTime.now();
		String formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmmss").format(dateAndTime);
		
		String className = context.getAllTestMethods()[0].getTestClass().getName();
        String reportPath;

        if (className.startsWith("com.spotify.api")) {
            reportPath = System.getProperty("user.dir") + "/reports/SpotifyApi_TestReport_" + formattedDate + ".html";
        } else if (className.startsWith("com.spotify.web")) {
            reportPath = System.getProperty("user.dir") + "/reports/SpotifyWeb_TestReport_" + formattedDate + ".html";
        } else {
            reportPath = System.getProperty("user.dir") + "/reports/Spotify_TestReport_" + formattedDate + ".html";
        }
		
		//String reportPath = System.getProperty("user.dir") + "/reports/SpotifyWeb_TestReport_" + formattedDate + ".html";
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		
		sparkReporter.config().setReportName("Spotify Test Suite Report");
		sparkReporter.config().setDocumentTitle("Spotify Test Results Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Executed on operating system: ", System.getProperty("os.name"));
		
		return extentReport;
		
	}
}
