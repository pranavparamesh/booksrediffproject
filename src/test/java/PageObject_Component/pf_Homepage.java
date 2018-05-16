package PageObject_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pf_Homepage {
	
	//3sections - 1. initialize page factory, 2. identify elements based on @findBy 3.create reusableable methods
	
	//2nd section - collect the attributes
	@FindBy(linkText="Sign In")
	public WebElement lnk_signIn;
	
	@FindBy(className="proper")
	public WebElement valid_msg;
	
	@FindBy(linkText="Sign Out")
	public WebElement lnk_signOut;
	
	@FindBy(id="srchword")
	public WebElement search_Txt;
	
	@FindBy(className="newsrchbtn")
	public WebElement search_Btn;
	
	@FindBy(className="sorrymsg")
	public WebElement sorry_Msg;
	
	@FindBy(xpath="//*[@id='myDataDiv']/div[1]/p[1]")
	public WebElement validsearch_Msg;
	
	
	//1st Section - initialize the page factory
	public pf_Homepage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//3rd Section - perform operation
	public void clickSignIn()
	{
		lnk_signIn.click();
	}
	
	public String getWlcMsg()
	{
		return valid_msg.getText();
	}
	
	public void clickSignOut()
	{
		lnk_signOut.click();
	}
	
	public void enterKeyword(String searchKeyword)
	{
		search_Txt.sendKeys(searchKeyword);
	}	
	
	public void clickSearch()
	{
		search_Btn.click();
	}
	
	public String getValidSearchMsg() throws InterruptedException
	{
		Thread.sleep(5000);
		return validsearch_Msg.getText();
	}
	
	public String getInvalidSearchMsg() throws InterruptedException
	{
		Thread.sleep(5000);
		return sorry_Msg.getText();
	}

}
