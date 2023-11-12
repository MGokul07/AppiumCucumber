package com.qa.runners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.stepdef.Hooks;
import com.qa.utils.DriverManager;

import io.appium.java_client.AppiumDriver;

public class TestListener implements ITestListener{

	private static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
        String screenshotName=Hooks.testCaseName.replace(" ", "")+".png";
        AppiumDriver driver = DriverManager.getDriver();
        if(driver!=null)
        {
        DriverManager.getScreenshotForFailures(screenshotName, driver);
        Hooks.extentTest.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",
				MediaEntityBuilder.createScreenCaptureFromPath(screenshotName + ".png").build());
        }
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			Hooks.extentTest.get().fail("Test failed, cannot attach screenshot");
		}

		Hooks.extentTest.get().log(Status.FAIL,Hooks.testCaseName+" Test Failed");

		 MyRunnerTest.extent.flush();	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


}
