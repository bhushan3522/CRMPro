package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import TestBasePackage.TestBaseClass;

public class TestUtil extends TestBaseClass{
	
	private static String testDataPath = "F:\\Projects\\crmpro\\src\\main\\java\\ExelData\\TestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	private String fileSeperator = System.getProperty("file.separator");
	private String screenshotFolderpath = System.getProperty("user.dir") +fileSeperator+ "Screenshot";
	private String screenshotFolderDateWise = "Screenshots_" + new SimpleDateFormat("ddMMyyyy").format(new Date());
	private String fileName = new SimpleDateFormat("ddMMyyyyHHmm").format(new Date()); 
	
	public static Object[][] getTestData(String sheetName)

	{
		FileInputStream fis = null;
		try 
		{
			fis = new FileInputStream(testDataPath);
			book = WorkbookFactory.create(fis);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		sheet=book.getSheet(sheetName);
		
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum() +"----------"+ sheet.getRow(0).getLastCellNum());
		
		for(int i=0; i<sheet.getLastRowNum();i++ )
		{
			for(int j=0; j<sheet.getRow(i).getLastCellNum();j++)
			{
				try
				{
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
				catch(NullPointerException e)
				{
					data[i][j]="";
				}
			}
		}
		return data;
	}

	public void screenshot(String name)
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			screenshotFolderpath = createNewDir(screenshotFolderpath);
			screenshotFolderDateWise = createNewDir(screenshotFolderpath+fileSeperator+screenshotFolderDateWise);
			FileHandler.copy(scrFile, new File(screenshotFolderDateWise+fileSeperator+ name + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private String createNewDir(String path)
	{
		File testDirectory = new File(path);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return path;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
		return path;
	}

	
}
