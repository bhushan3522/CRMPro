package PageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBasePackage.TestBaseClass;

public class NewContactsPage extends TestBaseClass {
	
	WebDriverWait wait;
	
	@FindBy(xpath="//legend[contains(text(),\"Contact Information\")]")
	private WebElement contactLabel;

	@FindBy(name="title")
	private WebElement title;
	
	@FindBy(xpath="//frameset/frame[@name=\"mainpanel\"]")
	private WebElement frame1;
	
	@FindBy(id="first_name")
	private WebElement firstName;
	
	@FindBy(id="surname")
	private WebElement lastName;
	
	@FindBy(id="middle_initial")
	private WebElement middleName;
	
	@FindBy(id="mobile")
	private WebElement mobileNo;
	
	@FindBy(id="email")
	private WebElement emailId;
	
	@FindBy(name="country")
	private WebElement country;
	
	@FindBy(xpath="//input[@id=\"first_name\"]//following-sibling::span")
	private WebElement mandatoryFirstName;
	
	@FindBy(xpath="//input[@id=\"surname_name\"]//following-sibling::span")
	private WebElement mandatoryLastName;
	
	@FindBy(xpath="//input[@value=\"Load From Company\"]//following-sibling::input[@value=\"Save\"]")
	private WebElement saveBtn;
	
	public NewContactsPage() {

		PageFactory.initElements(driver, this);
	}
	public void switchToFrame()
	{
		driver.switchTo().frame(frame1);
	}
	
	public String validateNewContactsLabel()
	{
		return contactLabel.getText();
	}
	
	public boolean mandatoryFields()
	{
		boolean flag;
		if(mandatoryFirstName.getText().contains("*") && mandatoryLastName.getText().contains("*"))
		{
			flag = true;
		}
		else
		{
			flag = false;
		}
		return flag;
	}
	
	public void deleteExistingContacts()
	{
		
	}
	
	public void createNewContacts(String lTitle,String lFName,String lMName,String lLName,String lMobile,String lEmail,String lCountry)
	{
		Select sel = new Select(title);
		sel.selectByValue(lTitle.concat("."));
		
		firstName.sendKeys(lFName);
		middleName.sendKeys(lMName);
		lastName.sendKeys(lLName);
		mobileNo.sendKeys(lMobile);
		emailId.sendKeys(lEmail);
		country.sendKeys(lCountry);
		
		saveBtn.click();
	}
	
	public void waitForElement()
	{
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(contactLabel));
	}
	
}
