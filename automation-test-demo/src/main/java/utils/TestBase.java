package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import model.Information;

public class TestBase {
	public Properties config;
	public String baseUrl;
	public WebDriver driver;
	public String objectRepoFile;
	public String dataInputFile;
	public String baseDir;
	public int timeoutSecond;
	
	public TestBase() {
		config = Utils.loadConfig("config.properties");
		baseUrl = config.getProperty("APP_BASE_URL");
		objectRepoFile = config.getProperty("LINEAR_CHANNEL_PAGE_FILE");
		dataInputFile = config.getProperty("DATA_INPUT_FILE");
		timeoutSecond = Integer.parseInt(config.getProperty("TESTBASE_DEFAULT_TIMEOUT_SECOND"));
		baseDir  = System.getProperty("user.dir");
	}
	
	public void initSeleniumTest(String browser) {
//		String browser = System.getProperty("browser");
		Log.info("Browser: " + browser);
		if ("chrome".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.chrome.driver", config.getProperty("BROWSER_DRIVER_CHROME"));
			driver = new ChromeDriver();
		} else if ("ie".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.ie.driver", config.getProperty("BROWSER_DRIVER_IE"));
			driver = new InternetExplorerDriver();
		} else {
			driver = new FirefoxDriver();
		}
		
		driver.get(baseUrl);

		driver.manage().window().maximize();
	}
	
	public List<Information> getListInformation() {
		String[][] dataInput = Utils.getTableArray(dataInputFile, Constants.SHEET_UPLOAD_FILE, Constants.TABLE_DATA_UPLOAD);
		List<Information> lstInput = new ArrayList<>();
		if (dataInput != null) {
			for (int i = 0; i < dataInput.length; i++) {
				Information input = new Information();
				input.setFileUpload(dataInput[i][0]);
				input.setUploadTo(dataInput[i][1]);
				input.setSubFolderName(dataInput[i][2]);
				input.setEmail(dataInput[i][3]);
				input.setExpected(dataInput[i][4]);
				lstInput.add(input);
			}
		}
		return lstInput;
	}
}
