package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.PressSequentiallyOptions;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.options.SelectOption;


import extentlisteners.ExtentListeners;

public class BasePage {

	public static Page page;
	
	public static Properties OR = new Properties();
	private static FileInputStream fis;
//	public static CarBase carBase;
	public BasePage(Page page) {
		
		this.page=page;
//		carBase = new CarBase(page);
		
		try {
			fis = new FileInputStream("./src/main/resources/properties/OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void click(String locatorKey) {

		try {
			page.locator(OR.getProperty(locatorKey)).click();
			ExtentListeners.getExtent().info("Clicking on an Element : " + locatorKey);
		} catch (Throwable t) {

			ExtentListeners.getExtent()
					.fail("Clicking on an Element : " + locatorKey + " error message is :" + t.getMessage());
			Assert.fail(t.getMessage());
		}
	}
	
	
	public void mouseHover(String locatorKey) {

		try {
			page.hover(OR.getProperty(locatorKey));
			ExtentListeners.getExtent().info("Moving on an Element : " + locatorKey);
		} catch (Throwable t) {

			ExtentListeners.getExtent()
					.fail("Error while Moving on an Element : " + locatorKey + " error message is :" + t.getMessage());
			Assert.fail(t.getMessage());
		}
	}

	public boolean isElementPresent(String locatorKey) {

		try {
			page.waitForSelector(OR.getProperty(locatorKey), new WaitForSelectorOptions().setTimeout(30000));

			ExtentListeners.getExtent().info("Finding an Element : " + locatorKey);
			return true;
		} catch (Throwable t) {

			ExtentListeners.getExtent().fail("Error while finding an Element : " + locatorKey);

			return false;
		}

	}

	public void type(String locatorKey, String value) {
		try {
			page.locator(OR.getProperty(locatorKey)).fill(value);
			ExtentListeners.getExtent()
					.info("Typing in an Element : " + locatorKey + " and entered the value as :" + value);
		} catch (Throwable t) {

			ExtentListeners.getExtent().fail(
					"Error while typing in an Element : " + t.getMessage() + " error message is :" + t.getMessage());
			Assert.fail(t.getMessage());
		}
	}

	
	public void select(String locatorKey, String value) {
		try {
			page.selectOption(OR.getProperty(locatorKey),new SelectOption().setLabel(value));
			ExtentListeners.getExtent()
					.info("Selecting an Element : " + locatorKey + " and selected the value as :" + value);
		} catch (Throwable t) {

			ExtentListeners.getExtent().fail(
					"Error while Selecting an Element : " + t.getMessage() + " error message is :" + t.getMessage());
			Assert.fail(t.getMessage());
		}
	}
	
	public void input(String locatorKey, String value) {
	    try {
	      
	        page.locator(OR.getProperty(locatorKey)).pressSequentially(value, new PressSequentiallyOptions().setDelay(100));

	        ExtentListeners.getExtent().info("Typing into an Element : " + locatorKey + " and typed the value as :" + value);

	    } catch (Throwable t) {

	        ExtentListeners.getExtent().fail(
	                "Error while Inputting into an Element : " + t.getMessage() + " error message is :" + t.getMessage());
	        Assert.fail(t.getMessage());
	    }
	}

	public void scrollToElement(String locatorKey) {
	    try {

	        Locator element = page.locator(OR.getProperty(locatorKey));
	        element.scrollIntoViewIfNeeded();
	        
	        ExtentListeners.getExtent().info("Scrolled to Element : " + locatorKey);

	        // Additional check to ensure the element is within viewport
	        Boolean isInViewport = (Boolean) element.evaluate("el => {"
	                + "const rect = el.getBoundingClientRect();"
	                + "return (rect.top >= 0 && rect.left >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && rect.right <= (window.innerWidth || document.documentElement.clientWidth));"
	                + "}");

	        if (!isInViewport) {
	            throw new Exception("Element not in viewport after scroll");
	        }

	    } catch (Throwable t) {

	        ExtentListeners.getExtent().fail(
	                "Error while scrolling to Element : " + t.getMessage() + " error message is :" + t.getMessage());
	        Assert.fail(t.getMessage());
	    }
	}

	

	
	
}
