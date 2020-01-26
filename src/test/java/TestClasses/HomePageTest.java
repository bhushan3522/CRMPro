package TestClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageClasses.NewContactsPage;
import PageClasses.HomePage;
import PageClasses.LoginPage;
import TestBasePackage.TestBaseClass;

public class HomePageTest extends TestBaseClass{
	
	LoginPage loginPage;
	HomePage homePage;
	NewContactsPage contactPage;
	
	@BeforeMethod
	public void setup()
	{
		initialize();
		loginPage = new LoginPage();
		homePage = loginPage.validateLogin();
		homePage.switchToFrame();
	}
	
	@AfterMethod
	public void tearDown()
	{
		destruct();
	}
	
	@Test(priority=1,enabled=true)
	public void loggedInUserTest()
	{
		String userName = homePage.validateLoggedInUser();
		Assert.assertTrue(userName.contains("Bhushanas"));
	}
	
	@Test(priority=2)
	public void currentDateTest()
	{
		String currentDate = homePage.currentCalenderDate();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		Assert.assertEquals(currentDate, formatter.format(date));
	}
	
	@Test(priority=2,enabled=false)
	public void navigationToContactsTest()
	{
		contactPage = homePage.navigateToNewContactsPage();
	}
}
