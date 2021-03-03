package com.test;


import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.identifyCarWashServices.CarWashService;
import com.identifyCarWashServices.DriverSetup;
import com.identifyCarWashServices.Fitness;
import com.identifyCarWashServices.FreeListing;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestNG {
	 //Declaration of static variables.
	public static String Url= "https://www.justdial.com";
	public static Scanner sc=new Scanner(System.in);
	public static WebDriver driver;
	
	//Extent report
	static String extentReportFile=System.getProperty("user.dir")+"/ExtentReport/extentReportFile.html";
	public static ExtentReports report = new ExtentReports(extentReportFile);
	public static ExtentTest test;
	
	//Method invoking the browser depending on platform requirements.
	@BeforeTest
    public WebDriver getDriver() throws Exception {
    	
    	test = report.startTest("Identify Car Wash Services");
		test.log(LogStatus.INFO, "Opening the Browser");
    	
		//System.out.print("Select one of the browser (Chrome / FireFox / Opera): ");
		//String browser=sc.next();
		
		driver=DriverSetup.setUpDriver("Chrome");
		test.log(LogStatus.PASS, "Browser Opened");
		
		test.log(LogStatus.INFO, "Opening the site: www.justdial.com");
		driver.get(Url);
		test.log(LogStatus.PASS, "Website Opened Successfully");
		driver.manage().window().maximize();
		return driver;
    }
    
    //To navigate the main page
    public static void navigate() {
    	driver.navigate().to(Url);;
    }
    
    //To catch popups and close it
 public static void testRandomPopup() throws InterruptedException {
	 	//To implement wait
	 	WebDriverWait wait = new WebDriverWait(driver, 5);
		By closeElementLocator = By.xpath("/html/body/section[16]/section/span");
		
		//Check if the popup is displayed and close it
	 	if (isDisplayed()) {
	        WebElement closeElement = wait.until(
	        visibilityOfElementLocated(closeElementLocator));
	        closeElement.click();
	 	}		  
 }
 
 	//To check if popup is displayed
 	private static Boolean isDisplayed() throws InterruptedException {
 		WebDriverWait wait=new WebDriverWait(driver, 5);
 		By popupIdLocator = By.xpath("/html/body/section[16]/section");
	     try {
	        WebElement popup = wait.until(
	                visibilityOfElementLocated(popupIdLocator));
	        return popup.isDisplayed();
	     }
	     catch (Exception ex) {
	       return false;
	     }
	  }
    
 	//Call all the mandatory methods for Car Wash Services
 	@Test
    public void fetchCarWashServices() throws Exception {
    	
		test.log(LogStatus.INFO, "Enter 'Mumbai' as location");
 		//To check location
    	CarWashService.checkLocation(driver);
    	test.log(LogStatus.PASS,"Location set to 'Mumbai' Successfully");
    	
    	test.log(LogStatus.INFO, "Enter service name");
    	//to enter service details
    	CarWashService.enterServiceDetails(driver);
    	test.log(LogStatus.PASS, "Service name entered successfully");
    	
    	//Delete all cookies and refresh
    	driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        
        test.log(LogStatus.INFO, "Handle random popup");
        //To handle popups
        testRandomPopup();
        test.log(LogStatus.PASS, "Popup closed successfully");
        
        //Delete all cookies
        driver.manage().deleteAllCookies();
        
        //To handle popups
        testRandomPopup();
        
        test.log(LogStatus.INFO, "Select 'ratings' filter");
        //To apply ratings
    	CarWashService.applyRatings(driver);
    	test.log(LogStatus.PASS, "'Ratings' filter applied successfully");
    	
    	//To handle popups
    	testRandomPopup();
    	
    	//Delete all cookies
    	driver.manage().deleteAllCookies();
    	
    	//To handle popups
    	testRandomPopup();
    	
    	test.log(LogStatus.INFO, "Display top 5 results on console");
    	//To display top 5 results
    	CarWashService.displayResults(driver);
    	test.log(LogStatus.PASS, "Top 5 results displayed on console successfully");
    }
	
    //to call mandatory methods for Free Listing
 	@Test(dependsOnMethods="fetchCarWashServices")
    public void checkFreeListing() throws Exception {
    	
 		test.log(LogStatus.INFO, "Navigate to home page");
    	//Navigate to the main page
    	navigate();
    	test.log(LogStatus.PASS, "Navigated to home page successfully");
    	
    	test.log(LogStatus.INFO, "Click on 'Free Listing button'");
    	//To locate freelisting option
    	FreeListing.freeListing(driver);
    	test.log(LogStatus.PASS, "'Free Listing' button clicked successfully");
    	
    	test.log(LogStatus.INFO, "Validate Free Listing form");
    	//To validate the form
    	FreeListing.validateForm(driver);
    	test.log(LogStatus.PASS, "Free Listing form validated successfully");
    }
	
    //to call mandatory methods for Fitness
 	@Test(dependsOnMethods="checkFreeListing")
    public void fetchFitnessMenu() throws Exception {
    	
    	test.log(LogStatus.INFO, "Navigate to home page");
    	//Navigate to the main page
    	navigate();
    	test.log(LogStatus.PASS, "Navigated to home page successfully");
    	
    	test.log(LogStatus.INFO, "Scroll the page");
    	//To scroll the page
    	Fitness.scroll(driver);
    	test.log(LogStatus.PASS, "Page scrolled successfully");
    	
    	test.log(LogStatus.INFO, "Select 'Fitness' option");
    	//To select the fitness option
    	Fitness.selectFitnessOption(driver);
    	test.log(LogStatus.PASS, "'Fitness' option selected successfully");
    	
    	test.log(LogStatus.INFO, "Select 'Gym' option");
    	//To select the gym option
    	Fitness.selectGymOption(driver);
    	test.log(LogStatus.PASS, "'Gym' option selected successfully");
    	
    	test.log(LogStatus.INFO, "Display all sub menu items on console");
    	//to display submenu items
    	Fitness.displayMenus(driver);
    	test.log(LogStatus.PASS, "List of all sub menu items displayed on console successfully");
    }
    
    
   //To close the browser
 	@Test(dependsOnMethods="fetchFitnessMenu")
    public void closeApplication() {
 		test.log(LogStatus.INFO, "Close the browser");
    	driver.close();
    	report.flush();
    	test.log(LogStatus.PASS, "Browser closed successfully");
    }
    
}
