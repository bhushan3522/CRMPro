package TestClasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageClasses.NewContactsPage;
import PageClasses.HomePage;
import PageClasses.LoginPage;
import TestBasePackage.TestBaseClass;
import Util.TestUtil;

public class NewContactsPageTest extends TestBaseClass{

	LoginPage loginPage;
	HomePage homePage;
	NewContactsPage newContactsPage;
	
	@BeforeMethod
	public void setup()
	{
		initialize();
		loginPage = new LoginPage();
		homePage = loginPage.validateLogin();
		homePage.switchToFrame();
		newContactsPage = homePage.navigateToNewContactsPage();
		newContactsPage.waitForElement();
		//contactsPage.switchToFrame();
	}
	
	@AfterMethod()
	public void tearDown()
	{
		destruct();
	}
	
	@DataProvider(name="TestDataProvider")
	public Object[][] fetchTestData()
	{
		return TestUtil.getTestData("ContactsData");
	}
	
	@Test(dataProvider="TestDataProvider")
	public void newContactCreateTest(String title, String fName,String mName,String lName,String mobile,String email,String country)
	{
		newContactsPage.createNewContacts(title, fName, mName, lName, mobile, email, country);
	}
}
