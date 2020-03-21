package wepPages;

import org.openqa.selenium.support.PageFactory;

import utilites.base;

public class managePages extends base
{
 public static void init()
 {
	 HP = PageFactory.initElements(driver, wepPages.homePage.class);
		

 }
}
