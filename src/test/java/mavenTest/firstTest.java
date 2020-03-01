package mavenTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class firstTest
{
	WebDriver driver;
	
	@BeforeClass
	public void chromeInit()
	{
		System.setProperty("webdriver.chrome.driver", "C://Automation/libs/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.danielgold.net/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test (priority = 1, groups = {"sanity"})
	public void confirmHeader()
	{
		try
		{
			String header = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/header/div/div[3]/div/div/div/div/h1")).getText();
			assertEquals(header, "In god we trust - The rest we test");
		}
		
		catch (AssertionError ae)
		{
			fail("Test failed: " + ae);
		}
	}
	
	@Test (priority = 2, groups = {"sanity"})
	public void confirmButton()
	{
		try
		{
			WebElement subButton = driver.findElement(By.className("subscribe-button"));
			assertTrue(subButton.isEnabled());
		}
		
		catch (AssertionError ae)
		{
			fail("Test failed: " + ae);
		}
	}
	
	@Test (priority = 3, groups = {"regression"})
	public void subscribeText()
	{
		try
		{
			driver.findElement(By.className("subscribe-button")).click();
			String alertHeader = driver.findElement(By.id("subscribe-title")).getText();
			assertEquals(alertHeader, "Subscribe to this blog");
		}
		
		catch (AssertionError ae)
		{
			fail("Test failed: " + ae);
		}
	}
	
	@AfterClass
	public void closeChrome()
	{
		driver.close();
	}
}
