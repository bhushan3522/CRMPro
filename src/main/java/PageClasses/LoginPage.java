package PageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBasePackage.TestBaseClass;

public class LoginPage extends TestBaseClass {
	
	WebDriverWait wait;
	
	@FindBy(name="username")
	private WebElement userName;

	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(xpath="//input[@value=\"Login\"]")
	private WebElement loginBtn;
	
	@FindBy(xpath="//div[@id=\"tawkchat-message-preview-close\"]//div")
	private WebElement closePopUp;
	
	public LoginPage() {

	PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public List<WebElement> validateMenuOptions()
	{
		
		List<WebElement> menuOptions = driver.findElements(By.xpath("//a[@href=\"https://www.crmpro.com/index.html\"]"
				+ "//parent::div//following-sibling::div/ul//li//a"));
		
		return menuOptions;
	}
	
	public boolean validateElemntsofLoginPage()
	{
		if(userName.isDisplayed() && password.isDisplayed() && loginBtn.isDisplayed())
		{
			return true;
		}
		else
			return false;
	}
	
	public HomePage validateLoginUsingLoginBtn()
	{
		userName.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		
		wait = new WebDriverWait(driver, 120);
		
		driver.switchTo().frame(4);
		
		wait.until(ExpectedConditions.elementToBeClickable(closePopUp));
		closePopUp.click();
		
		driver.switchTo().parentFrame();
		
		loginBtn.click();
		
		return new HomePage();
	}
	
	public HomePage validateLogin()
	{
		userName.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password")+Keys.ENTER);
		
		return new HomePage();
	}
	

}
