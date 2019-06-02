package com.optum.acoe.WebFunctionLib;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.JavascriptExecutor;

import com.google.common.io.Files;
import com.optum.acoe.Utils.PropertyReader;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class WebFunctionLib {
	
	private static String ff_driverpath;
	private static String ie_driverpath;
	private static String cr_driverpath;
	public static WebDriver driver = null;
	public static String mainWindow = null;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");;
	Logger log;
	PropertyReader property;
	
	public WebFunctionLib(String srcPath)
	{
		ff_driverpath = srcPath + File.separator + "geckodriver.exe";
		ie_driverpath = srcPath + File.separator  +"IEDriverServer.exe";
		cr_driverpath = srcPath + File.separator  + "chromedriver.exe";
		property = new PropertyReader();
		log = Logger.getLogger(WebFunctionLib.class);
		
	}
	
	public WebFunctionLib()
	{
		String srcPath = System.getProperty("user.dir") + File.separator + "Driver";
		ff_driverpath = srcPath + File.separator + "geckodriver.exe";
		ie_driverpath = srcPath + File.separator  +"IEDriverServer.exe";
		cr_driverpath = srcPath + File.separator  + "chromedriver.exe";
		property = new PropertyReader();
		log = Logger.getLogger(WebFunctionLib.class);
		
	}
	
	
	
	
	/**
	 * Launches the browser specified in "config.properties" file.
	 * @return boolean
	 */

	@SuppressWarnings("deprecation")
	public boolean launchBrowser() {
		String browser = property.readProperty("browser").replace(" ", "");
		try {
		if(browser.equalsIgnoreCase("internetexplorer")) {
//			WebDriverManager.iedriver().version("2.51").setup();
			System.setProperty("webdriver.ie.driver", ie_driverpath);
			DesiredCapabilities desiredcapabilities = new DesiredCapabilities();
			desiredcapabilities.setCapability("ignoreProtectedModeSettings", true);
			desiredcapabilities.setCapability("acceptSslCerts", true);
			desiredcapabilities.setJavascriptEnabled(true);
//			desiredcapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
//			desiredcapabilities.setCapability("enablePersistentHover", true);
//			desiredcapabilities.setCapability("nativeEvents", true);
//			desiredcapabilities.setCapability("enablePersistentHover", true);
//			desiredcapabilities.setCapability("requireWindowFocus", true);
//			desiredcapabilities.setCapability("unexpectedAlertBehaviour", true);
			driver = new InternetExplorerDriver(desiredcapabilities);
			log.info("opening internet explorer browser");
			driver.manage().window().maximize();
		}
		
		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ff_driverpath);
//			WebDriverManager.firefoxdriver().version("0.18.0").setup();
			driver = new FirefoxDriver();
			log.info("opening Firefox browser");
			driver.manage().window().maximize();
		}		
		
		else if (browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", cr_driverpath);
//			WebDriverManager.chromedriver().version("2.40").setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.setExperimentalOption("useAutomationExtension",false);
			driver = new ChromeDriver(options);
			log.info("opening Chrome browser");
			driver.manage().window().maximize();
		}
		
		else {
			throw new IllegalArgumentException("Invalid browser name in config.properties file, please check the \"browser\" argument for any mispells or typos.");
			}
		}
		catch(IllegalArgumentException e) {
			log.error(e);
			System.out.println(e.getMessage());
			throw e;
		}
		
		catch(WebDriverException  e) {
			System.out.println("Error opening browser");
			log.error("Error opening browser");
			log.error(e);
			e.printStackTrace();
			throw e;
		}
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return true;
	}
	
	/**
	 *  Navigates to the URL specified with "applicationurl" in "config.properties" file.
	 * 
	 */
	
	public void navigateToURL(String key) {
		
		String url = property.readProperty(key);
		try {
			System.out.println("opening url:" + url);
			driver.get(url);
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
		log.info("opened URL: " + url);
		
	}
	
	/**
	 * This method is used to close the browser or page currently which is having the focus.
	 */
	
	public void closeBrowser() {
		try {
				driver.close();
				log.info("Closing driver");
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * This is used to shut down the web driver instance or destroy the web driver instance.
	 */
	
	public void quitBrowser() {
		try {
			driver.quit();
			log.info("Closing browser");
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Use this method to send Keys into an element, which may set its value.
	 * @param element
	 * @param keysToSend
	 */
	public void enterText(WebElement element, CharSequence... keysToSend) {
		
		try {
			element.sendKeys(keysToSend);
			log.info("sending key \"" + keysToSend + "\" to element "+element.toString());
			}
			catch(Exception e) {
				log.error(e);
				throw e;
			}		
	}
	
	/**
	 * Returns the boolean value true if webelement is enabled.
	 * @param element
	 * @return boolean true if webelement is enabled.
	 */
	public boolean isEnabled(WebElement element) {
		
		try {
			return element.isEnabled();
			}
			catch(Exception e) {
				log.error(e);
				throw e;
			}
	}
	
	/**
	 * Clicks WebElement.
	 * @param webelement
	 */
	public void Click(WebElement webelement) {
		try {
		webelement.click();
		log.info("Clicking on element: " + webelement.toString());
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * If the element is a text entry element, this will clear the value.
	 * @param webelement
	 */
	
	public void Clear(WebElement webelement) {
		try {
			webelement.clear();
			log.info("Clearing WebElement: " + webelement.toString());
			}
			catch(Exception e) {
				log.error(e);
				throw e;
			}
	}
	
	/**
	 *  Gets the value of the given attribute of the element.
	 * @param element
	 * @param attribute
	 * @return
	 */
	
	public String getAttribute(WebElement element, String attribute) {
		try {
			return element.getAttribute(attribute);
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Gets the value of a given CSS property.
	 * @param element
	 * @param propertyName
	 * @return
	 */
	
	public String getCssValue(WebElement element, String propertyName) {
		try {
			return element.getCssValue(propertyName);
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Returns a Point Object, where on the page is the top left-hand corner of the rendered element.
	 * @param element
	 * @return
	 */
	
	public Point getLocation(WebElement element) {
		try {
			return element.getLocation();
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 *  Returns the location and size of the rendered element.
	 * @param element
	 * @return Rectangle
	 */
	
	public Rectangle getRect(WebElement element) {
		try {
			return element.getRect();
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Returns the width and height of the rendered element
	 * @param element
	 * @return Dimension
	 */
	
	public Dimension getSize(WebElement element) {
		try {
			return element.getSize();
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
		
	}
	
	/**
	 * Gets the tag name of the element.
	 * @param element
	 * @return String - tagname
	 */
	
	public String getTagName(WebElement element) {
		try {
			return element.getTagName();
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Gets the visible text of the given webelement.
	 * @param element
	 * @return String - visible text
	 */
	
	public String getText(WebElement element) {
		try {
			return element.getText();
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
	}
	
	/**
	 * Returns whether or not the element is displayed
	 * @param element
	 * @return boolean - true if element element is displayed else false.
	 */
	
	public boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}	
	}
	
	/**
	 *  Returns whether or not the element is selected. 
	 * @param element
	 * @return boolean - Returns true if the element is currently selected or checked, false otherwise.
	 */
	
	public boolean isSelected(WebElement element) {
		try {
			return element.isSelected();
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}	
	}
	
	/**
	 * If the element is a form, or an element within a form, then this will be submitted to the remote server.
	 * @param element
	 */
	
	public void submit(WebElement element) {
			try {
				element.submit();
			}
			catch(Exception e) {
				log.error(e);
				throw e;
			}
	}
	
	/**
	 * Verifies whether WebElement object exists, is enabled and is displayed on screen.
	 * @param element
	 * @return boolean
	 */

    public boolean Verify_VisibleEnabled(WebElement element) {
        try {

            if (!(element.equals(null))
                    || (element.isEnabled() && element.isDisplayed())) {
                System.out.println("Verify_VisibleEnabled: Element exist");
                log.info("Verify_VisibleEnabled: WebElement: "+ element +" exist's");
                return true;
            } else {
                System.out.println("Verify_VisibleEnabled: Element does NOT exist");
                log.info("Verify_VisibleEnabled: WebElement: "+ element +" does not exist");
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Verify_VisibleEnabled: NoSuchElementException thrown");
            log.error("verify_VisibleEnabled: NoSuchElementExpection thrown for WebElement: " + element);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("verify_VisibleEnabled: Expection thrown for WebElement: " + element);
            log.error(e);
            return false;
        }
    }
    
   
    /**
     * To compare Expected and Actual value of an attribute of a webelement.
     * @param element
     * @param Expected_Value
     * @param property
     * @return : boolean
     */
   
    public boolean Verify_AttributeValue_Element(WebElement element, String Expected_Value,	String property) {
    	try {
	        String Actual_Value = element.getAttribute(property);
	        if (Actual_Value.equals(Expected_Value)) {
	            System.out.println("Verify_Value_Element: String value is Matching");
	            log.info("Verify_Value_Element: String value is Matching for webelement: " + element);
	            return true;
	        } else {
	            System.out.println("Verify_Value_Element: String value NOT Matching!");
	            log.warn("Verify_Value_Element: String value NOT Matching! for webelement: " + element);
	            return false;
	        }
    	}
    	catch(Exception e) {
    		log.info(e);
    	}
		return false;
    }
    
    /**
     * Takes screenshot and stores in a file at location given.
     * @return Byte[]
     */
    public static byte[] takeScreeShotOfByte() {
    	String path = null;
    	try {
    		if(driver != null) {
    			path = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + dateFormat.format(new Date()) + "_picture.png";
		    	byte[] screenshotfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		    	return screenshotfile;
    		}
    	}
    	catch(Exception e) {
    		System.out.println("Error while taking screenshot and storing to file at location: " + path);
    		
    	}
		return null;
    }
    
    /**
     * Takes screenshot and returns the location where file is located.
     * @return File
     */
    public static String takeScreeShot() {

    	String path = null;
    	try {
    		if(driver != null) {
    			path = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + dateFormat.format(new Date()) + "_picture.png";
		    	File screenshotfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    	Files.copy(screenshotfile, new File(path));
    		}
    	}
    	catch(Exception e) {
    		System.out.println("Error while taking Screenshot");
    		
    	}
		return path;
    }
    
    /**
     * Clicks on ok/submit button of alert box.
     */
    public void acceptAlert() {
    	
    	try {
    		driver.switchTo().alert().accept();
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }
    
    /**
     * Clicks on cancel button of alert box.
     */
    public void rejectAlert() {
    	try {
    		driver.switchTo().alert().dismiss();
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }
    
    /**
     * Gets the message displayed on alert box.
     * @return String message from alert box.
     */
    public String getAlertMessage() {
    	try {
    		return driver.switchTo().alert().getText();
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}	
    }
    
    /**
     * Sends the text to alert box.
     * @param message
     */
    public void sendTextToAlert(String message) {
    	try {
    		driver.switchTo().alert().sendKeys(message);
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}	
    }
    
    /**
     * Shifts the control of driver form current window to latest child window.
     * @return String - window name/ window handle of the parent window
     */
    public String shiftControlToPopUpWindow () {
    	try {
    		mainWindow = driver.getWindowHandle();
    		Set<String> windowset = driver.getWindowHandles();
    		String windowname = null;
    		Iterator<String> windowiter = windowset.iterator();
    		while(windowiter.hasNext())
    			windowname = windowiter.next();
    		driver.switchTo().window(windowname);
    		
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    	return mainWindow;
    }
    
    /**
     * Shifts the control of driver form current window to given child window.
     */
    public void shiftControlToPopUpWindow (String windowname) {
    	try {
    		mainWindow = driver.getWindowHandle();
    		driver.switchTo().window(windowname);
    		
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }
    
    /**
     * Shifts the driver control to main window.
     */
    public void shiftControlToMainWindow(String windowname) {
    	try {
    		if(windowname == null)
    			driver.switchTo().window(mainWindow);
    		else
    			driver.switchTo().window(windowname);
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }
    
    /**
     * Shifts the driver control to another frame with given frame index.
     * @param index
     */
    public void ShiftControlToFrameByIndex(int index) {
    	try {
    		
    		driver.switchTo().frame(index);
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }
    
    /**
     * Shifts the driver control to another frame with given frame name.
     * @param frameName
     */
    public void ShiftControlToFrameByName(String frameName) {
    	try {
    		
    		driver.switchTo().frame(frameName);
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }

    /**
     * Shifts the driver control to another frame where hte given webelement exists.
     * @param webElement
     */
    public void ShiftControlToFrameByElement(WebElement element) {
    	try {
    		
    		driver.switchTo().frame(element);
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }
    
    /**
     * Shifts the driver control to the parent frame of current frame.
     */
    public void ShiftControlToParentFrame() {
    	try {
    		
    		driver.switchTo().parentFrame();
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }
    
    /**
     * Shifts the driver control to the outermost frame.
     */
    public void ShiftControlToDefaultFrame() {
    	try {
    		
    		driver.switchTo().defaultContent();
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }
    
    /**
     * Executes the java script.
     * @param script
     */
    public void executeJavaScript(String script) {
    	
    	try {
    		((JavascriptExecutor)driver).executeScript(script);
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    }
    
    /**
     * Returns the number of rows for the table.
     * @param table
     * @return Integer - number of rows in a table.
     */
    public int getTableRowNumber(WebElement table) {
    	int numrow = 0;
    	try{
    		java.util.List<WebElement> rows = table.findElements(By.tagName("tr"));
    		numrow = rows.size();
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    	return numrow;
    }
    
    /**
     * Returns the number columns for the first data row of table.
     * @param table
     * @return integer - number columns for the first data row of table.
     */
    public int getTableColNumber(WebElement table) {
    	int numcol = 0;
    	try{
    	WebElement row = (table.findElements(By.tagName("tr"))).get(1);
    	java.util.List<WebElement> columns = row.findElements(By.tagName("td"));
    		numcol = columns.size();
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    	return numcol;
    }
    
    /**
     * Returns the number columns for the specified data row of table.
     * @param table
     * @param index
     * @return integer - number columns for the specified data row of table.
     */
    public int getTableColNumber(WebElement table, int index) {
    	int numcol = 0;
    	try{
    	WebElement row = (table.findElements(By.tagName("tr"))).get(index);
    	java.util.List<WebElement> columns = row.findElements(By.tagName("td"));
    		numcol = columns.size();
    	}
    	catch(Exception e) {
    		log.error(e);
    		throw e;
    	}
    	return numcol;
    }
    
    /**
     * Returns the visible string of the given cell with row and column index.
     * @param table
     * @param rowindex
     * @param columnindex
     * @return
     */
    public String readCell(WebElement table, int rowindex, int columnindex) {
    	
    	String data = null;
    	try {
    		WebElement cell = (((table.findElements(By.tagName("tr"))).get(rowindex)).findElements(By.tagName("td"))).get(columnindex);
    		data = getText(cell);
    	}
    	catch (Exception e) {
    		log.error(e);
    		throw e;
    	}
    	return data;
    }
    
    /**
     * Returns the web element of the given cell with row and column index.
     * @param table
     * @param rowindex
     * @param columnindex
     * @return WebElement - the webelement present the cell with given row and column  index.
     */
    public WebElement getCell(WebElement table, int rowindex, int columnindex) {
    	
    	WebElement cell;
    	try {
    		cell = (((table.findElements(By.tagName("tr"))).get(rowindex)).findElements(By.tagName("td"))).get(columnindex);
    	}
    	catch (Exception e) {
    		log.error(e);
    		throw e;
    	}
    	return cell;
    }
}
