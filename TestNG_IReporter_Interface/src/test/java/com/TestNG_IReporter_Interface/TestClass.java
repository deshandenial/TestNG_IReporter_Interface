package com.TestNG_IReporter_Interface;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Listeners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;


@Listeners(IReportListeners.class)
public class TestClass
{
	static WebDriver driver;
	public static String methodName;
  @Test
  public void Test1() 
  {
	  driver.get("https://github.com");
	  driver.findElement(By.xpath("//input[@id='user[login]']")).sendKeys("abcd");
	  driver.findElement(By.xpath("//input[@id='user[email]']")).sendKeys("ssss@gmail.com");
	  driver.findElement(By.xpath("//input[@id='user[password]']")).sendKeys("abcd@123");
	  driver.findElement(By.xpath("//div[@class='rounded-1 text-gray bg-gray-light py-4 px-4 px-md-3 px-lg']//button[contains(text(),'Sign up for GitHub')]")).click();
	  
	  
  }
  
  @Test
  public void Test2() 
  {
	  driver.get("https://www.linkedin.com/");
	  driver.findElement(By.xpath("//a[contains(text(),'Join now')]")).click();
	  driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("desone");
	  driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("deshan");
	  driver.findElement(By.xpath("//input[@id='join-email']")).sendKeys("deshan@123");
	  
	  
  }
  
  
  @Test
  public void Test3() 
  {
	  driver.get("https://www.linkedin.com/");
	  driver.findElement(By.xpath("//input[@name='session_key']")).sendKeys("");
	  driver.findElement(By.xpath("//input[@name='session_password']")).sendKeys("");
	  
	  
	  
  }
  

  @BeforeTest
  public void beforeTest()
  {
	 System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
	 ChromeOptions option=new ChromeOptions();
	 option.addArguments("----disable-notification----");
	 driver=new ChromeDriver(option);
	 driver.manage().window().maximize();
  }

 
@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
		
			
			
			
			System.out.println("******ERROR****"+result.getName()+"***TEST HAS FAILED*******");
			methodName=result.getName().toString().trim();
			
			System.out.println("==================================");
			
			String failed_screenshot_path=IReportListeners.TakeFailedScreenShot(result.getName());
			
			System.out.println("==================================");
		}
		else 
			
		if(result.getStatus()==ITestResult.SUCCESS) 
		{
			
			System.out.println("******SUCCESS****"+result.getName()+"***TEST HAS SUCCESS*******");
			methodName=result.getName().toString().trim();
			
			System.out.println("==================================");
			
			String success_screenshot_path=IReportListeners.TakeSuccessScreenShot(result.getName());
			
			System.out.println("==================================");
			
		}
		else
		if(result.getStatus()==ITestResult.SKIP)
		{
			
			System.out.println("******SKIPPED****"+result.getName()+"***TEST HAS SKIPPED*******");
			methodName=result.getName().toString().trim();
			
			System.out.println("==================================");
			
			String success_screenshot_path=IReportListeners.TakeSuccessScreenShot(result.getName());
			
			System.out.println("==================================");
		}
	
	} 
  @AfterTest
  public void afterTest() 
  {
	  driver.close();
  }



}
