package Scenario_Component;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import Generic_Component.BaseClass;
import PageObject_Component.pf_Homepage;
import PageObject_Component.pf_Loginpage;

public class Scenario_Search extends BaseClass{
	
	@Test(dataProvider="dp_InvalidSearch",dataProviderClass=DataProvider_Component.DataproviderClass.class, groups={"regression"})	
	public void testInvalidSearch(Map<String,String> search) throws IOException, InterruptedException
	{
		SoftAssert sAssert = new SoftAssert();
		
		String key = search.get("Keyword");
		String Exp_Result = search.get("Expected_Results");
		String testcaseId = search.get("TC_ID");
		String orderSet = search.get("Order_Set");
		log.info("Executing the testcase "+testcaseId+" order set is "+orderSet);
		
		extentTest=extentReport.startTest(testcaseId);
		extentTest.log(LogStatus.PASS, "Executing the testcase "+testcaseId+" order set is "+orderSet);
		
		pf_Homepage home_page = new pf_Homepage(driver);
		home_page.enterKeyword(key);
		home_page.clickSearch();
		
		String actualResults = home_page.getInvalidSearchMsg();
		
		if(actualResults.equalsIgnoreCase(Exp_Result))
		{
			log.info("Passed as actual result is "+actualResults+" Expected result is "+Exp_Result);
			extentTest.log(LogStatus.PASS, "Passed as actual result is "+actualResults+" Expected result is "+Exp_Result,extentTest.addScreenCapture(capture_Screenshot(testcaseId, orderSet)));
		}
		else
		{
			log.info("Failed as actual result is "+actualResults+" Expected result is "+Exp_Result);
			extentTest.log(LogStatus.FAIL, "Failed as actual result is "+actualResults+" Expected result is "+Exp_Result,extentTest.addScreenCapture(capture_Screenshot(testcaseId, orderSet)));
			sAssert.assertEquals(actualResults, Exp_Result);
			capture_Screenshot(testcaseId, orderSet);
		}
		
		sAssert.assertAll();
	}
	
	@Test(dataProvider="dp_validSearch",dataProviderClass=DataProvider_Component.DataproviderClass.class, groups={"sanity"})	
	public void testValidSearch(Map<String,String> search) throws IOException, InterruptedException
	{
		SoftAssert sAssert = new SoftAssert();
		
		String key = search.get("Keyword");
		String Exp_Result = search.get("Expected_Results");
		String testcaseId = search.get("TC_ID");
		String orderSet = search.get("Order_Set");
		log.info("Executing the testcase "+testcaseId+" order set is "+orderSet);
		extentTest=extentReport.startTest(testcaseId);
		extentTest.log(LogStatus.PASS, "Executing the testcase "+testcaseId+" order set is "+orderSet);
		
		pf_Homepage home_page = new pf_Homepage(driver);
		home_page.enterKeyword(key);
		home_page.clickSearch();
		
		String actualResults = home_page.getValidSearchMsg();
		
		if(actualResults.contains(Exp_Result))
		{
			log.info("Passed as actual result is "+actualResults+" Expected result is "+Exp_Result);
			extentTest.log(LogStatus.PASS, "Passed as actual result is "+actualResults+" Expected result is "+Exp_Result,extentTest.addScreenCapture(capture_Screenshot(testcaseId, orderSet)));
		}
		else
		{
			log.info("Failed as actual result is "+actualResults+" Expected result is "+Exp_Result);
			extentTest.log(LogStatus.FAIL, "Failed as actual result is "+actualResults+" Expected result is "+Exp_Result,extentTest.addScreenCapture(capture_Screenshot(testcaseId, orderSet)));
			sAssert.assertEquals(actualResults, Exp_Result);
			capture_Screenshot(testcaseId, orderSet);
		}
		
		sAssert.assertAll();
	}

}
