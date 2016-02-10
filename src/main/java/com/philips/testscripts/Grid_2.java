package com.philips.testscripts;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Grid_2 {
WebDriver driver;
String baseURL,nodeURL;
@BeforeTest
public void setUp() throws MalformedURLException
{
	baseURL="http://newtours.demout.com/";
	nodeURL="http://161.91.234.235:5566/wd/hub";
	DesiredCapabilities capabilities=DesiredCapabilities.firefox();
	capabilities.setBrowserName("firefox");
	capabilities.setPlatform(Platform.VISTA);
	driver=new RemoteWebDriver(new URL(nodeURL),capabilities);
	
	
}
@AfterTest
public void tearDown()
{
	try
	{
		Thread.sleep(100000);
		System.out.println("Tear Down Method");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
@Test
public void simpleTest()
{
	driver.get(baseURL);
}

}
