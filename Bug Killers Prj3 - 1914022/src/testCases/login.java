package testCases;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.Constants;
import utilities.ExcelUtils;
import java.io.IOException;
import java.time.Duration;
//import java.util.concurrent.TimeUnit;

public class login {
    
	//creating object of ExcelUtils class
    static ExcelUtils excelUtils = new ExcelUtils();
    
    //using the Constants class values for excel file path 
    static String excelFilePath =Constants.Path_TestData+Constants.File_TestData;

    public static  void main(String args[]) throws IOException, InterruptedException {
        //set the Chrome Driver path
        System.setProperty("webdriver.chrome.driver","C:\\Users\\pq\\eclipse-workspace\\Bug Killers Prj3 - 1914022\\src\\chromedriver.exe");
        
        //Creating an object of ChromeDriver
        WebDriver chromedriver = new ChromeDriver();
        
        //launching the specified URL
        chromedriver.get("http://localhost/hotel-management-php/");
        
        //Identify the WebElements 
        
        //calling the ExcelUtils class method to initialise the workbook and sheet
        excelUtils.setExcelFile(excelFilePath,"Login");
         
        //JavascriptExecutor js = (JavascriptExecutor) chromedriver;  
        
        //iterate over all the row to print the data present in each cell.
        System.out.println("==========Sending  testcase!========");
        for(int i = 1 ;i <= excelUtils.getRowCountInSheet();i++)
        {
        	Thread.sleep(1000);
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        	WebElement accountButton = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
            accountButton.click();
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement loginButton = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[1]"));
            loginButton.click();
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement username = chromedriver.findElement(By.cssSelector("#user_email"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement pwd = chromedriver.findElement(By.cssSelector("#user_password"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            username.clear();
            username.sendKeys(excelUtils.getCellData(i,0));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	pwd.clear();
        	pwd.sendKeys(excelUtils.getCellData(i,1));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement submitFormButton = chromedriver.findElement(By.xpath("//*[@id='login_user']"));
            
        	try {
        		submitFormButton.submit();
        	}
        	catch (Exception e) {
        		System.out.println(e);
        	}
  	
        	Thread.sleep(1000);
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        	
        	WebElement accountButton1 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
            accountButton1.click();
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement loginButton1 = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[1]"));
            loginButton1.click();
        	
        	System.out.println("Completed testcase " + i + " with test case ID: " + String.valueOf(excelUtils.getCellData(i,2)));
        }

        
        System.out.println("=========Completed all testcase!======");
        
        //closing the driver
        
        chromedriver.quit();
    }
}