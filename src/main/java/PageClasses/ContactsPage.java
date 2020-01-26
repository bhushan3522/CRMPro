package PageClasses;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import TestBasePackage.TestBaseClass;

public class ContactsPage extends TestBaseClass{
	
	@FindBy(xpath="//form[@id=\"vContactsForm\"]//following::input[@type=\"checkbox\"]")
	private List<WebElement> contactsList;
	
	@FindBy(xpath="(//form[@id=\"vContactsForm\"]//following::input[@type=\"checkbox\"])[1]")
	private WebElement selectAllContactsCheckbox;
	
	@FindBy(xpath="//select[@name=\"do_action\"]")
	private WebElement selectedContactsActionDropdown;
	
	@FindBy(xpath="//input[@value=\"DO\"]")
	private WebElement doBtn;
	
	public ContactsPage() {

		PageFactory.initElements(driver, this);
	}
	
	public int deleteAllContacts()
	{
		if(contactsList.size() > 1)
		{
			selectAllContactsCheckbox.click();
			Select sel = new Select(selectedContactsActionDropdown);
			sel.selectByValue("DELETE");
			doBtn.click();
			
			Alert alert = driver.switchTo().alert();
			alert.accept();
			
		}
		else
			System.out.println("No Contacts to delete");
		return contactsList.size();
	}

}
