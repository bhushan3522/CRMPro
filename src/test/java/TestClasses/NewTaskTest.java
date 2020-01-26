package TestClasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageClasses.HomePage;
import PageClasses.LoginPage;
import PageClasses.NewTaskPage;
import TestBasePackage.TestBaseClass;
import Util.TestUtil;

public class NewTaskTest extends TestBaseClass{
	
	LoginPage loginPage;
	HomePage homePage;
	NewTaskPage newTaskPage;
	
	@BeforeMethod
	public void setUp(){
		initialize();
		loginPage = new LoginPage();
		homePage = loginPage.validateLogin();
		homePage.switchToFrame();
		newTaskPage = homePage.navigateToNewTaskPage();
	}
	
	@AfterMethod
	public void tearDown() {
		destruct();
	}
	
	@DataProvider(name="taskData")
	public Object[][] taskData() {
		return TestUtil.getTestData("TaskData");
	}
	
	@Test(dataProvider="taskData")
	public void testNewTaskCreation(String tTitle,String tDeadline,String tAutoExtend,String tStatus,String tCompletion,String tType,String tPriority) {
		newTaskPage.createNewTask(tTitle, tDeadline, tAutoExtend, tStatus, tCompletion, tType, tPriority);
	}

}
