package com.optum.acoe.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.acoe.WebFunctionLib.WebFunctionLib;

public class MyUHCLogin {
	
	WebFunctionLib web = new WebFunctionLib();
	
	public MyUHCLogin() {
		PageFactory.initElements(WebFunctionLib.driver, this);
	}
	
	@FindBy(xpath = "//INPUT[@id=\"hsid-username\"]")
	WebElement username;
	
	@FindBy(xpath = "//INPUT[@id=\"hsid-password\"]")
	WebElement password;
	
	@FindBy(xpath = "//BUTTON[@id=\"hsid-submit\"]/SPAN[normalize-space()=\"Sign in\"]")
	WebElement signin;
	
	public void enterCredentials(String user, String pass) {
		web.enterText(username, user);
		web.enterText(password, pass);
	}
	
	public void login() {
		web.Click(signin);
	}

}
