package testCases;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import base.baseClass;
import utilities.TestUtilities;

public class openAccnt extends baseClass {

	@BeforeClass
	public void openpage()
	{
		driver.findElement(By.cssSelector(global.getProperty("openAccnt"))).click();
	}
	
	@Test(dataProviderClass=TestUtilities.class,dataProvider="dp") 
	public void openAccount(Object cust, Object curr) throws Exception
	{
		doSelect(global.getProperty("customer").toString(),cust.toString());
		Thread.sleep(1000);
		
		doSelect(global.getProperty("currency").toString(),curr.toString());
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector(global.getProperty("process"))).click();
		Thread.sleep(1000);
		
		log.info(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		Thread.sleep(500);
	}
	
}
