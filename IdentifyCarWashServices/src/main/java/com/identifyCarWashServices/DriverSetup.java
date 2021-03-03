package com.identifyCarWashServices;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverSetup {
	//Declaring the static variable
	private static WebDriver driver;
	
	//Crating invokeDriver method which will take browserName as a parameter
	public static WebDriver setUpDriver(String browserName) {
		
		//Invoking Chrome Driver in WindowsOS
		if (browserName.equalsIgnoreCase("Chrome")) {
			String chromeDriverPath = "\\Drivers\\chromedriver.exe";
			String path=System.getProperty("user.dir");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", path+chromeDriverPath);
			
			driver =new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			
		}
		//Invoking Firefox Driver in WindowsOS
		else if (browserName.equalsIgnoreCase("Firefox")) {
			String firefoxDriverPath="\\Drivers\\geckodriver.exe";
			String path= System.getProperty("user.dir");
			System.setProperty("webdriver.gecko.driver", path+firefoxDriverPath);
			
			driver= new FirefoxDriver();	
		}
		//Invoking Opera Driver in WindowsOS
		else if(browserName.equalsIgnoreCase("Opera")) {
			String operaDriverPath="\\Drivers\\operadriver.exe";
			String path= System.getProperty("user.dir");
			System.setProperty("webdriver.opera.driver", path+operaDriverPath);
			
			driver= new OperaDriver();
		}
		
		//Returning the Driver
		return driver;
	}
	
}