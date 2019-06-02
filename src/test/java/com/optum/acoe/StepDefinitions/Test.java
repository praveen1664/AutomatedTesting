package com.optum.acoe.StepDefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;

import com.aventstack.extentreports.GherkinKeyword;

import com.optum.acoe.CucumberTransform.EmailTransform;
import com.optum.acoe.PageObjects.TestObject;
import com.optum.acoe.Utils.ExtentReportUtil;
import com.optum.acoe.WebFunctionLib.SeleniumActions;
import com.optum.acoe.WebFunctionLib.WebFunctionLib;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Test {
	 Logger log = Logger.getLogger(Test.class);

	@Given("^open the browser$")
	public void open_the_browser() throws Throwable {
	   
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Given"),"open the browser");
		System.out.println("inside test");
//	try
	{
		
		WebFunctionLib web = new WebFunctionLib();
		web.launchBrowser();
		web.navigateToURL("applicationurl");
		Thread.sleep(5000);
		TestObject tobj = new TestObject();
		Thread.sleep(5000);
		System.out.println("after sleep");
		tobj.enter_text("ACOE");
		tobj.searchbtn_click();
		Thread.sleep(5000);
		SeleniumActions selactions = new SeleniumActions();
		System.out.println("after sleep");
		selactions.SendKeys(Keys.CONTROL);
		selactions.SendKeys("a");
		System.out.println("end");
//		web.closeBrowser();
//		throw new Exception("wrong");
		
	}
//		catch(Exception e)
//		{
//			log.error(e);
//			ExtentReportUtil.features.log(Status.FAIL, e);
//			System.exit(-1);
//		}

	}
	
	@Then("^enter your mailid$")
	public void enter_your_mailid() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"enter your mailid");
		
	}
	
	@Then("^enter your \"([^\"]*)\"$")
	public void enter_your(@Transform(EmailTransform.class) String arg1) throws Throwable {
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"enter your email");
		try {
	   System.out.println("email: " + arg1);
		}
		catch(Exception e) {
			ExtentReportUtil.step.fail(e);
		}
	}
}
