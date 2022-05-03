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

public class CRUDRoom {
    
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
        excelUtils.setExcelFile(excelFilePath,"CRUDRoom");
         
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
            WebElement submitFormButton = chromedriver.findElement(By.xpath("//*[@id='login_user']"));
            submitFormButton.submit();
            
        	WebDriverWait wait = new WebDriverWait(chromedriver, Duration.ofSeconds(80));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/nav/div/ul/li[5]/div/a[1]")));
            element.click();
           
//          begin test case 1
            
            WebDriverWait wait1 = new WebDriverWait(chromedriver, Duration.ofSeconds(80));
            WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/nav/div/ul[2]/li[1]/a")));
            element1.click();
            
//            end test case 1
            
//          begin test case 2
            
            WebDriverWait wait2 = new WebDriverWait(chromedriver, Duration.ofSeconds(80));
            WebElement element2 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/nav/div/ul[2]/li[2]/a")));
            element1.click();
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement room_number = chromedriver.findElement(By.cssSelector("#room_number"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement room_name = chromedriver.findElement(By.cssSelector("#room_name"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement room_type = chromedriver.findElement(By.cssSelector("#room_type"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement room_floor = chromedriver.findElement(By.cssSelector("#room_floor"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement room_view = chromedriver.findElement(By.cssSelector("#room_view"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement room_amenities = chromedriver.findElement(By.cssSelector("#room_amenities"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement room_beds = chromedriver.findElement(By.cssSelector("#room_beds"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement bed_type = chromedriver.findElement(By.cssSelector("#bed_type"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement room_capacity = chromedriver.findElement(By.cssSelector("#room_capacity"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement room_price = chromedriver.findElement(By.cssSelector("#room_price"));
                  
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            WebElement add_room = chromedriver.findElement(By.xpath("//*[@id='add_room']"));
            
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            room_number.clear();
            room_number.sendKeys(excelUtils.getCellData(i,0));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	room_name.clear();
        	room_name.sendKeys(excelUtils.getCellData(i,1));

            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            room_type.clear();
            room_type.sendKeys(excelUtils.getCellData(i,2));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	room_floor.clear();
        	room_floor.sendKeys(excelUtils.getCellData(i,3));
        	
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            room_view.clear();
            room_view.sendKeys(excelUtils.getCellData(i,4));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	room_amenities.clear();
        	room_amenities.sendKeys(excelUtils.getCellData(i,5));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	room_beds.clear();
        	room_beds.sendKeys(excelUtils.getCellData(i,6));
        	
            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            bed_type.clear();
            bed_type.sendKeys(excelUtils.getCellData(i,7));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	room_capacity.clear();
        	room_capacity.sendKeys(excelUtils.getCellData(i,8));
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	room_price.clear();
        	room_price.sendKeys(excelUtils.getCellData(i,9));
        	try {
        		add_room.submit();
        	}
        	catch (Exception e) {
        		System.out.println(e);
        	}
            
//            end test case 2
        	
        	System.out.println("Completed testcase");
        }

        
        System.out.println("=========Completed all testcase!======");
        
        //closing the driver
        
        chromedriver.quit();
    }
}