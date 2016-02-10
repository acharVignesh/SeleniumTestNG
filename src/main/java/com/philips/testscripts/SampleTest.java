package com.philips.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTest {
	WebDriver driver;
	static ProfilesIni profile = null;
	static FirefoxProfile ffprofile = null;
	@BeforeTest
	public void setUp(){
	driver=new FirefoxDriver();
	profile = new ProfilesIni();
	ffprofile = profile.getProfile("SELENIUM");
	DesiredCapabilities cap = DesiredCapabilities.firefox();
	driver.get("https://www.google.co.in");
	}
	@Test
	public void searchGoogle()
	{
		try
		{
	WebElement searchSpace=	driver.findElement(By.name("q"));
	searchSpace.sendKeys("Sachin Tendulkar");	
	Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println("Exception");
		}
	
	}
	@AfterTest
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}

}
