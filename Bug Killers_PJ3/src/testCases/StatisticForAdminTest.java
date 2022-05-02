package testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Constants;
import utilities.ExcelUtils;

public class StatisticForAdminTest {
	//Creating object of ExcelUtils class
    static ExcelUtils excelUtils = new ExcelUtils();
    
    //Using the Constants class values for excel file path 
    static String excelFilePath = Constants.Path_TestData+Constants.File_TestData;

    public static void main(String args[]) throws IOException, InterruptedException {
        //Set the Chrome Driver path
        System.setProperty("webdriver.chrome.driver","D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\chromedriver.exe");
        
        //Creating an object of ChromeDriver
        WebDriver chromedriver = new ChromeDriver();
        
        //Launching the specified URL
        chromedriver.get("http://localhost/hotel-management-php/");
        
        //Identify the WebElements 
        
        //Calling the ExcelUtils class method to initialise the workbook and sheet
        excelUtils.setExcelFile(excelFilePath,"StaticticForAdmin");
         
        
        //iterate over all the row to print the data present in each cell.
        System.out.println("==========Sending  testcase!========");
        
        //System.out.println(excelUtils.getRowCountInSheet());
        for(int i = 1 ; i <= excelUtils.getRowCountInSheet() ; i++)
        {
        	//===============LOG IN SECTION==============//
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
            //=============ENTER CRUD FOR ADMIN SECTION==========//
            WebElement accountButtonAdmin = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
            accountButtonAdmin.click();
            
            WebElement adminCRUDButton = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul > li.nav-item.dropdown.show > div > a:nth-child(3)"));
            adminCRUDButton.click();
            
           
            
            var adminActionCase = excelUtils.getCellData(i,0);
            switch (adminActionCase) {
            	case "Add New Room(s)":
            		//Take screenshot of "All room types" chart before we add new room(s)
            		Thread.sleep(1000);
                    File screenshotBefore = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    	
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "AddNewRoom-Before";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(screenshotBefore, new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
            		
            		
            		
            		// Choose "Add new room" section on sidebar
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement addNewRoomButton = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.side-nav.bg-dark.text-light > li:nth-child(2) > a"));
            		addNewRoomButton.click();
            		
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement roomNumberField = chromedriver.findElement(By.cssSelector("#room_number"));
            		roomNumberField.clear();
            		roomNumberField.sendKeys("000");
            		
            		
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement roomNameField = chromedriver.findElement(By.cssSelector("#room_name"));
            		roomNameField.clear();
            		roomNameField.sendKeys("This room is for testing only!");
            		
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement roomTypeField = chromedriver.findElement(By.cssSelector("#room_type"));
            		roomTypeField.clear();
            		roomTypeField.sendKeys("Testing");
            		
            		
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement addImageButton = chromedriver.findElement(By.cssSelector("#add_room_form > div.row > div:nth-child(2) > div:nth-child(5) > input[type=file]"));
            		addImageButton.sendKeys("D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\room_sample.jpg");
            		
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement addRoomButton = chromedriver.findElement(By.cssSelector("#add_room"));
            		addRoomButton.submit();
            		
            		Thread.sleep(1000);
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement accountButtonAdmin2 = chromedriver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]"));
                    accountButtonAdmin2.click();
                    
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement backToStatisticButton = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li.nav-item.dropdown.show > div > a:nth-child(3)"));
            		backToStatisticButton.click();
            		
            		//Take screenshot of "All room types" chart after we add new room(s)
            		Thread.sleep(1000);
            		File screenshotAfter = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "AddNewRoom-After";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(screenshotAfter, new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
                    //Log out
                    WebElement backToWebsite = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li:nth-child(1) > a"));
                    backToWebsite.click();
                    
                    Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
                	
                	WebElement accountButton2 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
                    accountButton2.click();

                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
                    var logoutButton = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[3]"));
                    logoutButton.click();
            		
            		break;
            	case "Delete Room(s)":
            		//Take screenshot of "All room types" chart before we delete room(s)
            		Thread.sleep(1000);
                    File screenshotBeforeDelete = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    	
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "DeleteRoom-Before";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(screenshotBeforeDelete, new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
                    
                    //Enter Rooms Section 
            		Thread.sleep(1000);
            		WebElement roomsSection = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.side-nav.bg-dark.text-light > li:nth-child(1) > a"));
            		roomsSection.click();
            		
            		Thread.sleep(1000);
                    WebElement trashButton = chromedriver.findElement(By.cssSelector("#dtVerticalScrollExample > tbody > tr:last-child > td:nth-child(14) > a.text-danger"));
                    trashButton.click();
                    
                    WebElement confirmDeleteButton = chromedriver.findElement(By.cssSelector("#admin-main > section > form > div > div:nth-child(2) > button"));
                    confirmDeleteButton.click();
                    
                    //Take screenshot of "All room types" chart after we add new room(s)
                    Thread.sleep(1000);
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement accountButtonAdminDelete = chromedriver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]"));
                    accountButtonAdminDelete.click();
                    
                    Thread.sleep(1000);
            		WebElement backToStatisticButton2 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li.nav-item.dropdown.show > div > a:nth-child(3)"));
            		backToStatisticButton2.click();
                    
            		Thread.sleep(1000);
            		File screenshotAfterDelete = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "DeleteRoom-After";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(screenshotAfterDelete, new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
                    
                    //Log out
                    WebElement backToWebsiteDelete = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li:nth-child(1) > a"));
                    backToWebsiteDelete.click();
                    
                    Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
                	
                	Thread.sleep(1000);
                	WebElement accountButtonDelete = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
                    accountButtonDelete.click();

                    Thread.sleep(1000);
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
                    var logoutButtonDelete = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[3]"));
                    logoutButtonDelete.click();
            		
            		
            		break;
            	case "Delete User(s)":
            		//Take screenshot of "All room types" chart before we delete user(s)
            		Thread.sleep(1000);
                    File scrshotBeforeDeleteUser = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    	
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "DeleteUser-Before";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(scrshotBeforeDeleteUser, new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
                    //Enter User Section
                    WebElement userButton = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.side-nav.bg-dark.text-light > li:nth-child(3) > a"));
            		userButton.click();
            		
            		try {
            			WebElement trashUserButton = chromedriver.findElement(By.cssSelector("#dtVerticalScrollExample > tbody > tr:last-child > td:nth-child(8) > a.text-danger > span"));
                		trashUserButton.click();
            		}
            		catch(Exception e) {
            			System.out.println("Testcase " + i + " with test case ID: " + String.valueOf(excelUtils.getCellData(i,1)) + "encouterd error(s)");
            			
            		}
            		
            		Thread.sleep(1000);
            		File scrshotAfterDeleteUser = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "DeleteUser-After";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(scrshotAfterDeleteUser, new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
            		
            		 //Log out
                    Thread.sleep(1000);
                    WebElement backToWebsiteUser = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li:nth-child(1) > a"));
                    backToWebsiteUser.click();
                    
                    Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
                	
                	WebElement accountButton3 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
                    accountButton3.click();

                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
                    var logoutButto3 = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[3]"));
                    logoutButto3.click();
            		
            		break;
            	case "Add New Reservation(s)":
            		//Take screenshot of "All room types" chart before we add new reservation(s):
            		Thread.sleep(1000);
                    File scrshotBeforeReser = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    	
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "AddNewReservation-Before";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(scrshotBeforeReser , new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
                    
                    //Back to website to add new reservation(s):
                    Thread.sleep(1000);
                    WebElement backToWebsite4 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li:nth-child(1) > a"));
                    backToWebsite4.click();
                    
                    WebDriverWait wait4 = new WebDriverWait(chromedriver, Duration.ofSeconds(80));
                    WebElement element4 = wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/nav/div/ul/li[3]/a")));
                    element4.click();
                    
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    WebElement checkInField = chromedriver.findElement(By.cssSelector("#check_in_date"));
                    
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    WebElement checkOutField = chromedriver.findElement(By.cssSelector("#check_out_date"));
                    
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    WebElement roomsTypeField = chromedriver.findElement(By.cssSelector("#room_id"));
                    
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    WebElement optionOne = chromedriver.findElement(By.xpath("/html/body/section/div/div/div[2]/form/div[2]/select/option[2]"));
                    
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    WebElement adultNumber = chromedriver.findElement(By.cssSelector("#no_adults"));
                    
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    WebElement childrenNumber = chromedriver.findElement(By.cssSelector("#no_children"));
                    
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    WebElement reservationForm = chromedriver.findElement(By.cssSelector("#reservation_details"));
                	    	
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	checkInField.clear();
                	checkInField.sendKeys("2022-06-01");
                	
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	checkOutField.clear();
                	checkOutField.sendKeys("2022-06-02");
                	
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	roomsTypeField.click();
                	
                	optionOne.click();

                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	adultNumber.clear();
                	adultNumber.sendKeys("1");
                	
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	childrenNumber.clear();
                	childrenNumber.sendKeys("1");
                	
                	try {
                		reservationForm.submit();
                	}
                	catch (Exception e) {
                		System.out.println(e);
                	}
                	
                	Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
                    
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	WebElement cardNumberField = chromedriver.findElement(By.cssSelector("#payment_form > div:nth-child(2) > div > div > div > input"));
                    cardNumberField.sendKeys("123");

                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    WebElement expDayField = chromedriver.findElement(By.cssSelector("#payment_form > div.row > div.col-xs-7.col-md-7 > div > input"));
                    expDayField.sendKeys("123");
                	
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	WebElement CVCField = chromedriver.findElement(By.cssSelector("#payment_form > div.row > div.col-xs-5.col-md-5.float-xs-right > div > input"));
                    CVCField.sendKeys("123");

                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    WebElement cardOwnerField = chromedriver.findElement(By.cssSelector("#payment_form > div:nth-child(4) > div > div > input"));
                    cardOwnerField.sendKeys("Admin");

                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    var processPaymentButton = chromedriver.findElement(By.cssSelector("#payment_form > div:nth-child(4) > div > div > input"));
                    try {
                    	
                        processPaymentButton.submit();
                    }
                    catch (Exception e) {
                    	
                    	System.out.println(e);
                    	
                    }
                   
                    //Back to admin CRUD page
                    Thread.sleep(1000);
                    WebElement accountButtonAdmin4 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
                    accountButtonAdmin4.click();
                    
                    WebElement adminCRUDButton4 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul > li.nav-item.dropdown.show > div > a:nth-child(3)"));
                    adminCRUDButton4.click();
                    
                	
                	Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	
                	 //Take screenshot of "All room types" chart after we add new reservation(s).
                    Thread.sleep(1000);
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement accountButton4 = chromedriver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]"));
                    accountButton4.click();
                    
                    Thread.sleep(1000);
            		WebElement backToStatistic4 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li.nav-item.dropdown.show > div > a:nth-child(3)"));
            		backToStatistic4.click();
                    
            		Thread.sleep(1000);
            		File screenshot4= ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "AddNewReservation-After";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(screenshot4, new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
                    
                    //Log out
                    WebElement backToWebsite5 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li:nth-child(1) > a"));
                    backToWebsite5.click();
                    
                    Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
                	
                	Thread.sleep(1000);
                	WebElement accountButton5 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
                    accountButton5.click();

                    Thread.sleep(1000);
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
                    var logoutButton4 = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[3]"));
                    logoutButton4.click();
                	
                	
            		break;
            	case "Change Room(s) Type":
            		//Take screenshot of "All room types" chart before we add new reservation(s):
            		Thread.sleep(1000);
                    File scrshotBefore5 = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    	
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "ChangeRoomType-Before";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(scrshotBefore5 , new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }

                    //Enter Rooms Section 
            		Thread.sleep(1000);
            		WebElement roomsSection5 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.side-nav.bg-dark.text-light > li:nth-child(1) > a"));
            		roomsSection5.click();
            		
            		Thread.sleep(1000);
                    WebElement editButton = chromedriver.findElement(By.cssSelector("#dtVerticalScrollExample > tbody > tr:last-child > td:nth-child(14) > a.text-success > span"));
                    editButton.click();
                    
                    WebElement roomTypeField5 = chromedriver.findElement(By.cssSelector("#room_type"));
                    roomTypeField5.clear();
                    roomTypeField5.sendKeys("Prenium");
                    
                    WebElement confirmEditButton = chromedriver.findElement(By.cssSelector("#edit_room"));
                    confirmEditButton.submit();
                    
                    //Back to admin CRUD page
                    Thread.sleep(1000);
                    WebElement accountButtonAdmin6 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
                    accountButtonAdmin6.click();
                    
                    WebElement adminCRUDButton6 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul > li.nav-item.dropdown.show > div > a:nth-child(3)"));
                    adminCRUDButton6.click();
                    
                	
                	Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	
                	 //Take screenshot of "All room types" chart after we add new reservation(s).
                    Thread.sleep(1000);
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement accountButton6 = chromedriver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]"));
                    accountButton6.click();
                    
                    Thread.sleep(1000);
            		WebElement backToStatistic5 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li.nav-item.dropdown.show > div > a:nth-child(3)"));
            		backToStatistic5.click();
                    
            		Thread.sleep(1000);
            		File screenshot5= ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "ChangeRoomType-After";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(screenshot5, new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
                    
                    //Log out
                    WebElement backToWebsite6 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li:nth-child(1) > a"));
                    backToWebsite6.click();
                    
                    Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
                	
                	Thread.sleep(1000);
                	WebElement accountButton7 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
                    accountButton7.click();

                    Thread.sleep(1000);
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
                    var logoutButton5 = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[3]"));
                    logoutButton5.click();
                	
                    
                    
            		break;
            	case "Change Room(s) Type to Entire New Type": 
            		//Take screenshot of "All room types" chart before we add new reservation(s):
            		Thread.sleep(1000);
                    File scrshotBefore6 = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                    	
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "ChangeRoomTypeEntireNew-Before";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(scrshotBefore6 , new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }

                    //Enter Rooms Section 
            		Thread.sleep(1000);
            		WebElement roomsSection6 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.side-nav.bg-dark.text-light > li:nth-child(1) > a"));
            		roomsSection6.click();
            		
            		Thread.sleep(1000);
                    WebElement editButton2 = chromedriver.findElement(By.cssSelector("#dtVerticalScrollExample > tbody > tr:last-child > td:nth-child(14) > a.text-success > span"));
                    editButton2.click();
                    
                    WebElement roomTypeField6 = chromedriver.findElement(By.cssSelector("#room_type"));
                    roomTypeField6.clear();
                    roomTypeField6.sendKeys("Some Entire New Type");
                    
                    WebElement confirmEditButton2 = chromedriver.findElement(By.cssSelector("#edit_room"));
                    confirmEditButton2.submit();
                    
                    //Back to admin CRUD page
                    Thread.sleep(1000);
                    WebElement accountButtonAdmin7 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
                    accountButtonAdmin7.click();
                    
                    WebElement adminCRUDButton7 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul > li.nav-item.dropdown.show > div > a:nth-child(3)"));
                    adminCRUDButton7.click();
                    
                	
                	Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                	
                	 //Take screenshot of "All room types" chart after we add new reservation(s).
                    Thread.sleep(1000);
            		chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            		WebElement accountButton8 = chromedriver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]"));
                    accountButton8.click();
                    
                    Thread.sleep(1000);
            		WebElement backToStatistic6 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li.nav-item.dropdown.show > div > a:nth-child(3)"));
            		backToStatistic6.click();
                    
            		Thread.sleep(1000);
            		File screenshot6 = ((TakesScreenshot) chromedriver).getScreenshotAs(OutputType.FILE);
                    
                    //Copy the file to a location and use try catch block to handle exception
                    try {
                    	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
                        Date date = new Date();
                        var path = "D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\screenShotsAsOuput\\";
                    	var imgName = "ChangeRoomTypeEntireNew-After";
                    	var ext = String.valueOf(formatter.format(date));
                    	var completePath = path + imgName + ext + ".jpeg";
                    	
                        FileUtils.copyFile(screenshot6, new File(completePath));
                    
                    } catch (IOException e) {
                    	
                        System.out.println(e.getMessage());
                        
                    }
                    
                    //Log out
                    WebElement backToWebsite7 = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li:nth-child(1) > a"));
                    backToWebsite7.click();
                    
                    Thread.sleep(1000);
                	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
                	
                	Thread.sleep(1000);
                	WebElement accountButton9 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
                    accountButton9.click();

                    Thread.sleep(1000);
                    chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
                    var logoutButton6 = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[3]"));
                    logoutButton6.click();
            		
            		
            		break;
            	default:
            		System.out.println("Sorry, we couldn't understand this action!");
            }
            
        	//===============LOG OUT SECTION==============//
        	Thread.sleep(1000);
        	WebElement backToWebSiteButton = chromedriver.findElement(By.cssSelector("#navbarSupportedContent > ul.navbar-nav.ml-auto > li:nth-child(1) > a"));
        	backToWebSiteButton.click();
        	
        	chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        	
        	WebElement accountButton2 = chromedriver.findElement(By.xpath("//*[@id='navbarDropdown']"));
            accountButton2.click();

            chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
            var logoutButton = chromedriver.findElement(By.xpath("//*[@id='navbarSupportedContent']/ul/li[5]/div/a[3]"));
            logoutButton.click();
            
            System.out.println("Completed testcase " + i + " with test case ID: " + String.valueOf(excelUtils.getCellData(i,1)));

        	
          
        }

        
        System.out.println("=========Completed all testcase!======");
        
        //closing the driver
        
        chromedriver.quit();
    }
}
