package com.reqres.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReport/ReqresAPISuite.html");
		sparkReporter.config().setDocumentTitle("RegresAPIAutomation"); // report title
		sparkReporter.config().setReportName("API_Automation"); // report name
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Name", "ReqresRestAPI");
		extent.setSystemInfo("Host Name", "Loca Host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Automation Tester");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.PASS, "Test Case Passed is : " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case Failed is : " + result.getName());
		test.log(Status.FAIL, "Test Case Failed Exception : " + result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case Skipped is : " + result.getName());
	}

	public void onFinish(ITestResult result) {
		extent.flush();

	}

	@Override
	public void onTestStart(ITestResult result) {
//		System.out.println(result.getName()+ " starts**##@@");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
