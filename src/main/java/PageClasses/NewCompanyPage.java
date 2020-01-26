package PageClasses;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestBasePackage.TestBaseClass;

public class NewCompanyPage extends TestBaseClass {
	
	@FindBy(id = "company_name")
	private WebElement companyName;
	
	@FindBy(name = "industry")
	private WebElement industry;
	
	@FindBy(id = "annual_revenue")
	private WebElement annualRevenue;
	
	@FindBy(id = "num_of_employees")
	private WebElement numberOfEmp;
	
	@FindBy(name = "status")
	private WebElement status;
	
	@FindBy(xpath = "//select[@name=\"status\"]//following-sibling::input")
	private WebElement statusHelp;
	
	@FindBy(id = "phone")
	private WebElement phone;
	
	@FindBy(id = "website")
	private WebElement website;
	
	@FindBy(name = "address_title")
	private WebElement addTitle;
	
	@FindBy(name = "type")
	private WebElement addType;
	
	@FindBy(name = "address")
	private WebElement address;
	
	@FindBy(name = "country")
	private WebElement country;
	
	@FindBy(xpath = "(//*[@id=\"companyForm\"]//input[@value=\"Save\"])[1]")
	private WebElement saveBtn;
	
	public NewCompanyPage() {

		PageFactory.initElements(driver, this);
	}
	
	private void fillCompanyDataForm(String name,String industry,String revenue,String empoyee,String status,String phone,String website,String addTitle,String addType,String address,String country)
	{
		companyName.sendKeys(name);
		this.industry.sendKeys(industry);
		annualRevenue.sendKeys(revenue);
		numberOfEmp.sendKeys(empoyee);
		
		
		this.status.sendKeys(status);
		this.phone.sendKeys(phone);
		this.website.sendKeys(website);
		this.addTitle.sendKeys(addTitle);
	}
	
	

}
