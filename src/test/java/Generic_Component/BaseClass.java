package Generic_Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {
	
	public WebDriver driver;
	public static Logger log = Logger.getLogger(BaseClass.class);
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	
	
	@BeforeMethod(groups={"sanity","regression"})
	public void launchApplication() throws IOException
	{
		UtilityClass uc = new UtilityClass();
		
		driver= new FirefoxDriver();
		driver.get(uc.reading_properties("url"));
		driver.manage().window().maximize();	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	
	@AfterMethod(groups={"sanity","regression"})
	public void tearDown()
	{
		driver.close();	
		extentReport.endTest(extentTest);
		extentReport.flush();
	}
	
	public String capture_Screenshot(String TC_ID, String Order_Set) throws IOException
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String str = df.format(date)+".png";
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("D:\\HybridFramework_May\\Screenshot\\"+TC_ID+"-"+Order_Set+"-"+str));
		
		String path ="D:\\HybridFramework_May\\Screenshot\\"+TC_ID+"-"+Order_Set+"-"+str;
		return path;
	}
	
	@BeforeSuite(groups={"sanity","regression"})
	public void report_Extent()
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String str = df.format(date);
		extentReport = new ExtentReports("D:\\HybridFramework_May\\Report\\"+"BookRediff"+str+".html");
	}
	

}
