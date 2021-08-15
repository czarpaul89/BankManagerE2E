package testCases;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.*;

import base.baseClass;
import utilities.TestUtilities;

public class AddCustomer extends baseClass {

	@BeforeClass
	public void getBMPage()
	{
		driver.findElement(By.cssSelector(global.getProperty("Home"))).click();
		log.info("home btn clicked");
		driver.findElement(By.cssSelector(global.getProperty("BMLoginBTN"))).click();
		log.info("bank manager login");
		driver.findElement(By.cssSelector(global.getProperty("AddCust"))).click();
		log.info("adding customer");
	}
	
	@Test(dataProviderClass=TestUtilities.class,dataProvider="dp")
	public void addCust(Object firstName, Object lastName, Object pincode) throws Exception
	{
		driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/form/div[1]/input")).sendKeys(firstName.toString());
		driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/form/div[2]/input")).sendKeys(lastName.toString());
		driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/form/div[3]/input")).sendKeys(pincode.toString());
		Thread.sleep(500);
		driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/form/button")).click();
		Thread.sleep(500);
		log.info(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		Thread.sleep(500);
	}
	
}
