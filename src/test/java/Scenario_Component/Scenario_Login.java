package Scenario_Component;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import Generic_Component.BaseClass;
import PageObject_Component.pf_Homepage;
import PageObject_Component.pf_Loginpage;

public class Scenario_Login extends BaseClass{
	
	@Test(dataProvider="dp_Invalidlogin",dataProviderClass=DataProvider_Component.DataproviderClass.class, groups={"regression"})	
	public void testInvalidlogin(Map<String,String> login) throws IOException
	{
		SoftAssert sAssert = new SoftAssert();
		
		String Uname = login.get("Username");
		String Pwd = login.get("Password");
		String Exp_Result = login.get("Expected_Results");
		String testcaseId = login.get("TC_ID");
		String orderSet = login.get("Order_Set");
		log.info("Executing the testcase "+testcaseId+" order set is "+orderSet);
		
		extentTest=extentReport.startTest(testcaseId);
		extentTest.log(LogStatus.PASS, "Executing the testcase "+testcaseId+" order set is "+orderSet);
		
		pf_Homepage home_page = new pf_Homepage(driver);
		home_page.clickSignIn();
		
		pf_Loginpage login_page = new pf_Loginpage(driver);
		login_page.login(Uname, Pwd);
		String actualResults = login_page.getErrorMsg();
		
		if(Exp_Result.equalsIgnoreCase(actualResults))
		{
			log.info("Passed as actual result is "+actualResults+" Expected result is "+Exp_Result);
			extentTest.log(LogStatus.PASS, "Passed as actual result is "+actualResults+" Expected result is "+Exp_Result, extentTest.addScreenCapture(capture_Screenshot(testcaseId, orderSet)));
		}
		else
		{
			log.info("Failed as actual result is "+actualResults+" Expected result is "+Exp_Result);
			extentTest.log(LogStatus.FAIL, "Failed as actual result is "+actualResults+" Expected result is "+Exp_Result, extentTest.addScreenCapture(capture_Screenshot(testcaseId, orderSet)));
			sAssert.assertEquals(actualResults, Exp_Result);
			capture_Screenshot(testcaseId, orderSet);
		}
		
		sAssert.assertAll();
	}
	
	
	@Test(dataProvider="dp_validlogin",dataProviderClass=DataProvider_Component.DataproviderClass.class, groups={"sanity"})
	public void testValidlogin(Map<String,String> login) throws IOException
	{
		String Uname = login.get("Username");
		String Pwd = login.get("Password");
		String Exp_Result = login.get("Expected_Results");
		String testcaseId = login.get("TC_ID");
		String orderSet = login.get("Order_Set");
		log.info("Executing the testcase "+testcaseId+" order set is "+orderSet);
		extentTest=extentReport.startTest(testcaseId);
		extentTest.log(LogStatus.PASS, "Executing the testcase "+testcaseId+" order set is "+orderSet);
		
		SoftAssert sAssert = new SoftAssert();
		pf_Homepage home_page = new pf_Homepage(driver);
		home_page.clickSignIn();
		
		pf_Loginpage login_page = new pf_Loginpage(driver);
		login_page.login(Uname, Pwd);
		
		String actualResults = home_page.getWlcMsg();
		
		if(Exp_Result.equalsIgnoreCase(actualResults))
		{
			log.info("Passed as actual result is "+actualResults+" Expected result is "+Exp_Result);
			extentTest.log(LogStatus.PASS, "Passed as actual result is "+actualResults+" Expected result is "+Exp_Result, extentTest.addScreenCapture(capture_Screenshot(testcaseId, orderSet)));
		}
		else
		{
			log.info("Failed as actual result is "+actualResults+" Expected result is "+Exp_Result);
			extentTest.log(LogStatus.FAIL, "Failed as actual result is "+actualResults+" Expected result is "+Exp_Result, extentTest.addScreenCapture(capture_Screenshot(testcaseId, orderSet)));
			sAssert.assertEquals(actualResults, Exp_Result);
			capture_Screenshot(testcaseId, orderSet);
		}
		sAssert.assertAll();
	}

}
