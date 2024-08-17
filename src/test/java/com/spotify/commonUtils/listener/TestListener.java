package com.spotify.commonUtils.listener;

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
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.spotify.commonUtils.reports.ExtentManager;
import com.spotify.commonUtils.reports.ExtentReport;
import com.spotify.web.driver.DriverManager;

public class TestListener implements ITestListener{
	
	ExtentReports report;

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = report.createTest(result.getMethod().getMethodName());
		ExtentManager.getInstance().setExtent(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getInstance().getExtent().pass(MarkupHelper.createLabel(Status.PASS.toString(), ExtentColor.GREEN));
		ExtentManager.getInstance().closeExtent();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentManager.getInstance().getExtent().fail(MarkupHelper.createLabel(Status.FAIL.toString(), ExtentColor.RED));
		if (result.getTestClass().getName().startsWith("com.spotify.web")) {

			File scrFile = ((TakesScreenshot) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

			LocalDateTime dateAndTime = LocalDateTime.now();
			String formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmmss").format(dateAndTime);

			String filePath = System.getProperty("user.dir") + "/reports/Screenshots/"
					+ result.getMethod().getMethodName() + formattedDate + ".png";
			try {
				FileUtils.copyFile(scrFile, new File(filePath));
			} catch (IOException e) {
			}

			ExtentManager.getInstance().getExtent().addScreenCaptureFromPath(filePath,
					"Screenshot of failed test case");
		}
        ExtentManager.getInstance().closeExtent();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentManager.getInstance().getExtent().skip(MarkupHelper.createLabel(Status.SKIP.toString(), ExtentColor.YELLOW));
		ExtentManager.getInstance().closeExtent();
	}

	@Override
	public void onStart(ITestContext context) {
		report = ExtentReport.setupExtentReport(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
	
}
