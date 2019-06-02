package com.optum.acoe.StepDefinitions;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.optum.acoe.Runner.Runner;
import com.optum.acoe.Utils.ExtentReportUtil;
import com.optum.acoe.WebFunctionLib.WebFunctionLib;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberHooks {
	
	Logger log = Logger.getLogger(CucumberHooks.class);

	@Before
	public void initialize(Scenario scenario) {
		log.info("started scenario \"" + scenario.getName() +"\"");
		String temp = scenario.getId().split(";")[0];
		Runner.totalScenario++;
		if(ExtentReportUtil.featurename == null || !temp.equals(ExtentReportUtil.featurename)) {
			System.out.println("inside loop");
			ExtentReportUtil.flushReport();
			ExtentReportUtil.features = ExtentReportUtil.extent.createTest(temp);
			ExtentReportUtil.featurename = temp;
		}
		System.out.println("scenario name:"+scenario.getName());
		ExtentReportUtil.scenario = ExtentReportUtil.features.createNode(scenario.getName());
	}
	
	@After
	public void tearDown(Scenario scenario) throws IOException {


		if(scenario.isFailed()) {
			Runner.failedScenario++;
			Runner.scenarios.put(scenario.getName(), "Failed");
			String path = WebFunctionLib.takeScreeShot();
			scenario.embed(WebFunctionLib.takeScreeShotOfByte(), "image/png");
			WebFunctionLib.driver.quit();
			System.out.println("inside fail");
			System.out.println(path);
			if(path!=null) {
				ExtentReportUtil.scenario.fail("Failed in steps").addScreenCaptureFromPath(path);
				ExtentReportUtil.step.fail("Failed").addScreenCaptureFromPath(path);
			}
			log.info("scenario \"" + scenario.getName() + "\"" + " has failed");
			ExtentReportUtil.flushReport();
		}
		else {
			Runner.scenarios.put(scenario.getName(), "Passed");
			log.info("scenario \"" + scenario.getName() + "\"" + " has passed");
		}
	}
}
