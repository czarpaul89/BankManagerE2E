package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.baseClass;

public class loginTest extends baseClass {
	
	@Test
	public void Custlogin() throws Exception
	{
		if(!isDisplayedCSS(global.getProperty("CustLoginBTN").toString(),"CustLoginBTN"))
		{
			driver.findElement(By.cssSelector(global.getProperty("Home"))).click();
			log.info("Home button clicked");
		}
			
		driver.findElement(By.cssSelector(global.getProperty("CustLoginBTN"))).click();
		
		if(isDisplayedCSS("body > div.ng-scope > div > div.ng-scope > div > form > div > label", "Your Name Label"))
			log.info("Cust Login done");
	}
	
	@Test
	public void BMlogin() throws Exception
	{
		if(!isDisplayedCSS(global.getProperty("BMLoginBTN").toString(),"BMLoginBTN"))
		{
			driver.findElement(By.cssSelector(global.getProperty("Home"))).click();
			log.info("Home button clicked");
		}
			
		driver.findElement(By.cssSelector(global.getProperty("BMLoginBTN"))).click();
		
		if(isDisplayedCSS("body > div.ng-scope > div > div.ng-scope > div > div.center > button:nth-child(1)", "Add customer"))
			log.info("BM Login done");
		//Assert.fail();
	}
}
