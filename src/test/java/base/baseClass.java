package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExtentManager;

public class baseClass {

	public static Properties global;
	public static FileInputStream fis;
	public static WebDriver driver;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	static Object[][] data;
	static File F=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\AddCustData.xlsx");
	static FileInputStream ip;
	static Workbook workbook;
	static Sheet sheet;
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;
	
	@BeforeSuite
	public void setup() throws Exception
	{
		global = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\properties\\Global.properties");
		global.load(fis);
		if(driver==null)
		{
			if(global.getProperty("browser").equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				Assert.assertTrue(driver!=null);
				log.info("broswer initialised");
				
				driver.get(global.getProperty("website"));
				
				if(isDisplayedCSS(global.getProperty("CustLoginBTN").toString(),"CustLoginBTN"))
					log.info("website opened");
			}
		}else{
			driver.quit();
			log.warn("Previous broswer closed");
		}

	}
	
	public static boolean isDisplayedCSS(String csspath, String eleName)
	{
		try{
			driver.findElement(By.cssSelector(csspath)).isDisplayed();
			log.info(eleName+" found");
			return true;
		}catch(Exception e){
			log.error(eleName+" was not found");
			return false;
		}
	}
	
	public static Object[][] getDataFromExcel(String sheetName) throws Exception
	{
		ip = new FileInputStream(F);
		workbook = WorkbookFactory.create(ip);
		sheet = workbook.getSheet(sheetName);
		Row row;
		Cell col;
		int rowCount=sheet.getLastRowNum()+1;
		int colCount=sheet.getRow(0).getLastCellNum();
		data=new Object[rowCount-1][colCount];
		for(int i=1;i<rowCount;i++)
		{
			row= sheet.getRow(i);
			for(int j=0;j<colCount;j++)
			{
				col = row.getCell(j);
				if(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("pincode"))
				{
					data[i-1][j]=(int)col.getNumericCellValue();
				}else {
					data[i-1][j]=col.getStringCellValue();
				}
			}
		}
		log.info("data initialsed with : ["+(rowCount-1)+"]["+colCount+"]");
		return data;
	}
	
	static WebElement dropdown;
	public static void doSelect(String selector, String value)
	{
		dropdown = driver.findElement(By.cssSelector(selector));
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
	}
	
	
	@AfterSuite
	public void tearDown() throws Exception
	{
		Thread.sleep(3000);
		log.warn("broswer closed");
		driver.quit();
	}
}
