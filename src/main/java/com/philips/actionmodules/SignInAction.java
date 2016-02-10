package com.philips.actionmodules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.philips.pageobjects.HomePage;
import com.philips.pageobjects.LoginPage;
import com.philips.utils.DriverConfig;
import com.philips.utils.LoadProperties;
import com.philips.utils.Log;

public class SignInAction {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;

	@Test
	public void loginToApplication() throws Exception {
		Log.info("This is the Login step");
		LoadProperties.getProperties();
		String URL = LoadProperties.BASE_URL;
		System.out.println(URL);
		Log.info(URL);
		driver = DriverConfig.getDriver();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		loginPage = new LoginPage(driver);
		loginPage.login(LoadProperties.USERNAME, LoadProperties.PASSWORD);
	}
	@Test(priority=1)
	public void loginToApplication(String testCaseName) throws Exception {
		Log.startTestCase(testCaseName);
		LoadProperties.getProperties();
		String URL = LoadProperties.BASE_URL;
		System.out.println(URL);
		Log.info(URL);
		driver = DriverConfig.getDriver();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		loginPage = new LoginPage(driver);
		loginPage.login(LoadProperties.USERNAME, LoadProperties.PASSWORD);
	}
	@Test
	public void navigateToLinkPage() throws Exception {
		LoadProperties.getProperties();
		String URL = LoadProperties.BASE_URL;
		Log.info("Navigating to link patient step.");
		driver = DriverConfig.getDriver();
		homePage = new HomePage(driver);
		homePage.navigateToLinkPatient();
	}
	@Test
	public void navtigateToHomePage(String testCaseName) throws Exception{
		Log.info("Navgating to Home Page");
		driver = DriverConfig.getDriver();
		homePage = new HomePage(driver);
		homePage.navigateToHomePage();
		Log.endTestCase(testCaseName);
	}
	@Test
	public void closeBrowserSession() throws Exception {
		driver = DriverConfig.getDriver();
		Log.info("Closing the browser session");
		driver.close();
		driver.quit();
		Log.info("**********Browser is closed**********");
	}
}
