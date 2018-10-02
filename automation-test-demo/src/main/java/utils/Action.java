package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Action {
	public static void sendKeys(WebDriver driver, By locator, String value) {
		if (value != null) {
			WebElement element = findElement(driver, locator);
			element.click();
			element.clear();
			element.sendKeys(value);
			Log.info("Send " + value + " to element at " + locator.toString());
		}
	}
	
	public static void click(WebDriver driver, By elementLocator) {
		Log.info("Click element at: " + elementLocator.toString());
		WebElement el = findElement(driver, elementLocator);
		el.click();
	}
	
	public static boolean isDisplayed(WebDriver driver, By locator) {
		boolean display = findElement(driver, locator).isDisplayed();
		Log.info("Element at " + locator.toString() + " FOUND");
		return display;
	}
	
	public static String getText(WebDriver driver, By elementLocator) {
		String text = findElement(driver, elementLocator).getText();
		Log.info("Get text of element at " + elementLocator.toString());
		return text;
	}
	
	public static String getAttribute(WebDriver driver, By elementLocator, String attribute) {
		String text = findElement(driver, elementLocator).getAttribute(attribute);
		Log.info("Get text of element at " + elementLocator.toString());
		return text;
	}
	
	public static void printScreen(WebDriver driver, String path) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static WebElement findElement(WebDriver driver, By locator) {
		try {
			WebElement element = (new WebDriverWait(driver, 30))
					   .until(ExpectedConditions.visibilityOfElementLocated(locator));
			return element;
		} catch (ElementNotVisibleException e) {
			Log.debug("Element at" + locator.toString() + " NOT FOUND");
			return null;
		}
	}
	
	public static void uploadFile(WebDriver driver, By locator, String path) throws InterruptedException {
		try {
			File f = new File(path);
			if (f.isFile()) {
				
				click(driver, locator);
				Thread.sleep(500);

				StringSelection filePath = new StringSelection(f.getAbsolutePath());
				Log.info("File Selection: " + f.getAbsolutePath());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);

				r.keyRelease(KeyEvent.VK_A);
				r.keyRelease(KeyEvent.VK_CONTROL);

				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);

				r.keyRelease(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);

				Thread.sleep(1000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
			}
			Thread.sleep(5000);
		} catch (AWTException e) {
			System.console().writer().write("Unable to upload file");
		}
	}
	
	public static void selectByVisibleText(WebDriver driver, By locator, String visibleText) {
		WebElement element = findElement(driver, locator);
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
}
