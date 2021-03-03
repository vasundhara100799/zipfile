package com.identifyCarWashServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.TestNG;



 

public class Fitness {

     static WebDriver driver;
        static String filePath=null;
        static CaptureScreenshot cst=new CaptureScreenshot();
        
      //scrolling window to see available options      
        public static void scroll(WebDriver driver)
        {
            //implement JavascriptExecutor
            JavascriptExecutor jse=((JavascriptExecutor)driver);
            jse.executeScript("window.scrollBy(0,1000)", "");
            
        }
        
        //To select Fitness option from the list
        public static void selectFitnessOption(WebDriver driver) throws InterruptedException, Exception
        {
        	//Implement wait for 1 minute	
              driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
              
              //select "Fitness" option from list and highlight it
              WebElement fit=driver.findElement(By.id("ContextualHotkey_27"));
              HighlightElement.highlightElement(driver, fit);
              
            //Decide the filePath and capture Screenshot
              filePath=System.getProperty("user.dir")+"/Screenshots/FitnessScreenshot.png";
              HighlightElement.highlightElement(driver, fit);
              cst.captureScreenshot(driver, filePath);
              TestNG.test.log(LogStatus.INFO, "Fitness Screenshot:"+TestNG.test.addScreenCapture(filePath));
              
              //Click on "Fitness" button
              fit.click();
        }
        
        //To select Gym option from the list
        public static void selectGymOption(WebDriver driver) throws InterruptedException, Exception
        {
        	//Implement wait for 1 minute	
        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES); 
        	
            //get required data from KeywordDriven class
            String gym=KeywordDriven.getGymOption();
            
            //Select "Gym" from list and highlight it
            WebElement g=driver.findElement(By.xpath(gym));
            HighlightElement.highlightElement(driver, g);
            
          //Decide the filePath and capture Screenshot
            filePath=System.getProperty("user.dir")+"/Screenshots/GymScreenshot.png";
            HighlightElement.highlightElement(driver, g);
            cst.captureScreenshot(driver, filePath);
            TestNG.test.log(LogStatus.INFO, "Gym Screenshot:"+TestNG.test.addScreenCapture(filePath));
            
            //click on gym option
            g.click();
        }
        
        //print list of "Gym" sub-menus on console
        public static void displayMenus(WebDriver driver) throws Exception {
        	//Decide the filePath and capture Screenshot
        	filePath=System.getProperty("user.dir")+"/Screenshots/SubItemsScreenshot.png";
        	cst.captureScreenshot(driver, filePath);
        	TestNG.test.log(LogStatus.INFO, "Fitness Screenshot:"+TestNG.test.addScreenCapture(filePath));
        	
        	//Implement wait for 1 minute	
        	driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES); 
        	
        	//get required data from KeywordDriven class
        	String menu=KeywordDriven.getMenuList();
        	
        	//Locate the sub menu list and store it in a String variable
            String menu_list=driver.findElement(By.xpath(menu)).getText();
            ArrayList<String> menus=new ArrayList<String>();
            
            //Add all sub menu items in the list "menus"
            Collections.addAll(menus, menu_list.split("\n"));
            
            //Printing the sub menu items on console
                for(int i =0; i< menus.size(); i++){
                System.out.println((i+1)+" "+menus.get(i));
            }
            
            //Sending the list of sub menu items to excel file
            ExcelData.writeExcel(menus);
        }
        
}