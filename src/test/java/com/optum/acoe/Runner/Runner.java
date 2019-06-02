package com.optum.acoe.Runner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.optum.acoe.ExcelFunctionLib.ExcelFunctionLib;
import com.optum.acoe.Utils.ExtentReportUtil;
import com.optum.acoe.Utils.PropertyReader;


import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features    = { "src/test/resources/FeatureFiles/Sentinel.feature"},
		glue 		= { "com.optum.acoe.StepDefinitions" },
		plugin      = {"json:target/cucumber.json","html:target/cucumber-html"},
		tags = {"@tag1"}
		)
public class Runner {
	public static int totalScenario;
	public static int failedScenario;
	public static LinkedHashMap<String, String> scenarios;
	@BeforeClass
	public static void initialize() {
		PropertyReader reader = new PropertyReader(System.getProperty("user.dir") + "//src//main//resources//log4j.properties");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		String value = "src/test/resources/Logs/application_" + dateFormat.format(new Date()) +".html";
		System.out.println(value);
		reader.setProperty("log4j.appender.file.File", value);
//		ExtentReportUtil extentReportUtil = new ExtentReportUtil();
		ExtentReportUtil.ExtentReport();
//		ExtentReportUtil.features = ExtentReportUtil.extent.createTest("Test Feature");
		scenarios = new LinkedHashMap<String, String>();
	}
	
	@AfterClass
	public static void tearDown() throws IOException {
		ExtentReportUtil.flushReport();
		ExcelFunctionLib.generateGenericReport(totalScenario, failedScenario, scenarios);
	}
}
