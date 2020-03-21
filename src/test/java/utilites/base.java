package utilites;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.Screen;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import wepPages.sendMail;

public class base 

{
	public static WebDriver driver;
	public static Screen screen;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
	public static String ReportFullPathName;

	public static wepPages.homePage HP; // Page model






	public static String getData (String nodeName) throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = new File("./config.xml"); // xml config located in the project



		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // The project is with page factory
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile); 
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(nodeName).item(0).getTextContent();
	}

	public static void initBrowser(String browserType) throws ParserConfigurationException, SAXException, IOException // Browsers
	{
		switch (browserType.toLowerCase())
		{
		case "firfox":
			driver = initFFDriver();
			break;
		case "ie":
			driver = initIEDriver();
			break;
		case "chrome":
			driver = initChromeDriver();
			break;


		}

		driver.manage().window().setSize(new Dimension(1366, 768)); // Window size to display the app
		driver.get(getData("URL")); // The app url in the xml config
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // wait for a 10 sec before throwing an exception. Once this time is set, WebDriver will wait for the element before the exception occurs. Once the command is in place, Implicit Wait stays in place for the entire duration for which the browser is open.
		screen = new Screen(); //Open a new screen


	}
    /* initialize the borowsers */
	public static WebDriver initFFDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty ("webdriver.gecko.driver" , getData ("FFDriverPath"));
		WebDriver driverff = new FirefoxDriver();
		return driverff;

	}


	public static WebDriver initChromeDriver() throws ParserConfigurationException, SAXException, IOException
	
	{
		System.setProperty ("webdriver.chrome.driver" , getData ("ChromeDriverPath"));
		WebDriver driver = new ChromeDriver();
		return driver;

	}
	
	public static WebDriver initIEDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty ("webdriver.ie.driver" , getData ("IEDriverPath"));
		WebDriver driverIe = new InternetExplorerDriver();
		return driverIe;

	}

	/* initialize the report - Extend Report */
	public static void InstanceReport(String currentTime) throws ParserConfigurationException, SAXException, IOException
	
	{
		ReportFullPathName = getData("ReportFilePath")+ getData("ReportFileName") + currentTime + ".html";
		extent = new ExtentReports(getData("ReportFilePath")+ getData("ReportFileName") + currentTime + ".html",false);


	}

	public static void initReportTest(String testName, String testDescription)

	{
		test = extent.startTest(testName,testDescription);
	}

	public static void finalizeReportTest()
	{
		extent.endTest(test);
	}

	public static void finalizeReport()
	{
		extent.flush();
		extent.close();
	}

	// Screen shot is displayed in the report in case there is a failure
	public static String takeSS() throws ParserConfigurationException, SAXException, IOException

	{

		String SSpath = getData("ReportFilePath") + "screenshot_" + getRandomNumber() + ".png";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(SSpath));
		return SSpath;

	}

	public static int getRandomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(999999) + 1000;
	}

	@BeforeClass

	public static void  startsSession() throws ParserConfigurationException, SAXException, IOException

	{


		initBrowser(getData("BrowserType"));
		InstanceReport(timeStamp); // Report time stamp
		wepPages.managePages.init();



	}

	@Before //How the test will be displayed in the report

	public void doBeforeTest() throws ParserConfigurationException, SAXException, IOException
	{

		initReportTest(name.getMethodName().split("_")[0],name.getMethodName().split("_")[1]);
	}

	@Rule public TestName name = new TestName();

	
	@After


	public void doAfterTest()
	{

		finalizeReportTest(); // The report after every test

	}


	@AfterClass
	public static void  CloseSession()
	{
		finalizeReport(); //The report is closed after all the tests in the class was done
		driver.quit(); // The driver is closed after all the tests in the class was done
		sendMail.sendingMail("LogMeinHomeTest", ReportFullPathName); // the report is send by email after all the tests in the class was done
	}




}
