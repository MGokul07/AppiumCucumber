package com.qa.runners;

import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "@LoginScenarios", features = { "src/test/resources" }, glue = { "com.qa.stepdef" })
//@Listeners({ TestListener.class })
public class MyRunnerTest extends AbstractTestNGCucumberTests {

	public static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();

	@BeforeClass
	public static void initialize() throws Exception {
		GlobalParams params = new GlobalParams();
		params.initializeGlobalParams();

		ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_" + params.getDeviceName());

		new ServerManager().startServer();
		new DriverManager().initializeDriver();
	}

	@AfterClass
	public static void quit() {
		DriverManager driverManager = new DriverManager();
		if (driverManager.getDriver() != null) {
			driverManager.getDriver().quit();
			driverManager.setDriver(null);
		}
		ServerManager serverManager = new ServerManager();
		if (serverManager.getServer() != null) {
			serverManager.getServer().stop();
		}
	}

	@BeforeSuite
	public static void createExtentReport() {
		extent = ExtentReport.getExtentInstance();
		System.out.println("******************* :" + extent);
	}

	@AfterSuite
	public static void saveExtentReport() {
		extent.flush();
	}
	
}
