package com.spotify.web.reports;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
	public static ExtentReports extentReport;
	
	public static ExtentReports setupExtentReport() {
		
		LocalDateTime dateAndTime = LocalDateTime.now();
		String formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmmss").format(dateAndTime);
		
		String reportPath = System.getProperty("user.dir") + "/reports/Spotify_TestReport_" + formattedDate + ".html";
		
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
