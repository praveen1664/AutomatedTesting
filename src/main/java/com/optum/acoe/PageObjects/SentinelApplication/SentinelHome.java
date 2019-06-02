package com.optum.acoe.PageObjects.SentinelApplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.acoe.WebFunctionLib.SeleniumSelect;
import com.optum.acoe.WebFunctionLib.WebFunctionLib;

public class SentinelHome {

	public SentinelHome() {
		PageFactory.initElements(WebFunctionLib.driver, this);
	}
	
	WebFunctionLib web = new WebFunctionLib();
	
	@FindBy(id = "paduStatusChartAreaChart")
	private WebElement firstChart;
	
	@FindBy(xpath = "//DIV[2]/DIV[1]/DIV[1]/UL[@role=\"tablist\"][1]/LI[2]")
	private WebElement whiteboard;
	
	@FindBy(xpath = "//DIV/DIV[2]/nav/DIV/DIV[2]/UL/LI[2]/A")
	private WebElement AccountTab;
	
	@FindBy(xpath = "//*[@id = 'menu-toggle']")
	private WebElement togglebtn;
	
	@FindBy(xpath = "//*[@id='business-segment-switch']")
	private WebElement business_segment;
	
	@FindBy(xpath = "//*[@id = 'asset-group-switch']")
	WebElement assetsgroups;
	
	
	private void click_AccountTab() {
		
		web.Click(WebFunctionLib.driver.findElement(By.xpath("//DIV/DIV[2]/nav/DIV/DIV[2]/UL/LI[2]/A")));
	}
	
	private void click_Logoutbtn () {
	
		web.Click(WebFunctionLib.driver.findElement(By.xpath("//DIV/DIV[2]/nav/DIV/DIV[2]/UL/LI[2]/UL/LI[3]/A")));
	}
	
	public void Logout () {
		
		click_AccountTab();
		click_Logoutbtn();
	}
	
	public String getText_firstChart() {
		return web.getText(firstChart);
	}

	public void clickOnWhiteboardLink() {
		// TODO Auto-generated method stub
		whiteboard.click();
	}
	
	public void clickTogglebtn() {
		
		web.Click(togglebtn);
	}
	
	public String getBusinessSegmentText() {
		
//		return web.getText(business_segment);
		SeleniumSelect select = new SeleniumSelect(business_segment);
		return select.getFirstSelectedOption().getText();
		
	}
	
public String getAssetsGroupText() {
		
//		return web.getText(assetsgroups);
	SeleniumSelect select = new SeleniumSelect(assetsgroups);
	return select.getFirstSelectedOption().getText();
	
	}
}
