package testCases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.Constants;
import utilities.ExcelUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class SearchBarTest {
    static ExcelUtils excelUtils = new ExcelUtils();
    
    //Using the Constants class values for excel file path 
    static String excelFilePath = Constants.Path_TestData+Constants.File_TestData;

    public static  void main(String args[]) throws IOException, InterruptedException {
    	
        //Set the Chrome Driver path
        System.setProperty("webdriver.chrome.driver","D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\chromedriver.exe");
        
        //Creating an object of ChromeDriver
        WebDriver chromedriver = new ChromeDriver();
        
        //Launching the specified URL
        chromedriver.get("http://localhost/hotel-management-php/");
        
        //Identify the WebElements 
        
        //Calling the ExcelUtils class method to initialise the workbook and sheet
        System.out.println("==========Sending  testcase!========");
        excelUtils.setExcelFile(excelFilePath,"SearchBar");
        
        //Take screenshot of "All room types" chart before we add new room(s)
		Thread.sleep(1000);
        File screenshotBefore = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
        
        //Copy the file to a location and use try catch block to handle exception
        try {
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	
        	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
            Date date = new Date();
            var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
        	var imgName = "SearchBarTest-Before";
        	var ext = String.valueOf(formatter.format(date));
        	var completePath = path + imgName + ext + ".jpeg";
        	
            FileUtils.copyFile(screenshotBefore, new File(completePath));
        
        } catch (IOException e) {
        	
            System.out.println(e.getMessage());
            
        }
        
        WebElement searchBar = chromedriver.findElement(By.cssSelector("#find-available-rooms-form > div:nth-child(4) > button"));
        searchBar.click();
         
        Thread.sleep(1000);
        File screenshotAfter= ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
        
        //Copy the file to a location and use try catch block to handle exception
        try {
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	
        	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
            Date date = new Date();
            var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
        	var imgName = "SearchBarTest-After";
        	var ext = String.valueOf(formatter.format(date));
        	var completePath = path + imgName + ext + ".jpeg";
        	
            FileUtils.copyFile(screenshotAfter, new File(completePath));
        
        } catch (IOException e) {
        	
            System.out.println(e.getMessage());
            
        }

        System.out.println("=========Completed all testcase!======");
        
        //closing the driver
        
        chromedriver.quit();
    }
}
