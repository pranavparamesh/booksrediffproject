package PageObject_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pf_Loginpage {

	//1st Section - intialize the page factory
	public pf_Loginpage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//2nd section - collect the attributes
	@FindBy(name="logid")
	public WebElement username_txt;
	
	@FindBy(name="pswd")
	public WebElement password_txt;
	
	@FindBy(xpath="//input[@value='Login']")
	public WebElement login_btn;
	
	@FindBy(xpath="//*[contains(text(),'Sorry we were unable')]")
	public WebElement error_msg;
	
	public void login(String un, String pwd)
	{
		username_txt.sendKeys(un);
		password_txt.sendKeys(pwd);
		login_btn.click();
	}
	
	public String getErrorMsg()
	{
		return error_msg.getText();
	}
	
	

}
