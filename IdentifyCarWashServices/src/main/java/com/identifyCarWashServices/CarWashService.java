package com.identifyCarWashServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.TestNG;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class CarWashService {
	
	 public static WebDriver driver;
	 public static String filePath=null;
	 static CaptureScreenshot cst=new CaptureScreenshot();
	 //to set location to "Mumbai"
	 public static void checkLocation(WebDriver driver) {
		 //To locate city
		 WebElement city=driver.findElement(By.id("city"));
		 //highlight the element
		 HighlightElement.highlightElement(driver, city);
		 //Click on the city button 
		 city.click();
		 //Clear the value
		 city.clear();
		 //send value "Mumbai"
		 city.sendKeys("Mumbai");
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Mumbai")));
		 
		/* Actions builder=new Actions(driver);
		  builder.moveToElement(driver.findElement(By.id("Mumbai"))).build().perform();
		 //city.sendKeys(Keys.TAB);
		 driver.findElement(By.id("Mumbai")).click(); */
	 }
	 
	 //to locate "Search for anything,anywhere in India" textbox and search the service
	 public static void enterServiceDetails(WebDriver driver) throws Exception{
		 //Get required data from KeywordDriven Class
		 String searchBox=KeywordDriven.getSearchBox();
		 String serviceName=KeywordDriven.getService();
		 //Locate searchBox
		 WebElement element= driver.findElement(By.xpath(searchBox));
		 //Highlight the searchBox
		 HighlightElement.highlightElement(driver, element);
		 //Click on searchBox and enter the value
		 element.click();
		 element.sendKeys(serviceName);
		 //Locate the search button
		 WebElement search=driver.findElement(By.xpath("//body/div[@id='setbackfix']/section[1]/section[2]/section[1]/div[2]/div[1]/span[1]/button[1]"));
		 
		//Decide the filePath and capture screenshot
		 filePath=System.getProperty("user.dir")+"/Screenshots/homepageScreenshot.png";
		 cst.captureScreenshot(driver, filePath);
		 TestNG.test.log(LogStatus.INFO, "Homepage Screenshot:"+TestNG.test.addScreenCapture(filePath));
		 //Highlight the search button
		 HighlightElement.highlightElement(driver, search);
		 element.sendKeys(Keys.ENTER);
		 
		 
	 }
	 
	 //to select "Check car wash services near me" filter
	 public static void checkServiceNearMe(WebDriver driver) throws InterruptedException {
		 //To delete all cookies and refresh the page
		 driver.manage().deleteAllCookies();
		 driver.navigate().refresh();
		 Thread.sleep(2000);
		 //Locate the "Car Wash Services At Home near me" link
		 WebElement link1=driver.findElement(By.linkText("https://www.justdial.com/Mumbai/Car-Washing-Services-At-Home/nct-10837909"));
		 //Higlight the link and click
		 HighlightElement.highlightElement(driver, link1);
		 link1.click();
		 //to close the popup
		 TestNG.testRandomPopup();
	     driver.manage().deleteAllCookies();
	 }
	 
	 //to select filter "Ratings"
	 public static void applyRatings(WebDriver driver) throws Exception {
		 //Locate "ratings" button
		 Actions builder=new Actions(driver);
		 builder.moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[2]/div[2]/ul[1]/li[6]/a[1]/span[1]"))).build().perform();
		 WebElement rate=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[2]/div[2]/ul[1]/li[6]/a[1]/span[1]"));
		 //Highlight the "rating" button and click it
		 HighlightElement.highlightElement(driver, rate);
		 rate.click();
		//Decide the filePath and capture screenshot
		 filePath=System.getProperty("user.dir")+"/Screenshots/resultPageScreenshot.png";
		 cst.captureScreenshot(driver, filePath);
		 TestNG.test.log(LogStatus.INFO, "Result Page Screenshot:"+TestNG.test.addScreenCapture(filePath));
	 }
	 
	 //to check results having votes greater than 20
	 public static boolean checkVotes(String votes) {
			String vote=votes.replace(" Votes", "");
			int v=Integer.parseInt(vote);
				if(v>20) {
					return true;
				}
				else
					return false; 
	 }
	 
	 //to check results having ratings more than 4*
	 public static boolean checkRatings(String rating) {
		 float r=Float.parseFloat(rating);
			if(r>4.0) {
				return true;
			}
			else
				return false; 
	 }
	 
	 //to print first 5 result and send to excel file
	 public static void displayResults(WebDriver driver) {
		 HashMap<String,String> hmap=new HashMap<String,String>();
		 hmap.put("dc","+");
		 hmap.put("ba","-");
		 hmap.put("fe","(");
		 hmap.put("hg", ")");
		 hmap.put("acb", "0");
		 hmap.put("yz", "1");
		 hmap.put("wx","2");
		 hmap.put("vu","3");
		 hmap.put("ts","4");
		 hmap.put("rq","5");
		 hmap.put("po", "6");
		 hmap.put("nm","7");
		 hmap.put("lk","8");
		 hmap.put("ji","9");
		 
		 //Store results displayed in the list
		 List<WebElement> results = driver.findElements(By.className("cntanr"));
		 
		 //Create empty lists to store names and contacts
		 List<String> nameList=new ArrayList<String>();
		 List<String> contactList=new ArrayList<String>();
		 
		 for(int i=0;i<5;i++) {
			 //get the service name from i th result
			 String name=results.get(i).findElement(By.className("lng_cont_name")).getText();
			 //get the contact number from i th result
			 List<WebElement> contact = results.get(i).findElements(By.className("mobilesv"));
			 //get the votes from i th result
			 String votes=results.get(i).findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/section[1]/div[1]/ul[1]/li["+(i+1)+"]/section[1]/div[1]/section[1]/div[1]/p[1]/a[1]/span[3]")).getText();
			 //get the ratings from i th result
			 String ratings=results.get(i).findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/section[1]/div[1]/ul[1]/li["+(i+1)+"]/section[1]/div[1]/section[1]/div[1]/p[1]/a[1]/span[1]")).getText();
			 List<String >myList=new ArrayList<String>();
			 //to concatenate all the numbers and add it to "myList"
			 for(int j=0;j<contact.size();j++) {
				 String myString=contact.get(j).getAttribute("class").split("-")[1];
				 myList.add(hmap.get(myString));
			 }
			 //to check whether results have valid votes and ratings 
			 if(checkVotes(votes) && checkRatings(ratings)) {
				 //add names to "nameList"
				 nameList.add(name);
				 //add contact numbers to "contactList"
				 contactList.add("".join("", myList));
			 }
		}
		//send the names and contact numbers to excel file
		ExcelData.writeExcel(nameList, contactList);
		
		//print the names and contact numbers on console
		for(int i=0;i<contactList.size();i++) {
			System.out.println(nameList.get(i)+" "+contactList.get(i));
		}
	}
}
	 
	 
	 
	 
	 
	 

