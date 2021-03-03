package com.identifyCarWashServices;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//To capture screenshot
public class CaptureScreenshot {
	public void captureScreenshot(WebDriver driver,String filePath){
		//take the screenshot
		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//copy the file to a location and use try catch block
		try {
			FileUtils.copyFile(screenshot, new File(filePath));
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	
	}

}
