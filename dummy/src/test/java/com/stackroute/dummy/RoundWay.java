package com.stackroute.dummy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RoundWay {
	
	 static WebDriver wd;
	 
	 
	 @BeforeClass
		public static void init()
		{
	    	WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
			wd = new ChromeDriver();
			
			wd.get("https://www.spicejet.com/");
		}
	    
	    
	    @DataProvider(name="testData1")
	    public Object[][] TestDataFeed()
	    {
	    	ReadExcelFile config = new ReadExcelFile("/home/ubuntu/Downloads/roundway.xlsx");
	    	int rows = config.getRowCount(0);
	    	
	    	Object[][] oneway = new Object[rows][4];
	    	for(int i=0; i<rows;i++)
	    	{
	    		oneway[i][0]= config.getData(0, i, 0);
	    		oneway[i][1]= config.getData(0, i, 1);
	    		oneway[i][2]= config.getData(0,i,2);
	    		oneway[i][3]= config.getData(0,i,3);
	    		
	    	}
	    	return oneway;
	    }
	    
	
	  @Test(dataProvider="testData1")
	    public void verifyLoginTest02(String[] data)
	    {
	    	System.out.println("In verify test case method");
	    	WebElement flightArrival;
	    	WebElement flightDestination;

	    	//Radio-one way
	    	wd.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();;
	    	//Arrival
	    	flightArrival=wd.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
	       flightArrival.sendKeys(data[0]);
	    	
	    	
	    	    
	    	 //Destination
	         wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    	
	         wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).clear();
	    	flightDestination=wd.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));
	    	flightDestination.sendKeys(data[1]);
	    	wd.findElement(By.linkText("Mumbai (BOM)")).click();
	    	

	    	//Date
	    	
		
		  WebElement FromDateWidget =
		  wd.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]"));
		  List<WebElement> columns1 = FromDateWidget.findElements(By.tagName("td"));
		  for(WebElement cell1: columns1) { if(cell1.getText().equals("25")) {
		  cell1.findElement(By.linkText("25")).click(); break; } }
		  
		 	
			//WebElement ToDateWidget = wd.findElement(By.id("ctl00_mainContent_txt_Todate"));
			//ToDateWidget.sendKeys("28-02-2021");
			//new WebDriverWait(wd, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='ui-datepicker-calendar']//tr//a[contains(@class,'ui-state-default') and contains(.,'12')]"))).click();
			
			
			//boolean status= wd.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]")).isEnabled();
			//System.out.println(status);
	    	
	    	  wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    	  
	    	//wd.findElement(By.xpath("//input[@id='ctl00_mainContent_view_date2']")).click();
	    	

	    	wd.findElement(By.id("divpaxinfo")).click();
		//	wd.findElement(By.id("divAdult")).click();
	    	Select AdultDropdown = new Select(wd.findElement(By.id("ctl00_mainContent_ddl_Adult")));
			//int no= Integer.parseInt(data[3]) ;
			AdultDropdown.selectByValue("2");
			
	        Select CurrencyDropdown = new Select(wd.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
			
			CurrencyDropdown.selectByValue("AED");

	    	
	    	/*
		 * WebElement ToDateWidget =
		 * wd.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]"));
		 * 
		 * List<WebElement> columns2 = ToDateWidget.findElements(By.tagName("td"));
		 * for(WebElement cell2: columns2) { if(cell2.getText().equals("26")) {
		 * cell2.findElement(By.linkText("26")).click(); break; } }
		 */	
			wd.findElement(By.id("ctl00_mainContent_btn_FindFlights")).submit();
	    	wd.quit();
		
	    }
	 

}
