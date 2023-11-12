package com.qa.stepdef;

import com.aventstack.extentreports.ExtentTest;
import com.qa.pages.BasePage;
import com.qa.runners.MyRunnerTest;
import com.qa.utils.DriverManager;
import com.qa.utils.VideoManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;

import java.io.IOException;

public class Hooks {

	
	public static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	public static ExtentTest test;
	public static String testCaseName;
	
    @Before
    public void initialize(Scenario scenario) throws Exception {
        BasePage basePage = new BasePage();
        basePage.closeApp();
        basePage.launchApp();

/*        GlobalParams params = new GlobalParams();
        params.initializeGlobalParams();

        ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_"
                + params.getDeviceName());

        new ServerManager().startServer();
        new DriverManager().initializeDriver();*/
        new VideoManager().startRecording();
		 testCaseName = scenario.getName();
		 test = MyRunnerTest.extent.
				createTest("TC_Name :: "+testCaseName);
		System.out.println("TC_Name :: "+testCaseName);
		extentTest.set(test);
    }

    @After
    public void quit(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        new VideoManager().stopRecording(scenario.getName());
/*        DriverManager driverManager = new DriverManager();
        if(driverManager.getDriver() != null){
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }
        ServerManager serverManager = new ServerManager();
        if(serverManager.getServer() != null){
            serverManager.getServer().stop();
        }*/
    }
}
