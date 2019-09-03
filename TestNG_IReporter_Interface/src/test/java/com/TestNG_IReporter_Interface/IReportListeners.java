package com.TestNG_IReporter_Interface;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite;

public class IReportListeners extends TestClass implements IReporter

{
	public static String filePath_fail = System.getProperty("user.dir")+"\\src\\test\\java\\com\\TestNG_ITestListeners_FailedScreenshots\\";
	public static String reportsPath_fail="";
	
	public static String filePath_success = System.getProperty("user.dir")+"\\src\\test\\java\\com\\TestNG_ITestListeners_SuccessScreenshots\\";
	public static String reportsPath_success="";
	
	public static String filePath_skip = System.getProperty("user.dir")+"\\src\\test\\java\\com\\TestNG_ITestListeners_SkippedScreenshots\\";
	public static String reportsPath_skip="";
	
	public static String methodName;

	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String outputDirectory) 
	{
		// Second parameter of this method ISuite will contain all the suite executed.

        for (ISuite iSuite : arg1)
        {
        	 //Get a map of result of a single suite at a time

            Map<String,ISuiteResult> results =    iSuite.getResults();
            
            //Get the key of the result map

            Set<String> keys = results.keySet();
            
            //Go to each map value one by one

            for (String key : keys)
            {
            	//The Context object of current result

                ITestContext result = results.get(key).getTestContext();

                //Print Suite detail in Console

                 System.out.println("Suite Name->"+result.getName()

                        + "::Report output Ditectory->"+result.getOutputDirectory()

                         +"::Suite Name->"+ result.getSuite().getName()

                         +"::Start Date Time for execution->"+result.getStartDate()

                         +"::End Date Time for execution->"+result.getEndDate());

                

                 //Get Map for only failed test cases

                IResultMap fail = result.getFailedTests();

                //Get method detail of failed test cases

                Collection<ITestNGMethod> failedMethods = fail.getAllMethods();

                //Loop one by one in all failed methods

                System.out.println("--------FAILED TEST CASE---------");

			                for (ITestNGMethod iTestNGMethod : failedMethods)
			                {
			
			                    //Print failed test cases detail
			
			                    System.out.println("TESTCASE NAME->"+iTestNGMethod.getMethodName()
			
			                            +"\nDescription->"+iTestNGMethod.getDescription()
			
			                            +"\nPriority->"+iTestNGMethod.getPriority()
			
			                            +"\n:Date->"+new Date(iTestNGMethod.getDate()));
			
			                    
			
			                }
			                
			                
	      //Get Map for only pass test cases
			                
			         IResultMap  pass=result.getPassedTests();
			         
			         Collection<ITestNGMethod> passMethods=pass.getAllMethods();
			         
			         System.out.println("------PASS TEST CASE-------");
			         for(ITestNGMethod iTestNGMethod : passMethods)
			         {
			        	 System.out.println("TESTCASE NAME->"+iTestNGMethod.getMethodName()
			
			                            +"\nDescription->"+iTestNGMethod.getDescription()
			
			                            +"\nPriority->"+iTestNGMethod.getPriority()
			
			                            +"\n:Date->"+new Date(iTestNGMethod.getDate()));
			         }
			         
		//Get Map for only skip test cases
			         
			         IResultMap  skip=result.getSkippedTests();
			         
			         Collection<ITestNGMethod> skipMethods=skip.getAllMethods();
			         
			         System.out.println("------SKIP TEST CASE-------");
			         for(ITestNGMethod iTestNGMethod : skipMethods)
			         {
			        	 System.out.println("TESTCASE NAME->"+iTestNGMethod.getMethodName()
			
			                            +"\nDescription->"+iTestNGMethod.getDescription()
			
			                            +"\nPriority->"+iTestNGMethod.getPriority()
			
			                            +"\n:Date->"+new Date(iTestNGMethod.getDate()));
			         }
			         

            	}

            
        
        
        
        }
		
	}
	
	public static String TakeFailedScreenShot(String methodName)
	{
	   	
	   	
	   	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        //The below method will save the screen shot in d drive with test method name 
	           try 
	           {
	           	reportsPath_fail=filePath_fail+methodName+"-"+timestamp()+".png";
	           	
					FileUtils.copyFile(scrFile, new File(reportsPath_fail));
					
					System.out.println("***Placed screen shot in "+filePath_fail+" ***");
				} 
	           catch (IOException e) 
	           {
					e.printStackTrace();
				}
				return reportsPath_fail;
	   }

	//Success test case take screen shots

	public static String TakeSuccessScreenShot(String methodName)
	{
	   	
	   	
	   	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        //The below method will save the screen shot in d drive with test method name 
	           try 
	           {
	           	reportsPath_success=filePath_success+methodName+"-"+timestamp()+".png";
	           	
					FileUtils.copyFile(scrFile, new File(reportsPath_success));
					
					System.out.println("***Placed screen shot in "+filePath_success+" ***");
				} 
	           catch (IOException e) 
	           {
					e.printStackTrace();
				}
				return reportsPath_success;
	   }

	//Skipped test case take screen shots

	public static String TakeSkippedScreenShot(String methodName)
	{
	 	
	 	
	 	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      //The below method will save the screen shot in d drive with test method name 
	         try 
	         {
	         	reportsPath_skip=filePath_skip+methodName+"-"+timestamp()+".png";
	         	
					FileUtils.copyFile(scrFile, new File(reportsPath_skip));
					
					System.out.println("***Placed screen shot in "+filePath_skip+" ***");
				} 
	         catch (IOException e) 
	         {
					e.printStackTrace();
				}
				return reportsPath_skip;
	 }



	private static String timestamp() 
	{
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}




}
