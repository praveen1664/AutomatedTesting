package com.optum.acoe.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.acoe.WebFunctionLib.WebFunctionLib;

public class MyUHCSecurity {

	WebFunctionLib web = new WebFunctionLib();
	
	public MyUHCSecurity() {
		PageFactory.initElements(WebFunctionLib.driver, this);
	}
	
	@FindBy(id = "authQuestiontextLabelId")
	WebElement question;
	
	@FindBy(id = "challengeQuestionList[0].userAnswer")
	WebElement answerField;
	
	@FindBy(xpath = "//INPUT[@id='continueSubmitButton']")
	WebElement continuebtn;
	
	public void answerQuestion() {
		String que = web.getText(question);
		if (que.contains("job")) {
			web.enterText(answerField, "Optum");
		} else if (que.contains("city")) {
			web.enterText(answerField, "Orlando Florida");
		} else {
			web.enterText(answerField, "Orange");
		}

		web.Click(continuebtn);
	}
}
