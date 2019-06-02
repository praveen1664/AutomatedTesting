package com.optum.acoe.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.acoe.WebFunctionLib.WebFunctionLib;

public class MyUHCHome {

	WebFunctionLib web = new WebFunctionLib();
	
	public  MyUHCHome() {
		PageFactory.initElements(WebFunctionLib.driver, this);
	}
	
	@FindBy(xpath = "//DIV[@id=\"dashboard\"]/DIV[1]/SECTION[6]/COSTS[1]/DIV[1]/DIV[1]/DIV[1]/DIV[4]/DIV[1]/DIV[1]/A[1]")
	WebElement virtualvisitslink;
	
	public void clickVirtualVisits() {
		web.Click(virtualvisitslink);
	}
}
