package Listeners;

import java.util.Date;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import base.baseClass;
import utilities.TestUtilities;

public class listener extends baseClass implements ITestListener {

	public void onTestFailure(ITestResult arg0)
	{
		Reporter.log("Test has failed - "+arg0.getName());
		try {
			TestUtilities.takesScreenshot(arg0.getName().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, "Test Failed : "+arg0.getName());
		test.addScreenCapture(System.getProperty("user.dir")+"\\src\\test\\resources\\Screenshots\\Fail_"+arg0.getName()+".jpg");
		rep.endTest(test);
		rep.flush();
	}	
	
	public void onTestSuccess(ITestResult arg0)
	{
		test.log(LogStatus.PASS, "Test Passed : "+arg0.getName());
		rep.endTest(test);
		rep.flush();
	}
	
	public void onTestStart(ITestResult arg0)
	{
		test=rep.startTest(arg0.getName());
	}
	
}

