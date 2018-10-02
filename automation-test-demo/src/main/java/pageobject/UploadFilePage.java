package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import model.Information;
import utils.Action;
import utils.Constants;
import utils.TestBase;
import utils.Utils;

public class UploadFilePage extends TestBase{
	public By locBtnUploadFile;
	public By locSelectUploadTo;
	public By locTxtSubFolderName;
	public By locTxtEmail;
	public By locTxtFirstName;
	public By locBtnBeginUpload;
	public By locUploadSummary;
	public By locSummaryEmail;
	public By locSummaryFirstName;
	public By locSummaryFileName;
	
	public UploadFilePage(WebDriver driver) {
		this.driver = driver;
		initRepo();
	}
	
	/**
	 * Get data from object repository
	 * 
	 */
	public void initRepo() {
		String[][] loginObj = Utils.getTableObject(objectRepoFile, "Upload_File_Page");
		
		for (int i = 0, numOfRow = loginObj.length; i < numOfRow; i++) {
			switch (loginObj[i][0]) {
			case "BTN_UPLOAD_FILE":
				locBtnUploadFile = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			case "SELECT_UPLOAD_TO":
				locSelectUploadTo = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			case "TXT_SUB_FOLDER_NAME":
				locTxtSubFolderName = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			case "TXT_EMAIL_ADDRESS":
				locTxtEmail = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			case "TXT_FIRST_NAME":
				locTxtFirstName = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			case "BTN_BEGIN_UPLOAD":
				locBtnBeginUpload = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			case "UPLOAD_SUMMARY":
				locUploadSummary = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			case "SUMMARY_EMAIL":
				locSummaryEmail = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			case "SUMMARY_FIRSTNAME":
				locSummaryFirstName = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			case "SUMMARY_FILENAME":
				locSummaryFileName = Utils.getLocation(loginObj[i][1], loginObj[i][2]);
				break;
			}
		}
	}

	public void uploadInfo(Information info) throws InterruptedException {
		// 1. Choose file  to upload
		Action.uploadFile(driver, locBtnUploadFile, info.getFileUpload());
		// 2. Select dropdown upload to
		Action.selectByVisibleText(driver, locSelectUploadTo, info.getUploadTo());
		// 3. Input subfolder
		String subfolderText = info.getSubFolderName();
		if (subfolderText.equalsIgnoreCase("random")) {
			String name = Utils.randomString();
			Action.sendKeys(driver, locTxtSubFolderName, name);
		}
		
		// 4. Input email, first name
		Action.sendKeys(driver, locTxtEmail, info.getEmail());
		Action.sendKeys(driver, locTxtFirstName, info.getFirstName());
		
		// 5. Click Begin Upload
		Action.click(driver, locBtnBeginUpload);
	}
	
	public void verifyUploadInformation(Information info) {
		String expected = info.getExpected();
		if (!expected.equals("") && expected.equalsIgnoreCase(Constants.SUCCESSFULLY)) {
			String emailActual = Action.getText(driver, locSummaryEmail);
			Assert.assertEquals(emailActual, "Email Address: " + info.getEmail());
			String firstNameActual = Action.getText(driver, locTxtFirstName);
			Assert.assertEquals(firstNameActual, "First Name: " + info.getFirstName());
			String fileNameActual = Action.getAttribute(driver, locSummaryFileName, Constants.ATTR_VALUE);
			Assert.assertEquals(fileNameActual, info.getFileUpload());
		}
	}
}
