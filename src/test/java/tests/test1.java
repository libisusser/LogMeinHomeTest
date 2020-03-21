package tests;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import org.junit.experimental.ParallelComputer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import utilites.base;
import utilites.commonOps;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;;

public class test1 extends base

{
	
	@Test
	public void test01_flow01() throws InterruptedException, AWTException, ParserConfigurationException, SAXException, IOException

	{
		commonOps.clickOrChose(HP.acceptCookie,"acceptCookie");
		
		// I used the generic Method for the first test in the flow
		String sumAsString = commonOps.calculateOfTowInteger(HP.Btn2, "Btn2",HP.BtnPlus, "BtnPlus", HP.Btn3, "Btn3", HP.BtnCalc, "BtnCalc", 2, '+', 3);
		commonOps.waitForValuePresent(HP.resultWindow, sumAsString, 1000, 5000, "sumAsString");
		commonOps.verifyValue(HP.resultWindow, sumAsString, "resultWindow", "sumAsString"); // This step is not really necessary because there is value assertion in the previous step. 
		//I added it anyway so you see another option in all the test. In test Third test it is necessary.
		
		//Second test
		commonOps.clickOrChose(HP.BtnClear,"BtnClear");
		commonOps.clickOrChose(HP.Btn1,"button_1");
		commonOps.clickOrChose(HP.Btn0,"button_0");
		commonOps.clickOrChose(HP.BtnMinus,"BtnMinus");
		commonOps.clickOrChose(HP.Btn2,"button_2");
		commonOps.clickOrChose(HP.BtnCalc,"BtnCalc");
		commonOps.waitForValuePresent(HP.resultWindow, "8", 1000, 5000, "sumAsString");
		commonOps.verifyValue(HP.resultWindow, "8", "resultWindow", "8");
		
		//Third test
		commonOps.clickOrChose(HP.BtnClear,"BtnClear");
		commonOps.clickOrChose(HP.BtnParanL,"BtnParanL");
		commonOps.clickOrChose(HP.Btn1,"button_1");
		commonOps.clickOrChose(HP.Btn0,"button_0");
		commonOps.clickOrChose(HP.BtnMinus,"BtnMinus");
		commonOps.clickOrChose(HP.Btn2,"button_2");
		commonOps.clickOrChose(HP.BtnParanR,"BtnParanR");
		commonOps.clickOrChose(HP.BtnMult,"BtnMult");
		commonOps.clickOrChose(HP.Btn2,"button_2");
		commonOps.clickOrChose(HP.BtnCalc,"BtnCalc");
		commonOps.waitForValuePresent(HP.resultWindow, "16", 1000, 5000, "sumAsString");
		commonOps.verifyValueNotEquals(HP.resultWindow, "20", "resultWindow", "20");
		
		//Fourth test
		commonOps.clickOrChose(HP.BtnClear,"BtnClear");
		commonOps.clickOrChose(HP.BtnSin,"BtnSin");
		commonOps.clickOrChose(HP.Btn3,"button_3");
		commonOps.clickOrChose(HP.Btn0,"button_0");
		commonOps.clickOrChose(HP.BtnCalc,"BtnCalc");
		commonOps.waitForValuePresent(HP.resultWindow, "0.5",1000, 5000, "resultWindow");
		commonOps.verifyValue(HP.resultWindow, "0.5", "resultWindow", "0.5");
		
		//Fifth test
		commonOps.verifyListSize(HP.histList, 4, "histList", "4");
		










	}
		
		
		/*
		commonOps.insertText(HP.serachField, "selenuBtnPlusim", "serachField");
		commonOps.clickOrChose(HP.searchTerm, "searchTerm");
		commonOps.clickOrChose(RP.searchCountry, "searchCountry");
		commonOps.insertText(RP.searchCountryText, "Israel", "searchCountryText");
		commonOps.clickOrChose(RP.searchTermCountry, "searchTermCountry");
		commonOps.scroll(); //for the screen shot for the report
		commonOps.assertTextInList(RP.subregionsList, "Tel Aviv", CP.relatedQueries, "Hmm, your search doesn't have enough data to show here.");
	
	*/


	
}




