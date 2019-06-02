package com.optum.acoe.PageObjects.SentinelApplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.optum.acoe.WebFunctionLib.WebFunctionLib;

public class SentinelWhiteBoard extends WebFunctionLib{

	public static void enterFilter(String filter) throws InterruptedException {
		
		WebElement searchfield = driver.findElement(By.id("search-filter"));
		searchfield.sendKeys(filter);
		WebElement searchbtn = driver.findElement(By.id("whiteboard-search"));
		searchbtn.click();
		Thread.sleep(4000);
	}
	
	public static Integer numberOfRecords() {
		String data = driver.findElement(By.xpath("//DIV[2]/DIV[1]/DIV[1]/UL[@role=\"tablist\"][1]/LI[2]")).getText();
		
		data = data.split(" ")[1];
		
		data = data.substring(1, data.length()-1);
//		data.replaceAll("(", "");
//		data.replaceAll(")", "");
		System.out.println("data:" + data);
		System.out.println("length" + data.length());
		int a = Integer.parseInt(data);
		return a;
	}

	public static void clickResetBtn() {
		// TODO Auto-generated method stub
		WebElement resetbtn = driver.findElement(By.id("whiteboard-reset"));
		resetbtn.click();
	}
}
