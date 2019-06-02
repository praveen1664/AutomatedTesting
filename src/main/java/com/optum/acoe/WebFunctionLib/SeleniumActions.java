package com.optum.acoe.WebFunctionLib;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumActions {
	
	Actions action;
	Logger log;
	
	public SeleniumActions() {
		action = new Actions(WebFunctionLib.driver);
		log = Logger.getLogger(SeleniumActions.class);
	}
	
	/**
	 * Clicks at current mouse location.
	 */
	public void click() {
		try {
		action.click().perform();
		log.info("clicked the mouse at current location");
		}
		catch(Exception e) {
			log.error("error while clicking mouse at current location");
			log.error(e);
			throw e;
			
		}
	}
	
	/**
	 * Clicks in the middle of given WebElement.
	 * @param target
	 */
	public void click(WebElement target) {
		try {
		action.click(target).perform();
		log.info("clicked the mouse on webelement:" + target.toString());
		}
		catch(Exception e) {
			log.error("error while clicking mouse on webelement:" + target.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Clicks (without releasing) in the middle of the given element.
	 * @param target
	 */
	public void clickAndHold(WebElement target) {
		try {
		action.clickAndHold(target).perform();
		log.info("clicked and holding the mouse on webelement:" + target.toString());
		}
		catch(Exception e) {
			log.error("error while performing click and hold operation on webelement:" + target.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Performs a double-click at the current mouse location.
	 */
	public void doubleClick() {
		try {
		action.doubleClick().perform();
		log.info("double clicked the mouse at current location");
		}
		catch(Exception e) {
			log.error("error while double-clicking mouse at current location");
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Performs a double-click at middle of the given element.
	 * @param target
	 */
	public void doubleClick(WebElement target) {
		try {
		action.doubleClick(target).perform();
		log.info("double-clicked the mouse on webelement:" + target.toString());
		}
		catch(Exception e) {
			log.error("error while performing double-click operation on webelement:" + target.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Performs a right-click at the current mouse location.
	 */
	public void contextClick() {
		try {
			action.contextClick().perform();
			log.info("context-clicked at current mouse position");
		}
		catch(Exception e) {
			log.info("error while performing context-click at current mouse position");
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Performs a right-click at the middle of given element.
	 * @param element
	 */
	public void contextClick(WebElement element) {
		try {
			action.contextClick(element).perform();
			log.info("context-clicked on webelement: " + element.toString());
		}
		catch(Exception e) {
			log.info("error while performing context-click on webelement: " + element.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * performs click-and-hold at the location of the source element, moves to the location of the target element, then releases the mouse.
	 * @param source
	 * @param target
	 */
	public void dragAndDrop(WebElement source,WebElement target) {
		try {
			action.dragAndDrop(source, target).perform();
			log.info("dragging the source element:" + source);
		}
		catch(Exception e) {
			log.info("error while performing context-click on webelement: " + source.toString() + " and dropping on webelement: " + target.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 *  performs click-and-hold at the location of the source element, moves by a given offset, then releases the mouse.
	 * @param source
	 * @param xoffset
	 * @param yoffset
	 */
	public void dragAndDrop(WebElement source,int xoffset, int yoffset) {
		try {
			action.dragAndDropBy(source, xoffset, yoffset).perform();
			log.info("dragging the element:" + source.toString() + " with x, y offset: " + xoffset +"," + yoffset + " respectively");
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 *  Performs a modifier key press.
	 * @param key
	 */
	public void KeyDown(CharSequence key) {
		try {
			action.keyDown(key).perform();
			log.info("pressing a modifier key: " + key.toString());
		}
		catch(Exception e) {
			log.error("error while pressing a modifier key: " + key.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Performs a modifier key press after focusing on an element.
	 * @param element
	 * @param key
	 */
	public void KeyDown(WebElement element,CharSequence key) {
		try {
			action.keyDown(element, key).perform();
			log.info("pressing a modifier key: " + key.toString() + " on webelement: " + element.toString());
		}
		catch(Exception e) {
			log.error("error while pressing a modifier key: " + key.toString() + " on webelement: " + element.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Performs a modifier key press.
	 * @param key
	 */
	public void KeyUp(CharSequence key) {
		try {
			action.keyUp(key).perform();
			log.info("releasing a modifier key: " + key.toString());
		}
		catch(Exception e) {
			log.error("error while releasing a modifier key: " + key.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Performs a modifier key press after focusing on an element.
	 * @param element
	 * @param key
	 */
	public void KeyUp(WebElement element, CharSequence key) {
		try {
			action.keyUp(element, key).perform();
			log.info("releading a modifier key: " + key.toString() + " on webelement: " + element.toString());
		}
		catch(Exception e) {
			log.error("error while releasing a modifier key: " + key.toString() + " on webelement: " + element.toString());
			log.error(e);
		}
	}
	
	/**
	 * Performs a pause.
	 * @param duration
	 */
	public void pause(Duration duration) {
		try {
			action.pause(duration).perform();
			log.info("performing pause action for duration:" + duration.toString());
		}
		catch(Exception e) {
			log.error("error while performing pause action for duration: " + duration.toString());
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Sends keys to the active element.
	 * @param key
	 */
	public void SendKeys(CharSequence... key) {
		try {
			action.sendKeys(key).perform();
			log.info("sending key: " + key.toString() + " to active element");
		}
		catch(Exception e) {
			log.error("error while sending key: " + key.toString() + " to active element");
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Sends keys to the given element.
	 * @param element
	 * @param key
	 */
	public void SendKeys(WebElement element, CharSequence... key) {
		try {
			action.sendKeys(element, key).perform();
			log.info("sending key: " + key.toString() + " to element: " + element.toString());
		}
		catch(Exception e) {
			log.error("error while sending key: " + key.toString() + " to element: " + element.toString());
			log.error(e);
			throw e;
		}
	}
}
