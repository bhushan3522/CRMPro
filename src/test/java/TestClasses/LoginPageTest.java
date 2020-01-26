package TestClasses;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageClasses.HomePage;
import PageClasses.LoginPage;
import TestBasePackage.TestBaseClass;

public class LoginPageTest extends TestBaseClass {
	
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setup()
	{
		initialize();
		loginPage = new LoginPage();
	}

	@AfterMethod
	public void tearDown()
	{
		destruct();
	}
	
	@Test(priority=0,enabled=false)
	public void loginPageTitleTest()
	{
		String actualTitle = loginPage.validateLoginPageTitle();
		String expectedTitle = "CRMPRO - CRM software for customer relationship management, sales, and support.";
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(priority=1,enabled=false)
	public void menuOptionsTest()
	{
		boolean flag=true;
		List<WebElement> menuOptions = loginPage.validateMenuOptions();
		String menuOptionsArr[] = {"Home","Sign Up","Pricing","Features","Customers","Contact"};
		//System.out.println("size of string arr "+menuOptionsArr.length);
		//System.out.println("size of elements"+menuOptions.size());
		if(menuOptions.size()==menuOptionsArr.length)
		{
			for(int i=0; i<menuOptions.size();i++)
			{
				if(!(menuOptions.get(i).getText().equalsIgnoreCase(menuOptionsArr[i])))
				{
					flag=false;
					break;
				}
				else
					flag=true;
			}
		}
		
		Assert.assertTrue(flag);
	}
	
	@Test(priority=1,enabled=false)
	public void elementsAvailabilityTest()
	{
		boolean flag = loginPage.validateElemntsofLoginPage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=2)
	public void loginTest()
	{
		homePage = loginPage.validateLogin();
		Boolean flag = driver.getTitle().equals("CRMPRO");
		Assert.assertTrue(flag);
	}
}
