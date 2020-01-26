package PageClasses;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import TestBasePackage.TestBaseClass;

public class NewTaskPage extends TestBaseClass {
	
	@FindBy( id= "title")
	private WebElement title;
	
	@FindBy(id = "fieldId_deadline")
	private WebElement deadline;
	
	@FindBy(name = "auto_extend")
	private WebElement autoExtend;
	
	@FindBy(name = "status")
	private WebElement status;
	
	@FindBy(id="completion")
	private WebElement completion;
	
	@FindBy(name = "task_type")
	private WebElement taskType;
	
	@FindBy(name="priority")
	private WebElement priority;
	
	@FindBy(xpath = "(//form[@id=\"taskForm\"]//input[@value=\"Save\" and @type=\"submit\"])[1]")
	private WebElement save;
	
	public NewTaskPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void createNewTask(String tTitle,String tDeadline,String tAutoExtend, String tStatus,String tCompletion,String tType,String tPriority) {
		
		title.sendKeys(tTitle);
		deadline.sendKeys(tDeadline);
		
		//select dropdown for auto extend
		Select sel = new Select(autoExtend);
		List<WebElement> autoExtendList = sel.getOptions();
		for(WebElement e : autoExtendList) {
			if(e.getText().toLowerCase().contains(tAutoExtend.toLowerCase())) {
				sel.selectByVisibleText(e.getText());
			}
		}
		
		Select sel1 = new Select(status);
		List<WebElement> statusList = sel1.getOptions();
		for(WebElement e : statusList) {
			if(e.getText().toLowerCase().contains(tStatus.toLowerCase())) {
				sel1.selectByVisibleText(e.getText());
			}
		}
		
		completion.sendKeys(tCompletion);
		
		Select sel2 = new Select(taskType);
		List<WebElement> taskTypeList = sel2.getOptions();
		for(WebElement e : taskTypeList) {
			if(e.getText().toLowerCase().contains(tType.toLowerCase())) {
				sel2.selectByVisibleText(e.getText());
			}
		}
		
		Select sel3 = new Select(priority);
		List<WebElement> priorityList = sel3.getOptions();
		for(WebElement e : priorityList) {
			if(e.getText().toLowerCase().contains(tPriority.toLowerCase())) {
				sel3.selectByVisibleText(e.getText());
			}
		}
		
		save.click();
	}

}
