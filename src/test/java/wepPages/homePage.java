package wepPages;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.LogStatus;

import utilites.base;

public class homePage extends base

{
	public WebDriver driver;
	
	// Identify the relevant elements for the test in the home page

	@FindBy(how = How.ID, using = ("Btn0"))
	public WebElement Btn0 ;

	@FindBy(how = How.ID, using = ("Btn1"))
	public WebElement Btn1 ;
	
	@FindBy(how = How.ID, using = ("Btn2"))
	public WebElement Btn2 ;
	
	@FindBy(how = How.ID, using = ("Btn3"))
	public WebElement Btn3 ;
	
	@FindBy(how = How.ID, using = ("Btn10"))
	public WebElement Btn10 ;

	@FindBy(how = How.ID, using = ("BtnMinus"))
	public WebElement BtnMinus ;

	@FindBy(how = How.ID, using = ("BtnPlus"))
	public WebElement BtnPlus ;
	
	@FindBy(how = How.ID, using = ("BtnMult"))
	public WebElement BtnMult ;

	@FindBy(how = How.ID, using = ("BtnSin"))
	public WebElement BtnSin ;

	@FindBy(how = How.ID, using = ("BtnParanL"))
	public WebElement BtnParanL ;
	
	@FindBy(how = How.ID, using = ("BtnParanR"))
	public WebElement BtnParanR ;
	
	@FindBy(how = How.ID, using = ("BtnCalc"))
	public WebElement BtnCalc ;
	
	@FindBy(how = How.ID, using = ("input"))
	public WebElement resultWindow ;

	@FindBy(how = How.ID, using = ("cookieconsentallowall"))
	public WebElement acceptCookie ;

	@FindBy(how = How.ID, using = ("BtnClear"))
	public WebElement BtnClear ;
	
	@FindBy(how = How.CLASS_NAME, using = ("r"))
	public List<WebElement> histList	;





	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
		
		
	}

