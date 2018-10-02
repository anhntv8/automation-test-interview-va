package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Utils {
	
	public static String randomString() {
		Random random = new Random();
		String strRandom = "nashtech" + random.nextInt(5);
		return strRandom;
	}
	
	public static Properties loadConfig(String fileName) {
		Properties configProperties = null;

		FileInputStream in;
		try {
			in = new FileInputStream(fileName);
			configProperties = new Properties();
			configProperties.load(in);
			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return configProperties;
	}
	
	public static String[][] getTableObject(String xlFilePath, String sheetName) {
		String[][] tabArray = null;
		int ci, cj;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			tabArray = new String[sheet.getRows() - 1][3];
			ci = 0;

			for (int i = 1; i < sheet.getRows(); i++, ci++) {
				cj = 0;
				for (int j = 0; j < 3; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (tabArray);
	}
	
	public static String[][] getTableArray(String xlFilePath, String sheetName, String tableName) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			int startRow, startCol, endRow, endCol, ci, cj;
			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();

			System.out.println("Loaded data from Excel: Row[" + startRow + ".." + endRow + "], Columns[" + startCol + ".." + endCol
					+ "]");

			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;

			for (int i = startRow + 1; i < endRow; i++, ci++) {
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (tabArray);
	}
	
	public static By getLocation(String ex, String typeEx) {
		By result;
		switch (typeEx) {
			case "id":
				result = By.id(ex);
				break;
			case "xpath":
				result = By.xpath(ex);
				break;
			case "linkText":
				result = By.linkText(ex);
				break;
			case "className":
				result = By.className(ex);
				break;
			case "cssSelector":
				result = By.cssSelector(ex);
				break;
			case "name":
				result = By.name(ex);
				break;
			case "partialLinkText":
				result = By.partialLinkText(ex);
				break;
			case "tagName":
				result = By.tagName(ex);
				break;
			default:
				result = null;
		}
		return result;
	}
}
