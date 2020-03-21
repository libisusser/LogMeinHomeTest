package utilites;

import org.openqa.selenium.WebElement;
import static org.junit.Assert.fail;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;


import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class commonOps extends base
{

	// There are try and catch after every action - both of then write to the report.
	// There is a fail action in every catch so the test will fail and end if there is any exception.
	// The Report will displayed a screen shot in case there is any exception.



	// Click method
	public static void clickOrChose(WebElement elem, String string) throws ParserConfigurationException, SAXException, IOException

	{
		try
		{

			elem.click();
			System.out.println(string + " " + "is clicked");
			test.log(LogStatus.PASS, string + " " + "is clicked"); //The string tell more information in the repot abot the element that was failed.
		}

		catch (Exception e)
		{
			System.out.println(string + " " + "not clicked!");
			test.log(LogStatus.FAIL, string + " " + "not clicked! + see Screen Shot: " + test.addScreenCapture(takeSS()));
			fail (string + " " + "not clicked!");
		}
	}

	// Verify Attribute Value for assert the the value is displayed
	public static void verifyValue(WebElement elem,  String expectedValue, String string, String value) throws ParserConfigurationException, SAXException, IOException

	{
		try
		{
			String actual = elem.getAttribute("value");
			assertEquals(expectedValue, actual);

			System.out.println(value + " " + " is OK!");
			test.log(LogStatus.PASS, value + " " + " is OK!");
		}
		catch (Exception e)
		{
			System.out.println(string + " " + " NOT Exist!"+ e.getMessage());
			test.log(LogStatus.FAIL, string + " " + " NOT Exist! + see Screen Shot: " +  e.getMessage() +  test.addScreenCapture(takeSS()));
			fail (string +" NOT Exist!");
		}

		catch (AssertionError ea)
		{
			System.out.println(value + " " + "is not OK!" + ea.getMessage());
			test.log(LogStatus.FAIL, value + " " + " is not OK! + see Screen Shot: " + ea.getMessage() + test.addScreenCapture(takeSS()));
			fail (value + " " + " is not OK!");
		}


	}

	// Verify Attribute Value for assert the the value is Not displayed
	public static void verifyValueNotEquals(WebElement elem,  String expectedValue, String string, String value) throws ParserConfigurationException, SAXException, IOException

	{
		try
		{
			String actual = elem.getAttribute("value");
			assertNotEquals(expectedValue, actual);

			System.out.println(value + " " + " is OK!");
			test.log(LogStatus.PASS, value + " " + " is OK!");
		}
		catch (Exception e)
		{
			System.out.println(string + " " + " NOT Exist!"+ e.getMessage());
			test.log(LogStatus.FAIL, string + " " + " NOT Exist! + see Screen Shot: " +  e.getMessage() +  test.addScreenCapture(takeSS()));
			fail (string +" NOT Exist!");
		}

		catch (AssertionError ea)
		{
			System.out.println(value + " " + "is not OK!" + ea.getMessage());
			test.log(LogStatus.FAIL, value + " " + " is not OK! + see Screen Shot: " + ea.getMessage() + test.addScreenCapture(takeSS()));
			fail (value + " " + " is not OK!");
		}


	}

	// Verify list size - verify that the list size is as expceted
	public static void verifyListSize(List<WebElement>elements, int expectedValue, String string, String text) throws ParserConfigurationException, SAXException, IOException

	{
		try
		{
			int actual = elements.size();
			assertEquals(expectedValue, actual);



			System.out.println(text + " " + " is OK!");
			test.log(LogStatus.PASS, text + " " + " is OK!");
		}
		catch (Exception e)
		{
			System.out.println(string + " " + " NOT Exist!"+ e.getMessage());
			test.log(LogStatus.FAIL, string + " " + " NOT Exist! + see Screen Shot: " +  e.getMessage() +  test.addScreenCapture(takeSS()));
			fail (string +" NOT Exist!");
		}

		catch (AssertionError ea)
		{
			System.out.println(text + " " + "is not OK!" + ea.getMessage());
			test.log(LogStatus.FAIL, text + " " + " is not OK! + see Screen Shot: " + ea.getMessage() + test.addScreenCapture(takeSS()));
			fail (text + " " + " is not OK!");
		}


	}

	//Wait method that wait until the Attribute Value is displayed in the element. This method using loop statement that test every 1 sec if the value is displayed and stop the loop after is displayed or after the max time is finished
	public static void  waitForValuePresent(WebElement elem,String expectedValue, int timeOutInMilSeconds, int totalTimeInMilSeconds, String string) throws InterruptedException, ParserConfigurationException, SAXException, IOException 

	{
		int loop;
		loop = totalTimeInMilSeconds/timeOutInMilSeconds;
		Exception last = null;
		AssertionError lastError = null;

		for(int i=1; i<=loop; i++)
		{
			try
			{

				String actual = elem.getAttribute("value");

				String str2 = expectedValue;

				Boolean a = actual.toLowerCase().contains(str2.toLowerCase());

				if (a == true)
				{

					System.out.println(string + " " +" found after " + ((i-1)*timeOutInMilSeconds/1000 + " seconds"));
					return ;
				}

				else
				{


					Thread.sleep(timeOutInMilSeconds);
				}


			}

			catch (Exception e) {
				System.out.println("Wait for" + string + " " + " present failed after " + totalTimeInMilSeconds/1000 + " seconds");
				test.log(LogStatus.FAIL, "Wait for" + string + " " + " present failed after " + totalTimeInMilSeconds/1000 + " seconds" + last.getMessage() + test.addScreenCapture(takeSS()));
				fail("Wait for" + string + " " + " present failed after " + totalTimeInMilSeconds/1000 + " seconds");
				last = e;
			} 

		} 

	}


	/* 
  A method that performs a mathematical operation on two numbers (including button presses) 
  and returns the sum as a variable that transforms from a integer to a string so that it can 
  be used in the following steps in the test.
  There are try and catch after every Selenium action and one for the whole method 
	 */

	public static String calculateOfTowInteger(WebElement elem1, String string1,WebElement elem2,String string2,WebElement elem3,String string3,WebElement elem4,String string4,int num1,char operator,int num2) throws ParserConfigurationException, SAXException, IOException

	{
		int sum = 0;
		try
		{
			try{
				commonOps.clickOrChose(HP.BtnClear,"BtnClear");
				System.out.println("BtnClear is clicked");
				test.log(LogStatus.PASS, "BtnClear is clicked");

			}

			catch (Exception e)
			{
				System.out.println("BtnClear not clicked!");
				test.log(LogStatus.FAIL, "BtnClear not clicked! + see Screen Shot: " + test.addScreenCapture(takeSS()));
				fail ("BtnClear not clicked!");
			}



			try
			{
				elem1.click();
				System.out.println(string1 + " " + "is clicked");
				test.log(LogStatus.PASS, string1 + " " + "is clicked");

			}

			catch (Exception e)
			{
				System.out.println(string1 + " " + "not clicked!");
				test.log(LogStatus.FAIL, string1 + " " + "not clicked! + see Screen Shot: " + test.addScreenCapture(takeSS()));
				fail (string1 + " " + "not clicked!");
			}

			try
			{
				elem2.click();
				System.out.println(string2 + " " + "is clicked");
				test.log(LogStatus.PASS, string2 + " " + "is clicked");
			}
			catch (Exception e)
			{
				System.out.println(string2 + " " + "not clicked!");
				test.log(LogStatus.FAIL, string2 + " " + "not clicked! + see Screen Shot: " + test.addScreenCapture(takeSS()));
				fail (string2 + " " + "not clicked!");
			}
			
			try 
			{
				elem3.click();
				System.out.println(string3 + " " + "is clicked");
				test.log(LogStatus.PASS, string3 + " " + "is clicked");
			}
			catch (Exception e)
			{
				System.out.println(string3 + " " + "not clicked!");
				test.log(LogStatus.FAIL, string3 + " " + "not clicked! + see Screen Shot: " + test.addScreenCapture(takeSS()));
				fail (string3 + " " + "not clicked!");
			}
			
			try 
			{

				elem4.click();
				System.out.println(string4 + " " + "is clicked");
				test.log(LogStatus.PASS, string4 + " " + "is clicked");
			}
			catch (Exception e)
			{
				System.out.println(string4 + " " + "not clicked!");
				test.log(LogStatus.FAIL, string4 + " " + "not clicked! + see Screen Shot: " + test.addScreenCapture(takeSS()));
				fail (string4 + " " + "not clicked!");
			}


			switch(operator)
			{
			case '+':
				sum = num1 + num2;
				break;

			case '-':
				sum = num1 - num2;
				break;

			case '*':
				sum = num1 * num2;
				break;

			case '/':
				sum = num1 / num2;
				break;

				/* If user enters any other operator or char apart from
				 * +, -, * and /, then display an error message to user
				 * 
				 */
			default:
				System.out.printf("You have entered wrong operator");
				test.log(LogStatus.FAIL, "You have entered wrong operator" + " " + "not clicked! + see Screen Shot: " + test.addScreenCapture(takeSS()));
				fail ("You have entered wrong operator" + " " + "not clicked!");


			}



		}

		catch (Exception e)
		{
			System.out.println("You have entered wrong operator" + " " + "not clicked!");
			test.log(LogStatus.FAIL, "You have entered wrong operator" + " " + "not clicked! + see Screen Shot: " + test.addScreenCapture(takeSS()));
			fail ("You have entered wrong operator" + " " + "not clicked!");
		}
	
		String sumAsString = Integer.toString(sum);
		return sumAsString;
		}
	

}













