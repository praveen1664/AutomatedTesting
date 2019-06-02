package com.optum.acoe.StepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import com.aventstack.extentreports.GherkinKeyword;

import com.optum.acoe.ApplicationFunLib.BookedValue;
import com.optum.acoe.ApplicationFunLib.ErrorData;
import com.optum.acoe.ExcelFunctionLib.ExcelFunctionLib;
import com.optum.acoe.PageObjects.SentinelApplication.SentinelHome;
import com.optum.acoe.PageObjects.SentinelApplication.SentinelLogin;
import com.optum.acoe.PageObjects.SentinelApplication.SentinelWhiteBoard;
import com.optum.acoe.Utils.ExtentReportUtil;
import com.optum.acoe.Utils.PropertyReader;

import com.optum.acoe.WebFunctionLib.SeleniumSelect;
import com.optum.acoe.WebFunctionLib.WebFunctionLib;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Sentinel {

	WebFunctionLib web = new WebFunctionLib();
	int tcosum = 0;
	public static int actualPageTCO = 0, actualTCOsum = 0;
	PropertyReader reader = new PropertyReader();
	SentinelHome sentinelhome;
	SentinelLogin sentinellogin;
	ExcelFunctionLib excel = null;
	HashMap<Integer, String> columnsMap;
	int app_col_index;
	String businessgroupOption;
	String assetsgroupOption;
	LinkedList<ErrorData> errorrows;
	ErrorData errordata;
	public static HashMap<String, Integer> errorTCO;

	
	@Given("^Browser is launched$")
	public void this_is_a_test_step() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Given"),"Browser is launched");
		
		//reading business and assets group values from config.properties file.
		businessgroupOption = reader.readProperty("businessgroup");
		assetsgroupOption = reader.readProperty("assetsgroup");
		
		web.launchBrowser();

	}
	
	@When("^Navigate to the sentinel application$")
	public void navigate_to_the_sentinel_application() throws Throwable {
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("When"),"Navigate to the sentinel application");
		
		//navigating to URL mentioned in config.properties file.
		
		web.navigateToURL("Sentinelapplicationurl");

	}

	@When("^Enter login credentials and click on login button$")
	public void enter_login_credentials_and_click_on_login_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("When"),"Enter login credentials and click on login button");

		sentinellogin = new SentinelLogin();
		sentinellogin.EnterCredentials(reader.readProperty("Sentinelusername"), reader.readProperty("Sentinelpassword"));
		sentinellogin.login();
	}

	@Then("^Select business segment and assets group from toggle menu$")
	public void select_business_segment_and_assets_group_from_toggle_menu() throws InterruptedException {
		
		sentinelhome = new SentinelHome();

		sentinelhome.clickTogglebtn();
		
		WebElement businessgroup = WebFunctionLib.driver.findElement(By.xpath("//*[@id='business-segment-switch']"));
		SeleniumSelect sel = new SeleniumSelect(businessgroup);
		sel.selectByText(businessgroupOption);
		
		WebElement assetsgroup = WebFunctionLib.driver.findElement(By.xpath("//*[@id = 'asset-group-switch']"));
		sel = new SeleniumSelect(assetsgroup);
		sel.selectByText(assetsgroupOption);
		
		sentinelhome.clickTogglebtn();
		
		
	}
	
	@When("^Move to the Whiteboard tab$")
	public void move_to_the_Whiteboard_tab() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("When"), "Move to the Whiteboard tab");
		
		Thread.sleep(5000);
		
		System.out.println("after sleep");
		
		sentinelhome = new SentinelHome();
		
		sentinelhome.clickOnWhiteboardLink();
	   
	}
	
	@Then("^verify the page TCO sum of first page$")
	public void verify_the_page_TCO_sum_of_first_page() throws Throwable {
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("When"),"verify the page TCO sum of first page");
		
		int pageTCO = 0;
		
		WebElement table = WebFunctionLib.driver.findElement(By.id("datatableWhiteboard"));
		
		for(int i = 1; i < web.getTableRowNumber(table)-1; i++) {
			for(int j = 1; j < web.getTableColNumber(table); j++) {
				String cellData = web.readCell(table, i, j);
				System.out.println("cell data:"+ cellData + " rowindex: " + i + " column index: " + j + " in page number: 1");
				
				if(cellData == null || cellData == "") {
//					System.out.println("returned balnk or null cell value, data:"+ cellData + " rowindex: " + i + " column index: " + j + " in page number: 1");
					
				}
				
				if(j== (web.getTableColNumber(table)-1)) {
					cellData = cellData.replace("$", "").replace(",", "");
					pageTCO += Integer.parseInt(cellData);
				}
				
			}					
		}
		
		String temp = WebFunctionLib.driver.findElement(By.xpath("//*[@id = \"datatableWhiteboard\"]/TFOOT/TR/TD/SPAN")).getText().replace(" ", "").replace(",", "").replace("$", "");
		actualPageTCO = Integer.parseInt(temp.split("/")[0]);
		actualTCOsum = Integer.parseInt(temp.split("/")[1]);
		System.out.println(actualPageTCO + "/" + actualTCOsum);
		System.out.println("page sum: " + pageTCO);
		
