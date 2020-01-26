package TestClasses;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageClasses.ContactsPage;
import PageClasses.HomePage;
import PageClasses.LoginPage;
import TestBasePackage.TestBaseClass;

public class ContactsPageTest extends TestBaseClass{

	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	@BeforeMethod
	public void setup()
	{
		initialize();
		loginPage = new LoginPage();
		homePage = loginPage.validateLogin();
		homePage.switchToFrame();
		contactsPage = homePage.navigateToContactsPage();
	}
	
	@AfterMethod
	public void tearDown()
	{
		destruct();
	}
	
	@Test
	public void deleteExistingContactsTest()
	{
		int size = contactsPage.deleteAllContacts();
		Assert.assertTrue(size == 1);
	}
}
