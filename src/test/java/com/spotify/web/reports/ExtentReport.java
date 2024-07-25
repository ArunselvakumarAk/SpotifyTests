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
		String formattedDate = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(dateAndTime);
		
		String reportPath = System.getProperty("user.dir") + "/reports/Report_" + formattedDate + ".html";
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setDocumentTitle("Spotify Tests Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Executed on operating system: ", System.getProperty("os.name"));
		
		return extentReport;
		
	}
}
