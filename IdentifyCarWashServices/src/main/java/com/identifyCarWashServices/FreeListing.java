package com.identifyCarWashServices;

//import org.openqa.selenium.Alert; 

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By; 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions; 

import org.openqa.selenium.support.ui.WebDriverWait; 

import org.testng.annotations.AfterSuite; 

import org.testng.annotations.BeforeSuite; 

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.TestNG;





//get the driver from driver class 

public class FreeListing{ 

  static WebDriver driver; 

  static WebDriverWait wait=null; 
  static String filePath=null;
  static CaptureScreenshot cst=new CaptureScreenshot();

//verify the title of the page 

 // @Test 

  public void Title(){ 

      String s=driver.getTitle(); 

      System.out.println(s); 

      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

  } 

//click on the listing button to go forward 
  public static void freeListing(WebDriver driver){ 
	  //Locate "Free Listing" button
      WebElement fl=driver.findElement(By.xpath("//a[@id='h_flist']")); 
      //Highlight the button and click it
      HighlightElement.highlightElement(driver, fl);
      fl.click();
      //Implement wait for 10 seconds
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

  } 

  //To validate the form and identify errors 
  public static void validateForm(WebDriver driver) throws Exception { 
	  
      //TEST:1 Not insert any value in mobile number section 
	  
	  //Empty String array to store the error messages
	  String[] error=new String[4];
	  
	  //Get required data from KeywordDriven class
	  String company=KeywordDriven.getCompany();
	  String fname=KeywordDriven.getFirstName();
	  String lname=KeywordDriven.getLastName();
	  String invalid1=KeywordDriven.getInvalidContact1();
	  String invalid2=KeywordDriven.getInvalidContact2();
	  String invalid3=KeywordDriven.getInvalidContact3();
	  String valid=KeywordDriven.getValidContact();
	  
	  //Locate "company" textbox, highlight it and enter the value
      WebElement com=driver.findElement(By.xpath("//input[@id='fcom']"));
      HighlightElement.highlightElement(driver, com);
      com.sendKeys(company);
      
      //Implement wait for 10 seconds
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      //Locate "firstname" textbox, highlight it and enter the value
      WebElement fn=driver.findElement(By.id("fname"));
      HighlightElement.highlightElement(driver, fn);      
      fn.sendKeys(fname); 
      
    //Implement wait for 10 seconds
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      //Locate "lastname" textbox, highlight it and enter the value
      WebElement ln=driver.findElement(By.id("lname"));
      HighlightElement.highlightElement(driver, ln);
      ln.sendKeys(lname); 
      
    //Implement wait for 10 seconds
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      //Locate "submit" button, highlight it and click
      WebElement submit=driver.findElement(By.xpath("//div[1]/div[4]/div[3]/button[1]"));
      HighlightElement.highlightElement(driver, submit);
      submit.click(); 

      //Switch to alert
      Alert alert=driver.switchTo().alert();
      
      Thread.sleep(4000);
      
      //Get the text from alert box
      String s=alert.getText(); 
      
      //Click 'ok' in the alert box       
      alert.accept();  

      //Store the error in array and print on console
      error[0]="Error message for submitting form without any phone number: "+s;
      System.out.println(error[0]);
      
       

       

      //TEST:2 give less than 10 digits in mobile number section 
      
      //Locate "Mobile No" textbox, highlight it and enter the invalid value
      WebElement iv=driver.findElement(By.id("fmb0"));
      HighlightElement.highlightElement(driver, iv);
      iv.sendKeys(invalid1); 
      
      //Highlight the "submit" button and click it 
      HighlightElement.highlightElement(driver, submit);
      submit.click();
      
      //Locate the error message and store it in a String variable
      String s1=driver.findElement(By.xpath("//span[@id='fmb0Err']")).getText(); 
      
      //Decide the filePath and capture Screenshot
      filePath=System.getProperty("user.dir")+"/Screenshots/errorScreenshot1.png";
      cst.captureScreenshot(driver, filePath);
      TestNG.test.log(LogStatus.INFO, "Error Screenshot 1:"+TestNG.test.addScreenCapture(filePath));
      
    //Store the error in array and print on console
      error[1]="Error message by giving a number less than 10 digits:"+s1;
      
      System.out.println(error[1]);
      
    //Implement wait for 10 seconds
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 

       

       

      //TEST:3 give a 10 digit number which not starts with 6,7,8 or 9 
      
      //clear the section to input new value 
      iv.clear(); 
      
      //Highlight the box and enter invalid value
      HighlightElement.highlightElement(driver, iv);
      iv.sendKeys(invalid2);
      
      //highlight "submit" button and click
      HighlightElement.highlightElement(driver, submit);
      submit.click(); 
      
    //Locate the error message and store it in a String variable
      String s2=driver.findElement(By.xpath("//span[@id='fmb0Err']")).getText(); 
      
    //Decide the filePath and capture Screenshot
      filePath=System.getProperty("user.dir")+"/Screenshots/errorScreenshot2.png";
      cst.captureScreenshot(driver, filePath);
      TestNG.test.log(LogStatus.INFO, "Error Screenshot 2:"+TestNG.test.addScreenCapture(filePath));
      
    //Store the error in array and print on console
      error[2]="Error message for a number not starting with 6, 7, 8 or 9:"+s2;
      System.out.println(error[2]);
      
    //Implement wait for 10 seconds
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 

       

       

      //TEST:4 give character values instead of numeric values 

    //clear the section to input new value 
      iv.clear(); 
      
    //Highlight the box and enter invalid value
      HighlightElement.highlightElement(driver, iv);
      iv.sendKeys(invalid3); 
      
    //highlight "submit" button and click
      HighlightElement.highlightElement(driver, submit);
      submit.click(); 

    //Locate the error message and store it in a String variable
      String s3=driver.findElement(By.xpath("//span[@id='fmb0Err']")).getText(); 
      
    //Decide the filePath and capture Screenshot
      filePath=System.getProperty("user.dir")+"/Screenshots/errorScreenshot3.png";
      cst.captureScreenshot(driver, filePath);
      TestNG.test.log(LogStatus.INFO, "Error Screenshot 3:"+TestNG.test.addScreenCapture(filePath));
      
    //Store the error in array and print on console
      error[3]="Error message after inserting character value:"+s3;
      System.out.println(error[3]);

    //Implement wait for 10 seconds
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 

       

       

      //TEST:5 give a number which matches the appropriate criteria 

    //clear the section to input new value 
      driver.findElement(By.id("fmb0")).clear(); //clear the section to input new value 

    //Highlight the box and enter invalid value
      driver.findElement(By.id("fmb0")).sendKeys(valid); 
      
    //Implement wait for 10 seconds
      wait=new WebDriverWait(driver,10); 
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Free Listing"))); 

      //Send all the errors to excel file
      ExcelData.writeExcel(error);
  } 

} 