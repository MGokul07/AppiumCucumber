package com.qa.runners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport {
	public static ExtentSparkReporter es;
    public static ExtentReports extent;
    public static ExtentTest test;
    
    private static String DIR_PATH = System.getProperty("user.dir");
    private static String reportPath = DIR_PATH+File.separator+"report"+File.separator;
    private static String reportName;
    
    /**
	  *@method getExtentInstance() method which helps to generate the extent report to track the status of Test's
	  *@return object of ExtentReports
	  */
    public static ExtentReports getExtentInstance()
    {
    	reportName = reportPath+getReportName();
        es = new ExtentSparkReporter(reportName);
        es.config().setDocumentTitle("Appium Cucumber Framework");
        es.config().setReportName("RegressionSuite");
        extent = new ExtentReports();
        extent.attachReporter(es);
        return extent;
    }

    /**
	  *@method getReportName() method which helps to create the report name with time stamp
	  *@return String -- name of the extent report postfix with time stamp
	  */
    public static String getReportName()
    {
    	String d = new SimpleDateFormat("E MMM.dd.yyyy.HH.mm.ss").format(new java.util.Date());
        
        String reportName="Report_"+d.toString().replace(":","_").
                replace(" ","_")+".html";

        return reportName;
    }
    
    
    /**
	  *@method getScreenshotnameofFailureTest() method which helps to create the screenshot name with time stamp
	  *@return String -- name of the extent report postfix with time stamp
	  */
    public static String getScreenshotnameofFailureTest(String testCasename) {
		 Date d = new Date();
        return testCasename+"_"+d.toString().replace(":","_").
	                replace(" ","_");
	}
    
        
    
    public static void main(String[] args) {
		System.out.println(reportPath);
	}
}
