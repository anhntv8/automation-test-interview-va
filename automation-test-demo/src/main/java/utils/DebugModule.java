package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class DebugModule extends TestBase {
	private String path;
	private String caseName;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public void setDebugParameters(String path, String caseName) {
		this.setPath(path + "/screenshoot/");
		this.setCaseName(caseName);
	}

	public void printScreen(int number) {
		try {
			String finalPath = this.getPath() + this.getCaseName() + number + ".jpg";
			Action.printScreen(driver, finalPath);
		} catch (Exception e) {
			System.out.println("There Is An Error When Capturing Screen");
		}

	}

	public void printScreen(WebDriver driver, ITestResult result) {
//		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				String finalPath = this.getPath() + this.getCaseName() + ".jpg";
				Action.printScreen(driver, finalPath);
			}
//		} catch (Exception e) {
//			Log.error(e.getMessage());
////			System.out.println("There Is An Error When Capturing Screen");
//		}
	}

}
