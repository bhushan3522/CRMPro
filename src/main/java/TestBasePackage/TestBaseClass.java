package TestBasePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBaseClass {
	
	public static WebDriver driver;
	public Properties prop;
	public TestBaseClass() {

		try 
		{
			FileInputStream fis = new FileInputStream("F:\\Projects\\crmpro\\src\\main\\java\\config\\config.properties");
			prop = new Properties();
			prop.load(fis);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void initialize()
	{
		if(prop.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverPath"));
			driver = new ChromeDriver();
			
			Map<String,Object> prefMap = new HashMap<String,Object>();
			prefMap.put("profile.default_content_settings.popup", 0);
			prefMap.put("download.default_directory", "F:\\Projects\\crmpro\\Downloads");
			
			ChromeOptions option = new ChromeOptions();
			option.setExperimentalOption("prefs", prefMap);
			option.addArguments("--test-type");
			option.addArguments("--disable-extensions");
		}
		else
			if(prop.getProperty("browser").equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", prop.getProperty("geckoDriverPath"));
				driver = new FirefoxDriver();
			}
			else
				System.out.println("BROWSER IS NOT SUPPORTED, USE EITHER CHROME/FIREFOX");
	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageLoadTimeOut")), TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
	public void destruct()
	{
		driver.close();
	}

	
}
