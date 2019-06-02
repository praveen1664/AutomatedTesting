package com.optum.acoe.PageObjects.SentinelApplication;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.acoe.WebFunctionLib.WebFunctionLib;

public class SentinelLogin {

	public SentinelLogin() {
	
		PageFactory.initElements(WebFunctionLib.driver, this);
		
	}
	
	WebFunctionLib web = new WebFunctionLib();
	
	@FindBy(xpath = ("//DIV[normalize-space()=\"Username\"]/INPUT[1]"))
	WebElement usernameField;
	
	@FindBy(xpath = ("//DIV[normalize-space()=\"Password\"]/INPUT[1]"))
	WebElement passwordField;
	
	@FindBy(xpath = "//FORM[@id=\"formId\"]/DIV[1]/DIV[3]/BUTTON[1]")
	WebElement submitBtn;
	
	public void  EnterCredentials(String username, String password) {
		
		web.enterText(usernameField, username);
		web.enterText(passwordField, password);
		
	}
	
	public void login() {
		
		
		web.Click(submitBtn);
	}
}
