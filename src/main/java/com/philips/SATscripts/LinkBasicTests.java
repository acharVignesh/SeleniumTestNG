package com.philips.SATscripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.philips.customasserts.CustomAssert;
import com.philips.exceptions.IHEExceptions;
import com.philips.pageobjects.HomePage;
import com.philips.pageobjects.LinkPatient;
import com.philips.pageobjects.LoginPage;
import com.philips.utils.DriverConfig;
import com.philips.utils.LoadProperties;
import com.philips.utils.Log;

public class LinkBasicTests {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	LinkPatient linkPatient;
	String sourceAggregateId;
	String targetAggregateId;
	String empiRoot;
	Properties prop;

	@BeforeClass
	public void setUp() {
		try {
			LoadProperties.getProperties("IHE.properties");
			String URL = LoadProperties.BASE_URL;
			System.out.println(URL);
			driver = DriverConfig.createDriver();
			driver.get(URL);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			loginPage = new LoginPage(driver);
			homePage = new HomePage(driver);
			loginPage.login(LoadProperties.USERNAME, LoadProperties.PASSWORD);
			homePage.navigateToHomePage();
			FileInputStream fin=new FileInputStream(new File("TestCase.properties"));
			prop=new Properties();
			prop.load(fin);
			System.out.println("**Set Up is Successfull**");
			 
		} catch (Exception e) {
			System.out.println("Exception While Setting Up*");
		}
	}

	@AfterClass
	public void tearDown() {
		try {
			Thread.sleep(5000);
			driver.quit();
			System.out.println("Completed Executing the Test Suit!!");
		} catch (Exception e) {
			System.out.println("Error while closing the browser");
		}
	}

	@BeforeMethod
	public void setAggregateID() {
		sourceAggregateId = LoadProperties.SOURCE_AGGREGATE;
		targetAggregateId = LoadProperties.TARGET_AGGREGATE;
		empiRoot =LoadProperties.EMPI_ROOT;
	}

	@Test
	public void testLinkingSingleFragment() {
		try {
			Log.startTestCase("testLinkingSingleFragment");
			System.out.println(prop.getProperty("patient2NameFamily"));
			System.out.println("Link Test 1");
			homePage.navigateToLinkPatient();
			linkPatient = new LinkPatient(driver);
			linkPatient.setSourceExtensonId(sourceAggregateId);
			linkPatient.setTargetExtensonId(targetAggregateId);
			linkPatient.setSourceRootId(empiRoot);
			linkPatient.searchFragments();
			/*Map<String,String> resultMap = linkPatient.getSourceAggregateDemographic();
			for (String pointer : resultMap.keySet()) 
			{
				System.out.println(pointer+ ":"
						+ resultMap.get(pointer));
			}*/
			/*CustomAssert.verifyEqual(resultMap.get("firstname"), prop.getProperty("patient1NameFamily"));
			CustomAssert.verifyEqual(resultMap.get("lastname"), prop.getProperty("patient1NameFamily"));
			CustomAssert.verifyEqual(resultMap.get("gender"), prop.getProperty("patient1Gender"));
			CustomAssert.verifyEqual(resultMap.get("dob"), prop.getProperty("patient1BirthTime"));*/
			linkPatient.selectFragmentToLink(prop.getProperty("patient1IdRoot"),prop.getProperty("patient1IdExtension"));
			linkPatient.linkFragment();
			Map<String, List<String>> targetDemography=linkPatient.getTargetAggregateDemographic();
			
			for (String pointer : targetDemography.keySet()) 
			{
				System.out.println(pointer+ ":"
						+ targetDemography.get(pointer));
			}
			Log.endTestCase("testLinkingSingleFragment");
		} catch (Exception e) {
			System.out.println("Error in linking Patient");
		}
	}

}
