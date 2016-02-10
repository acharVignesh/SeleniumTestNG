package com.philips.SATscripts;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.philips.customasserts.CustomAssert;
import com.philips.exceptions.IHEExceptions;
import com.philips.pageobjects.HomePage;
import com.philips.pageobjects.LoginPage;
import com.philips.pageobjects.UnlinkPatient;
import com.philips.utils.DriverConfig;
import com.philips.utils.LoadProperties;
public class UnlinkTests {
	WebDriver driver;
	HomePage homePage;
	UnlinkPatient unlinkPatient;
	LoginPage loginPage;
	Properties prop;
	String sourceAggregateId;
	String empiRoot;
	@BeforeSuite
	public void openApplication() {
		try {
			LoadProperties.getProperties("IHE.properties");
			String URL = LoadProperties.BASE_URL;
			driver = DriverConfig.createDriver();
			driver.get(URL);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			loginPage = new LoginPage(driver);
			homePage = new HomePage(driver);
			unlinkPatient=new UnlinkPatient(driver);
			loginPage.login(LoadProperties.USERNAME, LoadProperties.PASSWORD);
			homePage.navigateToHomePage();
			FileInputStream fin=new FileInputStream(new File("TestCase.properties"));
			prop=new Properties();
			prop.load(fin);
			System.out.println("**Set Up is Successfull**");
		} catch (Exception e) {
			System.out.println("Error opening the application");
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void setUp() {
		try
		{
			sourceAggregateId = LoadProperties.SOURCE_AGGREGATE;
			empiRoot =LoadProperties.EMPI_ROOT;
		}
		catch(Exception e)
		{
			System.out.println("Failed to set up");
			e.printStackTrace();
		}
	}
	@AfterTest
	public void tearDown() {
		homePage.navigateToHomePage();
	}
	@AfterSuite
	public void closeApplication() {
		driver.close();
		driver.quit();
	}
	@SuppressWarnings("unused")
	@Test
	public void unlinkLeastWeightedFragment()
	{
		try
		{
		homePage.navigateToHomePage();
		homePage.navigateToUnLinkPatient();
		unlinkPatient.setSourceRootId(empiRoot);
		unlinkPatient.setSourceExtensionId(sourceAggregateId);
		unlinkPatient.searchFragments();
		unlinkPatient.selectFragmentToUnLink(prop.getProperty("patient2IdRoot"), prop.getProperty("patient2IdExtension"));
		unlinkPatient.unlinkFragment();
		Map<String, List<String>> sourceDemography=unlinkPatient.getSourceAggregateDemographic();
		Map<String, List<String>> targetDemography=unlinkPatient.getTargetAggregateDemographic();
		CustomAssert.verifyEqual(prop.getProperty("patient2NameGiven"),targetDemography.get("firstname").get(0));
		CustomAssert.verifyEqual(prop.getProperty("patient2NameFamily"), targetDemography.get("lastname").get(0));
		CustomAssert.verifyEqual(prop.getProperty("patient2BirthTime"), targetDemography.get("dob").get(0));
		CustomAssert.verifyEqual(prop.getProperty("mailId"), targetDemography.get("email").get(0));
		
		CustomAssert.verifyEqual(prop.getProperty("patient1NameGiven"),sourceDemography.get("firstname").get(0));
		CustomAssert.verifyEqual(prop.getProperty("patient1NameFamily"), sourceDemography.get("lastname").get(0));
		CustomAssert.verifyEqual(prop.getProperty("patient1BirthTime"), sourceDemography.get("dob").get(0));
		CustomAssert.verifyEqual(prop.getProperty("mailId"), sourceDemography.get("email").get(0));
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		
	}

}
