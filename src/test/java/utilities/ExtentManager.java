package utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	public static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		if(extent==null)
		{
			extent=new ExtentReports
					(System.getProperty("user.dir")+"\\src\\test\\resources\\Reports\\extent.html",
							true, DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File("C:\\Users\\Nakul Paul\\eclipse-workspace\\SeleniumProject1\\src\\test\\resources\\ReportsConfig.xml"));
		}
		return extent;
	}
}
