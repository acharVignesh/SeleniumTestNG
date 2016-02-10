package com.philips.SATscripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.philips.pageobjects.LoginPage;
import com.philips.utils.DriverConfig;
import com.philips.utils.LoadProperties;

public class LoginTest {
	WebDriver driver;
	LoginPage loginPage;
	@BeforeTest
	public void setUp()
	{
		try
		{
		LoadProperties.getProperties("IHE.properties");
		String URL=LoadProperties.BASE_URL;
		System.out.println(URL);
		driver=DriverConfig.createDriver();
		driver.get(URL);
		}
		catch(Exception e)
		{
			System.out.println("Exception While Launching Chrome Browser");
		}
	}
	@Test
	public void loginTest()
	{
		try {
		loginPage=new LoginPage(driver);
		loginPage.setUserName("admin");
		loginPage.setPassword("admin");
		loginPage.clickLogin();
		Thread.sleep(2000);

			//driver.manage().wait(5000);
		//	Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Errror");
			//e.printStackTrace();
		}
	}
	@AfterTest
	public void teatDown()
	{
		try
		{
		driver.close();
		driver.quit();
		}
		catch(Exception e)
		{
			System.out.println("Exceptioned");
		}
	}
	

}
