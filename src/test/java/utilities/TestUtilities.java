package utilities;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.baseClass;

public class TestUtilities extends baseClass {
	
	public static void takesScreenshot(String funcName) throws Exception
	{
		File tss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String fileName = "Fail_"+funcName+"_"+d.toString().replace(":", "_").replace(" ","_")+".jpg";
		File target = new File("C:\\Users\\Nakul Paul\\eclipse-workspace\\SeleniumProject1\\src\\test\\resources\\Screenshots\\"+fileName);
		FileUtils.copyFile(tss, target);
	}
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m) throws Exception
	{
		Object[][] data=getDataFromExcel(m.getName());
		return data;
	}
}
