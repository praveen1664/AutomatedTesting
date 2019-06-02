package com.optum.acoe.WebFunctionLib;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumSelect {
	Select sel;
	Logger log;
	public SeleniumSelect(WebElement element) {
		 
		 sel = new Select(element);
		 log = Logger.getLogger(SeleniumSelect.class);
	}
	
	/**
	 * Clear all selected entries.
	 */
	public void deSelectAll() {
		
		try {
				sel.deselectAll();
				log.info("Deselecting all from select webelement: " + sel.toString());
		}
		catch(Exception e) {
			log.error("error while deselecting all from select webelement:" + sel.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Deselect the option at the given index.
	 * @param index
	 */
	public void deselectByIndex(int index) {
		
		try {
			sel.deselectByIndex(index);
			log.info("Deselecting from select webelement: " + sel.toString() + " with index: " + index);
		}
		catch(Exception e) {
			log.error("Error while deselecting from select webelement: " + sel.toString() + " with index: " + index);
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Select the option at the given index.
	 * @param index
	 */
	public void selectByIndex(int index) {
		
		try {
			sel.selectByIndex(index);
			log.info("Selecting from select webelement: " + sel.toString() + " with index: " + index);
		}
		catch(Exception e) {
			log.error("Error while selecting from select webelement: " + sel.toString() + " with index: " + index);
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Select all options that have a value matching the argument.
	 * @param value
	 */
	public void selectByValue(String value) {
			
			try {
				sel.selectByValue(value);
				log.info("Selecting from select webelements: " + sel.toString() + " with value: " + value);
			}
			catch(Exception e) {
				log.error("Error while selecting from select webelements: " + sel.toString() + " with value: " + value);
				log.error(e);
				throw e;
			}
		}
	
	/**
	 * Deselect all options that have a value matching the argument.
	 * @param value
	 */
	public void deselectByValue(String value) {
			
			try {
				sel.deselectByValue(value);
				log.info("Deselecting from select webelements: " + sel.toString() + " with value: " + value);
			}
			catch(Exception e) {
				log.error("Error while deselecting from select webelements: " + sel.toString() + " with value: " + value);
				log.error(e);
				throw e;
			}
		}
	
	/**
	 * Deselect all options that display text matching the argument.
	 * @param text
	 */
	public void deselectByText(String text) {
			
			try {
				sel.deselectByValue(text);
				log.info("Deselecting from select webelements: " + sel.toString() + " with display text: " + text);
			}
			catch(Exception e) {
				log.error("Error while deselecting from select webelements: " + sel.toString() + " with display text: " + text);
				log.error(e);
				throw e;
			}
		}
	
	/**
	 * Select all options that display text matching the argument.
	 * @param text
	 */
	public void selectByText(String text) {
			
			try {
				sel.selectByVisibleText(text);
				log.info("Selecting from select webelements: " + sel.toString() + " with display text: " + text);
			}
			catch(Exception e) {
				log.error("Error while selecting from select webelements: " + sel.toString() + " with display text: " + text);
				log.error(e);
				throw e;
			}
		}
	
	/**
	 * Return first selected option.
	 * @return WebElement - the first selected option.
	 */
	public WebElement getFirstSelectedOption() {
		WebElement element = null;
		try {
			element = sel.getFirstSelectedOption();
			log.info("Getting First selected option for select webelement: " + sel.toString());
		}
		catch(Exception e) {
			log.error("Error while getting first selected option select webelement: " + sel.toString());
			log.error(e);
			throw e;
		}
		return element;
	}
	
	/**
	 * Returns all options belonging to the select tag.
	 * @return List of WebElements.
	 */
	public List<WebElement> getOptions() {
		List<WebElement> element = null;
		try {
			element = sel.getOptions();
			log.info("Getting all options for select webelement: " + sel.toString());
		}
		catch(Exception e) {
			log.error("Error while getting all option select webelement: " + sel.toString());
			log.error(e);
			throw e;
		}
		return element;
	}
	
	/**
	 * Returns whether this select element support selecting multiple options
	 * @return boolean
	 */
	public boolean isMultiple() {
		try {
			return sel.isMultiple();
		}
		catch(Exception e) {
			log.error("Error while checking select element supports slecting multiple options");
			log.error(e);
			throw e;
		}
	}
}
