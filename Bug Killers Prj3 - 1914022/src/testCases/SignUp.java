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

public class SignUp {
    
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
        excelUtils.setExcelFile(excelFilePath,"SignUp");
         
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
            WebElement loginButton = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[2]"));
            loginButton.click();
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
            WebElement phone = chromedriver.findElement(By.cssSelector("#user_phone"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement phone_next = chromedriver.findElement(By.xpath("#proceed-part-2"));
            phone_next.click();
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement fname = chromedriver.findElement(By.cssSelector("#user_fname"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement lname = chromedriver.findElement(By.cssSelector("#user_lname"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement dob = chromedriver.findElement(By.cssSelector("#dob"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement name_next = chromedriver.findElement(By.xpath("#proceed-part-3"));
            name_next.click();
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement user_email = chromedriver.findElement(By.cssSelector("#user_email"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement user_password = chromedriver.findElement(By.cssSelector("#user_password"));

            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            phone.clear();
            phone.sendKeys(excelUtils.getCellData(i,0));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	fname.clear();
        	fname.sendKeys(excelUtils.getCellData(i,1));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	lname.clear();
        	lname.sendKeys(excelUtils.getCellData(i,2));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	dob.clear();
        	dob.sendKeys(excelUtils.getCellData(i,3));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	user_email.clear();
        	user_email.sendKeys(excelUtils.getCellData(i,4));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	user_password.clear();
        	user_password.sendKeys(excelUtils.getCellData(i,5));
    	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement signUp = chromedriver.findElement(By.xpath("//*[@id='signup_user']"));
            
        	try {
        		signUp.submit();
        	}
        	catch (Exception e) {
        		System.out.println(e);
        	}
    	
        	Thread.sleep(1000);
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        	
        	WebElement accountButton2 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
            accountButton2.click();

            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
            var logoutButton = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[3]"));
            logoutButton.click();
        	
        	System.out.println("Completed testcase " + i + " with test case ID: " + String.valueOf(excelUtils.getCellData(i,6)));
        }

        
        System.out.println("=========Completed all testcase!======");
        
        //closing the driver
        
        chromedriver.quit();
    }
}