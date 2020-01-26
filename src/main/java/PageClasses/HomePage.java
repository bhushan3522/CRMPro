package PageClasses;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import TestBasePackage.TestBaseClass;

public class HomePage extends TestBaseClass{

	Actions action = new Actions(driver);
	
	@FindBy(xpath="//td[contains(text(),\"Bhushan\")]")
	private WebElement loggedInUser;
	
	@FindBy(xpath="//frameset/frame[@name=\"mainpanel\"]")
	private WebElement frame1;
	
	@FindBy(css = "a[title=\"Contacts\"]")
	private WebElement contactsPage;
	
	@FindBy(xpath="//a[contains(text(),\"Contacts\")]")
	private WebElement contactTab;
	
	@FindBy(xpath="//a[contains(text(),\"New Contact\")]")
	private WebElement newContactSubMenu;
	
	@FindBy(xpath="(//div[@id=\"crmcalendar\"]//table)[2]//tbody//descendant::td")
	private List<WebElement> calData;
	
	@FindBy(name="slctMonth")
	private WebElement monthDropdown;
	
	@FindBy(name="slctYear")
	private WebElement yearDropdown;
	
	@FindBy(xpath = "//a[contains(text(),\"Companies\")]")
	private WebElement companiesTab;
	
	@FindBy(xpath = "//a[contains(text(),\"New Company\")]")
	private WebElement newCompany;
	
	@FindBy(xpath = "//a[@title=\"Tasks\"]")
	private WebElement tasksTab;
	
	@FindBy(xpath="//a[@title=\"New Task\"]")
	private WebElement newTask;
	
	public HomePage() {

		PageFactory.initElements(driver, this);
	}

	public void switchToFrame()
	{
		driver.switchTo().frame(frame1);
	}
	
	public String validateLoggedInUser()
	{
		return loggedInUser.getText();
	}
	
	public String currentCalenderDate()
	{
		//fetching current day
		WebElement date;
		String day="";
		//System.out.println("Size of rows is : "+calData.size());
		Iterator<WebElement> it = calData.iterator();
		while(it.hasNext())
		{
			date=it.next();
			if(date.getCssValue("background-color").equalsIgnoreCase("rgba(192, 192, 192, 1)"))
			{
				day = date.getText();
				break;
			}
		}
		
//		for(int i = 0; i < calData.size();i++)
//		{
//			System.out.println(calData.get(i).getCssValue("background-color")+calData.get(i).getText());
//			if(calData.get(i).getCssValue("background-color").equalsIgnoreCase("rgba(192, 192, 192, 1)"))
//			{
//				System.out.println(calData.get(i).getText());
//				break;
//			}
//		}
		
		//fetching current month
		Select sel = new Select(monthDropdown);
		WebElement selectedMonth = sel.getFirstSelectedOption();
		String month = selectedMonth.getAttribute("value");
		
		//fetching current year
		Select sel1 = new Select(yearDropdown);
		WebElement selectedYear = sel1.getFirstSelectedOption();
		String year = selectedYear.getAttribute("value");
		
		//concatenating all strings
		String currentDate = day.concat("/").concat(month).concat("/").concat(year);
		return currentDate;
	}
	
	public ContactsPage navigateToContactsPage()
	{
		contactsPage.click();
		return new ContactsPage();
	}
	
	public NewContactsPage navigateToNewContactsPage()
	{
		action.moveToElement(contactTab)
		.moveToElement(newContactSubMenu)
		.click()
		.build()
		.perform();		
		
		return new NewContactsPage();
	}
	
	public NewTaskPage navigateToNewTaskPage() {
		
		action.moveToElement(tasksTab)
		.moveToElement(newTask)
		.click()
		.build()
		.perform();
		
		return new NewTaskPage();
	}
}
