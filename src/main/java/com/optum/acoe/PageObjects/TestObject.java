package com.optum.acoe.PageObjects;

import java.io.File;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.acoe.WebFunctionLib.WebFunctionLib;

public class TestObject {

	WebDriver driver;
	String srcPath = System.getProperty("user.dir") + File.separator + "Driver";
	WebFunctionLib web= new WebFunctionLib();
	
	public TestObject() {
		PageFactory.initElements(WebFunctionLib.driver, this);
	}
	
	public TestObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "//FORM[@id=\"tsf\"]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/INPUT[@role=\"combobox\"][1]") 
	WebElement searchbox;
	
	@FindBy(xpath = "//FORM[@id=\"tsf\"]/DIV[2]/DIV[1]/DIV[3]/CENTER[1]/INPUT[1]")
	WebElement search_btn;
	
	public void enter_text(String str) {
		
		web.enterText(searchbox, str);
	}
	
	public void searchbtn_click() {
		web.enterText(search_btn,Keys.RETURN);
	}
}
