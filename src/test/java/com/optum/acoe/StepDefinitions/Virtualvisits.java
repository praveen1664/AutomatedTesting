package com.optum.acoe.StepDefinitions;

import org.openqa.selenium.By;

import com.aventstack.extentreports.GherkinKeyword;
import com.optum.acoe.PageObjects.MyUHCHome;
import com.optum.acoe.PageObjects.MyUHCLogin;
import com.optum.acoe.PageObjects.MyUHCSecurity;
import com.optum.acoe.PageObjects.MyUHCVirtualvisits;
import com.optum.acoe.Utils.ExtentReportUtil;
import com.optum.acoe.Utils.PropertyReader;
import com.optum.acoe.WebFunctionLib.WebFunctionLib;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Virtualvisits {

	WebFunctionLib web = new WebFunctionLib();
	PropertyReader reader = new PropertyReader();
	
	@Given("^Browser is Launched$")
	public void browser_is_Launched() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Given"),"Browser is Launched");
		
		web.launchBrowser();
	   
	}

	@When("^Navigate to MyUHC URL in browser$")
	public void user_enters_MyUHC_URL_in_browser_and_click_enter() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("When"),"Navigate to MyUHC URL in browser");
		
		web.navigateToURL("MyUHCapplicationurl");
		
	}


	@When("^Enter login credentials and click on sign in button$")
	public void enter_password_in_MyUHC_portal_login_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("When"),"Enter login credentials and click on sign in button");
		
		MyUHCLogin loginpage = new MyUHCLogin();
		loginpage.enterCredentials(reader.readProperty("MyUHCusername"), reader.readProperty("MyUHCpassword"));
		loginpage.login();
	}

	@When("^Answer the security question and click submit button$")
	public void user_clicks_on_sign_in_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("When"),"Answer the security question and click submit button");
		
		Thread.sleep(30000);
		System.out.println("after sleep");
	   MyUHCSecurity sec = new MyUHCSecurity();
	   sec.answerQuestion();
	}

	@Then("^User should navigate to the My UHC portal Home page$")
	public void user_should_navigate_to_the_My_UHC_portal_Home_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"User should navigate to the My UHC portal Home page");
		
		
	}

	@When("^Click on virtual visits link, under Common Services and Costs$")
	public void the_user_should_click_on_virtual_visits_link_under_Common_Services_and_Costs() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("When"),"Click on virtual visits link, under Common Services and Costs");

		MyUHCHome home = new MyUHCHome();
		home.clickVirtualVisits();
	}

	@Then("^Should be navigated to virtual visits section, under Common Services and Costs$")
	public void the_user_should_view_virtual_visits_section_under_Common_Services_and_Costs() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"Should be navigated to virtual visits section, under Common Services and Costs");

		MyUHCVirtualvisits virtualvisits = new MyUHCVirtualvisits();
		virtualvisits.displayCosts();
	}	

	@Then("^Logout of MyUHC portal$")
	public void logout_of_MyUHC_portal() throws Throwable{
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"Logout of MyUHC portal");
		
		web.Click(WebFunctionLib.driver.findElement(By.xpath("//*[@id = \"account-btn\"]/DIV/BUTTON")));
		web.Click(WebFunctionLib.driver.findElement(By.xpath("//*[@id = \"account-btn\"]/DIV/DIV/A[6]")));
		Thread.sleep(2000);
	}
	
	@Then("^close the browser$")
	public void close_the_browser() throws Throwable{
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"close the browser");
		
		web.closeBrowser();
	}
}
