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

public class CRUDUser {
    
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
        excelUtils.setExcelFile(excelFilePath,"CRUDUser");
         
        //JavascriptExecutor js = (JavascriptExecutor) chromedriver;  
        
        //iterate over all the row to print the data present in each cell.
        System.out.println("==========Sending  testcase!========");
        
//        	begin test case 1
        	Thread.sleep(1000);
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        	WebElement accountButton = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
            accountButton.click();
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement loginButton = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[1]"));
            loginButton.click();

            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement submitFormButton = chromedriver.findElement(By.xpath("//*[@id='login_user']"));
            submitFormButton.submit();
            
        	WebDriverWait wait = new WebDriverWait(chromedriver, Duration.ofSeconds(80));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/nav/div/ul/li[5]/div/a[1]")));
            element.click();
            
            WebDriverWait wait1 = new WebDriverWait(chromedriver, Duration.ofSeconds(80));
            WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/nav/div/ul[2]/li[3]/a")));
            element1.click();
            
//            end test case 1
            
//            begin test case 2
            
            WebDriverWait wait2 = new WebDriverWait(chromedriver, Duration.ofSeconds(80));
            WebElement element2 = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/section/table/tbody/tr[0]/td[7]/a[0]/")));
            element2.click();
               
//           end test case 2
            
//          begin test case 3
            
          WebDriverWait wait3 = new WebDriverWait(chromedriver, Duration.ofSeconds(80));
          WebElement element3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/section/table/tbody/tr[0]/td[7]/a[1]/")));
          element3.click();
             
//         end test case 3
        	
        	System.out.println("Completed testcase ");
        

        
        System.out.println("=========Completed all testcase!======");
        
        //closing the driver
        
        chromedriver.quit();
    }
}