//		assertEquals(pageTCO, actualPageTCO);
		if(!(pageTCO==actualPageTCO)) {
			errorTCO.put("pagetco", pageTCO);
		}
		
		web.Click(WebFunctionLib.driver.findElement(By.linkText("Next")));
		
	}

	@When("^Move through all the pages and validate TCO sums$")
	public void move_through_all_the_pages_and_validate_TCO_sums() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"Move through all the pages and validate TCO sums");
		
//		System.out.println("cell value: "+web.readCell(WebFunctionLib.driver.findElement(By.id("datatableWhiteboard")), 1, 8));
		
		
		int tot = WebFunctionLib.driver.findElements(By.xpath(".//*[@id=\"datatableWhiteboard_paginate\"]/Ul/li")).size();
		WebElement lastpage = WebFunctionLib.driver.findElements(By.xpath(".//*[@id=\"datatableWhiteboard_paginate\"]/Ul/li")).get(tot-2);
		int numOfPages = Integer.parseInt(web.getText(lastpage));
		System.out.println("number of pages:" + numOfPages);
		
		
			for(int k = 2; k <= numOfPages; k++) {
				
				String temp = WebFunctionLib.driver.findElement(By.xpath("//*[@id = \"datatableWhiteboard\"]/TFOOT/TR/TD/SPAN")).getText().replace(" ", "").replace(",", "").replace("$", "").split("/")[0];
				tcosum += Integer.parseInt(temp);
				
				if(k < numOfPages)
					web.Click(WebFunctionLib.driver.findElement(By.linkText("Next")));
				
			}
			
			tcosum += actualPageTCO;
			
			System.out.println("TCO sum:" + tcosum);
			
//			assertEquals(tcosum, actualTCOsum);
			if(!(tcosum==actualTCOsum)) {
				errorTCO.put("totaltco", tcosum);
			}
			
			ExcelFunctionLib.writeErrorInfoValidateTCO(errorTCO, Sentinel.actualPageTCO, Sentinel.actualTCOsum);
			
	}
	
	@Then("^Logout from the application$")
	public void logout_from_the_application() throws Throwable{
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"Logout from the application");
		
