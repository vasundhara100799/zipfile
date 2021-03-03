package com.identifyCarWashServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	public static String inputData[]=new String[7];
			//XSSFWorkbook for writing data to excel
			public static XSSFWorkbook workbook = new XSSFWorkbook();
			//XSSFSheet for writing data to excel
			public static XSSFSheet sheet = workbook.createSheet("Output");
	
			//Reading the data from the excel
	public static String[] readExcelData(String sheetName) throws Exception {

		//Excelfile path
		String workingDir=System.getProperty("user.dir");
		String excelFilePath=workingDir+File.separator+"ExcelInput.xlsx";
		//Excel file
		FileInputStream excelFile=new FileInputStream(excelFilePath);
		//XSSFWorkbook
		XSSFWorkbook workbook1 = new XSSFWorkbook(excelFile);
		//XSSFSheet
		XSSFSheet sheet=workbook1.getSheet(sheetName);
		//XSSFRow
		XSSFRow row=sheet.getRow(0);
		
		for(int i=0;i<7;i++)
		{
			//XSSFCell
			XSSFCell cell = row.getCell(i);
			//DataFormatter
			DataFormatter dataFormatter = new DataFormatter();
			 dataFormatter.formatCellValue(cell);
			inputData[i]=dataFormatter.formatCellValue(cell);
		}
		//Close the workbook
		workbook1.close();
		
		//Returning the String array storing the excel data
		return inputData;
	}

	//To write data to excel
	public static void writeExcel(List<String> nameList,List<String> contactList) {
		
		//Create row
		Row row1 = sheet.createRow(0);
		
		//Create cell
		Cell cell = row1.createCell(0);
		cell.setCellValue("5 Car Washing Services");
		for(int i = 1;i<=nameList.size();i++) {
			//Create rows
			Row row = sheet.createRow(i);
			
			//Create cells
			Cell cell1 = row.createCell(0);
			Cell cell2= row.createCell(1);
			
			//Set the cell values from the lists
			cell1.setCellValue(nameList.get(i-1));
			cell2.setCellValue(contactList.get(i-1));
		}
	}
	
	
	//To write data to excel
	public static void writeExcel(ArrayList<String> arr)
	{
		//Writing the data
		Row row1 = sheet.createRow(15);
		Cell cell = row1.createCell(0);
		cell.setCellValue("Sub-menu items from \"Gym\" Menu");
		for(int i=16,j=0;j<arr.size();i++,j++) {
			//Create rows
			Row row=sheet.createRow(i);

			//Create cells
			Cell cell1=row.createCell(0);
			
			//Set the cell values from the lists
			cell1.setCellValue(arr.get(j));
		}
		try {
			
			//Create FileOutputStream object
			FileOutputStream file = new FileOutputStream("ExcelOutput.xlsx");
			
			//write the data in workbook
			workbook.write(file);
			
			//close the workbook
			workbook.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//To write data to excel
	public static void writeExcel(String[] error) {
				//Writing the data
				Row row1 = sheet.createRow(9);
				
				//Create cell
				Cell cell = row1.createCell(0);
				
				cell.setCellValue("Errors for phone number field");
				for(int i=10,j=0;j<error.length;i++,j++) {
					//Create rows
					Row row=sheet.createRow(i);
					
					//Create cells
					Cell cell1=row.createCell(0);
					
					//Set the cell values from the lists
					cell1.setCellValue(error[j]);
				}

	}
	
}
