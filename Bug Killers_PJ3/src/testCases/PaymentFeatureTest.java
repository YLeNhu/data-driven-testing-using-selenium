package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.Constants;
import utilities.ExcelUtils;
import java.io.IOException;
import java.time.Duration;


public class PaymentFeatureTest {
	//Creating object of ExcelUtils class
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
        excelUtils.setExcelFile(excelFilePath,"Payment");
         
        
        //iterate over all the row to print the data present in each cell.
        System.out.println("==========Sending  testcase!========");

        //System.out.println(excelUtils.getRowCountInSheet());
        for(int i = 1 ; i <= excelUtils.getRowCountInSheet() ; i++)
        {
        	//==========LOG IN SECTION==========//
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
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/nav/div/ul/li[3]/a")));
            element.click();
            //===================================//
            
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement checkInField = chromedriver.findElement(By.cssSelector("#check_in_date"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement checkOutField = chromedriver.findElement(By.cssSelector("#check_out_date"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement roomsTypeField = chromedriver.findElement(By.cssSelector("#room_id"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement optionOne = chromedriver.findElement(By.cssSelector("#room_id > option:nth-child(2)"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement adultNumber = chromedriver.findElement(By.cssSelector("#no_adults"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement childrenNumber = chromedriver.findElement(By.cssSelector("#no_children"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement reservationForm = chromedriver.findElement(By.cssSelector("#reservation_details"));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	checkInField.clear();
        	checkInField.sendKeys(excelUtils.getCellData(i,0));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	checkOutField.clear();
        	checkOutField.sendKeys(excelUtils.getCellData(i,1));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	roomsTypeField.click();
        	
        	optionOne.click();
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	adultNumber.clear();
        	adultNumber.sendKeys(excelUtils.getCellData(i,2));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	childrenNumber.clear();
        	childrenNumber.sendKeys(excelUtils.getCellData(i,3));
        	
        	try {
        		reservationForm.submit();
        	}
        	catch (Exception e) {
        		System.out.println(e);
        	}
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	WebElement cardNumberField = chromedriver.findElement(By.cssSelector("#payment_form > div:nth-child(2) > div > div > div > input"));
            cardNumberField.sendKeys(excelUtils.getCellData(i,5));

            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement expDayField = chromedriver.findElement(By.cssSelector("#payment_form > div.row > div.col-xs-7.col-md-7 > div > input"));
            expDayField.sendKeys(excelUtils.getCellData(i,6));
        	
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	WebElement CVCField = chromedriver.findElement(By.cssSelector("#payment_form > div.row > div.col-xs-5.col-md-5.float-xs-right > div > input"));
            CVCField.sendKeys(excelUtils.getCellData(i,7));

            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement cardOwnerField = chromedriver.findElement(By.cssSelector("#payment_form > div:nth-child(4) > div > div > input"));
            cardOwnerField.sendKeys(excelUtils.getCellData(i,8));

            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            var processPaymentButton = chromedriver.findElement(By.cssSelector("#payment_form > div:nth-child(4) > div > div > input"));
            try {
            	
                processPaymentButton.submit();
            }
            catch (Exception e) {
            	
            	System.out.println("Fail testcase " + i + " with test case ID: " + String.valueOf(excelUtils.getCellData(i,9)));
            	
            }
        	
        	Thread.sleep(1000);
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	
        	WebElement accountButton2 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
            accountButton2.click();

            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
            var logoutButton = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[3]"));
            logoutButton.click();
            
            if(i == 1) {
            	System.out.println("Completed testcase " + i + " with test case ID: " + String.valueOf(excelUtils.getCellData(i,9)));
            }
        	
            else{
            	
            	System.out.println("Fail testcase " + i + " with test case ID: " + String.valueOf(excelUtils.getCellData(i,9)));
            }
        }

        
        System.out.println("=========Completed all testcase!======");
        
        //closing the driver
        
        chromedriver.quit();
    }
}
