package com.optum.acoe.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil {

	public static ExtentReports extent;
	public static ExtentTest scenario;
	public static ExtentTest features;
	public static ExtentTest step = null;
	public static String featurename = null;
	public static String fileName = System.getProperty("user.dir") + "/src/test/resources/Reports/extentReports/extentreport.html";
	
	public static void ExtentReport() {
		extent = new ExtentReports();
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(fileName);
		htmlreporter.config().setTheme(Theme.DARK);
		htmlreporter.config().setDocumentTitle("Test report for selenium");
		htmlreporter.config().setEncoding("UTF-8");
		htmlreporter.config().setReportName("Test Report");
		extent.attachReporter(htmlreporter);
	}
	
	public static void flushReport() {
		extent.flush();
	}
}
