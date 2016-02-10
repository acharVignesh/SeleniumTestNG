package com.philips.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class ChromTest {
	Proxy proxy;
	@Test
	public void testGoogleSearch() throws InterruptedException {
	  // Optional, if not specified, WebDriver will search your path for chromedriver.
		try
		{
	  System.setProperty("webdriver.chrome.driver", "D:/Workspace/seleniumautomation/BrowserServer/chromedriver.exe");
	  ChromeOptions options=new ChromeOptions();
	  options.addArguments("user-data-dir=C:/310187347/user/AppData/Local/Google/Chrome/User Data");
	  options.addArguments("--start-maximized");
      options.addArguments("--use-fake-ui-for-media-stream");
	  options.addArguments("--test-type");
	  options.addArguments("detach=false");
	  
	  WebDriver driver = new ChromeDriver(options);
		//Thread.sleep(5000);
	  driver.get("http://www.google.com/xhtml");
	  WebElement searchBox = driver.findElement(By.name("q"));
	  searchBox.sendKeys("ChromeDriver");
	  searchBox.submit();
	  Thread.sleep(5000);  // Let the user actually see something!
	  driver.close();
	  //driver.quit();
		}
		catch(InterruptedException e)
		{
			System.out.println("IE Exception:");
		}
		catch(Exception e)
		{
		System.out.println("Exception:");	
		}
		
	}
}
