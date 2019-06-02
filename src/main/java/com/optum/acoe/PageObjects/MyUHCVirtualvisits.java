package com.optum.acoe.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.acoe.WebFunctionLib.WebFunctionLib;

public class MyUHCVirtualvisits {
	
	WebFunctionLib web = new WebFunctionLib();
	
	public MyUHCVirtualvisits() {
		PageFactory.initElements(WebFunctionLib.driver, this);
	}
	
	@FindBy(xpath = "//DIV[2]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/TABLE[1]/TBODY[1]/TR[1]/TD[3]")
	WebElement ondemandestimatecost;
	
	@FindBy(xpath = "//DIV[2]/DIV[1]/DIV[2]/DIV[1]/DIV[2]/TABLE[1]/TBODY[1]/TR[1]/TD[3]")
	WebElement amwellestimatecost;
	
	public void displayCosts() {
		System.out.println("On demand Estimated cost: "+web.getText(ondemandestimatecost));
		System.out.println("Amwell Estimated cost: "+web.getText(ondemandestimatecost));
	}

}