//		sentinelhome.Logout();
		Thread.sleep(2000);
		web.Click(WebFunctionLib.driver.findElement(By.xpath("//DIV/DIV[2]/nav/DIV/DIV[2]/UL/LI[2]/A")));
		web.Click(WebFunctionLib.driver.findElement(By.xpath("//*[text() = ' Sign Out']")));
		
	}
	
	@Then("^Close browser$")
	public void close_browser() throws Throwable{
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"Close browser");
		
		web.closeBrowser();
	
	}
	
	
	@Then("^Move through all the pages and validate that there no null values$")
	public void move_through_all_the_pages_and_validate_that_there_no_null_values() throws Throwable{
		
		ExtentReportUtil.step = ExtentReportUtil.scenario.createNode(new GherkinKeyword("Then"),"Move through all the pages and validate that there no null values");
		
		excel = new ExcelFunctionLib();
		
		errorrows = new LinkedList<ErrorData>();
	
		
		int tot = WebFunctionLib.driver.findElements(By.xpath(".//*[@id=\"datatableWhiteboard_paginate\"]/Ul/li")).size();
		System.out.println("tot:" + tot);
		WebElement lastpage = WebFunctionLib.driver.findElements(By.xpath(".//*[@id=\"datatableWhiteboard_paginate\"]/Ul/li")).get(tot-2);
		System.out.println("lastpage:" + lastpage.getText());
		int numOfPages = Integer.parseInt(web.getText(lastpage));
		System.out.println("number of pages:" + numOfPages);
		
		//adding the column names to hashmap<columnindex,columnname>
		columnsMap = new HashMap<Integer,String>();
		WebElement header_row = WebFunctionLib.driver.findElement(By.id("datatableWhiteboard")).findElements(By.tagName("tr")).get(0);
		int numOfCols = header_row.findElements(By.tagName("th")).size();
		for(int i=0; i < numOfCols; i++) {
			String value = header_row.findElements(By.tagName("th")).get(i).getText();
			columnsMap.put(i, value);
			if(value.equals("App Name")) 
				app_col_index = i;
		}
		
		for(int k = 1; k <= numOfCols; k++) {
			
			WebElement table = WebFunctionLib.driver.findElement(By.id("datatableWhiteboard"));
			
			for(int i = 1; i < web.getTableRowNumber(table)-1; i++) {
				for(int j = 1; j < web.getTableColNumber(table); j++) {
					String cellData = web.readCell(table, i, j);
//					System.out.println("cell data:"+ cellData + " rowindex: " + i + " column index: " + j + " in page number: " + k );
					
					if(cellData.length() == 0) {
//						System.out.println("returned blank or null cell value, data:"+ cellData + " rowindex: " + i + " column index: " + j + " in page number: " + k);
						String temp = web.readCell(table, i, app_col_index);
						errordata = new ErrorData(businessgroupOption, assetsgroupOption,  temp, columnsMap.get(j), k, i, (j+1));
						errorrows.add(errordata);
					}
					
				}					
			}
			
			String temp = WebFunctionLib.driver.findElement(By.xpath("//*[@id = \"datatableWhiteboard\"]/TFOOT/TR/TD/SPAN")).getText().replace(" ", "").replace(",", "").replace("$", "").split("/")[0];
			tcosum += Integer.parseInt(temp);
			
			if(k < numOfPages)
				web.Click(WebFunctionLib.driver.findElement(By.linkText("Next")));
			
		}
	
		System.out.println("================================");
		System.out.println(errorrows);
		
		excel.enterErrorDataInfo(errorrows);
	
	}
	
	@Then("^Validate Tech Padu Graph$")
	public void validate_Tech_Padu_Graph() throws Throwable {
		
		HashMap<String, Integer> map1 = new HashMap<String, Integer>();
		map1.put("P", 35);
		map1.put("A",52);
		map1.put("D", 9);
		map1.put("U", 11);
		map1.put("NA", 67);
		map1.put("Multiple", 23);
		
		HashMap<String, Integer> map2 = getValuesfromwhiteboard(map1.keySet());
		ExcelFunctionLib.writeTechPaduErrorvalues(map1,map2);
		
	}
	

	@Then("^Validate Booked Value Table$")
	public void Validate_Booked_Value_Table() throws Throwable {
		
		LinkedList<BookedValue> dashboardbookedvalues1 = getBookedValuesfromDashboard();
		String capability;
		String filter;
		LinkedList<BookedValue> dashboardbookedvalues2 = new LinkedList<BookedValue>();
		move_to_the_Whiteboard_tab();
		
		Iterator<BookedValue> itr = dashboardbookedvalues1.iterator();
		
		while(itr.hasNext()) {
			
			Thread.sleep(3000);
			SentinelWhiteBoard.clickResetBtn();
			Thread.sleep(2000);
			capability = itr.next().getCapability();
			filter = "app.capability = '" + capability + "'";
			SentinelWhiteBoard.enterFilter(filter);
			int rec = SentinelWhiteBoard.numberOfRecords();
			
			String temp = WebFunctionLib.driver.findElement(By.xpath("//*[@id = \"datatableWhiteboard\"]/TFOOT/TR/TD/SPAN")).getText().replace(" ", "").replace(",", "").replace("$", "");
			float f = Float.parseFloat(temp.split("/")[1]);
			
			dashboardbookedvalues2.add(new BookedValue(capability,rec,f));
			
			
			
		}
		writeErrorInfoTOExcel(dashboardbookedvalues1, dashboardbookedvalues2);
	}

	private void writeErrorInfoTOExcel(LinkedList<BookedValue> dashboardbookedvalues1,
			LinkedList<BookedValue> dashboardbookedvalues2) throws IOException {
		// TODO Auto-generated method stub
		String temp = null;
		LinkedList<String> errorData = new LinkedList<String>();
		for(int i=0; i < dashboardbookedvalues1.size(); i++) {
			
			if(!(dashboardbookedvalues1.get(i).compare(dashboardbookedvalues2.get(i)))) 
				temp = dashboardbookedvalues1.get(i).toString() + "|" + dashboardbookedvalues2.get(i).toString();
		
			if(temp != null)
				errorData.add(temp);
			temp = null;
		}
		ExcelFunctionLib.writeErrorBookedValueInToExcel(errorData);
		
	}

	private LinkedList<BookedValue> getBookedValuesfromDashboard() {
		// TODO Auto-generated method stub
		LinkedList<BookedValue> list = new LinkedList<BookedValue>();
		WebElement tbody = WebFunctionLib.driver.findElement(By.id("bookedValueIdTbody"));
		int rows = tbody.findElements(By.tagName("tr")).size();
		
		for(int i = 0; i < rows-1; i++) {
			
			WebElement row = tbody.findElements(By.tagName("tr")).get(i);
			
			String capability = row.findElements(By.tagName("td")).get(0).getText();
			int numOfApps = Integer.parseInt(row.findElements(By.tagName("td")).get(1).getText());
			float tcosum = Float.parseFloat(row.findElements(By.tagName("td")).get(2).getText().replace(" ", "").replace(",", "").replace("$", ""));
			BookedValue bvalue = new BookedValue(capability,numOfApps,tcosum);
			
			list.add(bvalue);	
			
		}
		
		return list;
	}
	
	private HashMap<String, Integer> getValuesfromwhiteboard(Set<String> set) throws Throwable {
		// TODO Auto-generated method stub
		move_to_the_Whiteboard_tab();
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Iterator<String> itr = set.iterator();
		while(itr.hasNext()) {
			
			String key = itr.next();
			String filter = "app.PADUSTATUS  ";
			if(key.equals("Multiple"))
				filter = filter + "NOT IN('P','A','D','U','NA')";
			else
				filter = filter + "= '" + key + "'";
			SentinelWhiteBoard.enterFilter(filter);
			int rec = SentinelWhiteBoard.numberOfRecords();
			map.put(key, rec);
			
			Thread.sleep(2000);
			SentinelWhiteBoard.clickResetBtn();
			Thread.sleep(2000);
		}
		return map;
	}
	
}
