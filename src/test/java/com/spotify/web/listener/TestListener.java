package com.spotify.web.listener;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.spotify.web.driver.DriverManager;
import com.spotify.web.reports.ExtentManager;
import com.spotify.web.reports.ExtentReport;
import com.spotify.web.utils.ResourceUtils;

@Listeners
public class TestListener implements ITestListener{
	
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		ExtentManager.getInstance().setExtent(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getInstance().getExtent().log(Status.PASS, "Test passed");
		ExtentManager.getInstance().closeExtent();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentManager.getInstance().getExtent().log(Status.FAIL, "Test failed");
		
		File scrFile = ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		LocalDateTime dateAndTime = LocalDateTime.now();
		String formattedDate = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(dateAndTime);
        String filePath = System.getProperty("user.dir") + "/reports/Screenshots/"+ result.getMethod().getMethodName()  + formattedDate+".png";
        try {
            FileUtils.copyFile(scrFile, new File(filePath));
            ResourceUtils.log.info("Screenshot saved: " + filePath);
        } catch (IOException e) {
            ResourceUtils.log.error("Failed to save screenshot: ", e);
        }
        
        ExtentManager.getInstance().getExtent().addScreenCaptureFromPath(filePath, "Screenshot of failed test case");
        ExtentManager.getInstance().closeExtent();
        
		ResourceUtils.log.info("Screenshot has been taken for the failed test case.");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentManager.getInstance().getExtent().log(Status.SKIP, "Test skipped");
		ExtentManager.getInstance().closeExtent();
	}

	@Override
	public void onStart(ITestContext context) {
		report = ExtentReport.setupExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
	
}
