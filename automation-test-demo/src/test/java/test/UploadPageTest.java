package test;

import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import model.Information;
import pageobject.UploadFilePage;
import utils.DebugModule;
import utils.Log;
import utils.TestBase;

public class UploadPageTest extends TestBase{
	private DebugModule debug = new DebugModule();
	
	@Test (priority = 0, dataProvider = "UploadFileInput")
	public void uploadDemoTest(Information info) throws InterruptedException {
		Log.startTestCase((new Object() {
		}).getClass().getEnclosingMethod().getName());
		debug.setDebugParameters(baseDir, (new Object() {
		}).getClass().getEnclosingMethod().getName());
		
		UploadFilePage page = new UploadFilePage(driver);
		page.uploadInfo(info);
		page.verifyUploadInformation(info);
	}
  //================================================================
	@DataProvider(name = "UploadFileInput")
	private Object[][] create() {
		List<Information> lstLcInput = getListInformation();
		Object[][] objArray = new Object[lstLcInput.size()][0];
		for (int i = 0; i < objArray.length; i++) {
			objArray[i] = new Object[1];
			objArray[i][0] = lstLcInput.get(i);
		}
		return objArray;
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void BeforeMethod(String browser) {
		initSeleniumTest(browser);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult iResult) {
		debug.printScreen(driver, iResult);
		Log.info("Close browser");
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
