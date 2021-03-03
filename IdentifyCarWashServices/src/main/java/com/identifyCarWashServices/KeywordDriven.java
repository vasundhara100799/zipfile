package com.identifyCarWashServices;

public class KeywordDriven {
	
	public static String HomepageUrl() {
		return "https://www.justdial.com/";
	}
	
	//Car Washing Services
	public static String getLocation() {
		String city="https://www.justdial.com/";
		return city;
	}
	
	public static String getSearchBox() {
		String searchBox="//input[@id='srchbx']";
		return searchBox;
	}
	
	public static String getSearchBtn() {
		String searchBtn="//span[@id='srIconwpr']";
		return searchBtn;
	}
	
	public static String getServiceAtHome() {
		String homestr="//a[contains(text(),'Car Washing Services At Home near me')]";
		return homestr;
	}
	
	public static String getServiceNearMe() {
		String nearMe="//a[contains(text(),'Car Washing Services near me')]";
		return nearMe;
	}
	
	public static String getRatingFilter() {
		String ratings="//a[@id='distdrop_rat']";
		return ratings;
	}
	
	//reading data from InputExcel.xlsx file
	public static String getService() throws Exception {
		String[] data=ExcelData.readExcelData("Car Washing");
		String service=data[0];
		return service;
	}
	
	public static String getCompany() throws Exception {
		String[] data=ExcelData.readExcelData("Free Listing");
		String company=data[0];
		return company;
	}
	
	public static String getFirstName() throws Exception{
		String[] data=ExcelData.readExcelData("Free Listing");
		String fname=data[1];
		return fname;
	}
	
	public static String getLastName() throws Exception{
		String[] data=ExcelData.readExcelData("Free Listing");
		String lname=data[2];
		return lname;
	}
	
	public static String getInvalidContact1() throws Exception{
		String[] data=ExcelData.readExcelData("Free Listing");
		String invalid=data[3];
		return invalid;
	}
	
	public static String getInvalidContact2() throws Exception{
		String[] data=ExcelData.readExcelData("Free Listing");
		String invalid=data[4];
		return invalid;
	}
	
	public static String getInvalidContact3() throws Exception{
		String[] data=ExcelData.readExcelData("Free Listing");
		String invalid=data[5];
		return invalid;
	}
	
	public static String getValidContact() throws Exception{
		String[] data=ExcelData.readExcelData("Free Listing");
		String valid=data[6];
		return valid;
	}

	//Fitness
	
	public static String getGymOption() {
		String gym="//body/div[@id='setbackfix']/div[2]/div[1]/div[2]/ul[1]/li[3]/a[1]";
		return gym;
	}
	
	public static String getMenuList() {
		String menuList="//*[@class='mm-listview']";
		return menuList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
