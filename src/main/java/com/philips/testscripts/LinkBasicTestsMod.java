package com.philips.testscripts;

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

import com.philips.pageobjects.HomePage;
import com.philips.pageobjects.LinkPatientMod;
import com.philips.pageobjects.LoginPage;
import com.philips.utils.DriverConfig;
import com.philips.utils.LoadProperties;

public class LinkBasicTestsMod 
{

	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	LinkPatientMod linkPatient;
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
			homePage.navigateToLinkPatient();
			linkPatient = new LinkPatientMod(driver);
			linkPatient.setSourceExtensionId(sourceAggregateId);
			linkPatient.setTargetExtensionId(targetAggregateId);
			linkPatient.setSourceRootId(empiRoot);
			linkPatient.searchFragments();
			linkPatient.selectFragmentToLink(prop.getProperty("patient1IdRoot"),prop.getProperty("patient1IdExtension"));
			linkPatient.linkFragment();
			Thread.sleep(2000);
			Map<String, List<String>> targetMap = linkPatient.getTargetAggregateDemographic();
			
			System.out.println("Target details start------------");
			System.out.println("Gender:"+targetMap.get("gender").get(0));
			System.out.println("DOB:"+targetMap.get("dob").get(0));
			System.out.println("Rootid:"+targetMap.get("rootid").get(0));
			System.out.println("MMN:"+targetMap.get("mmn").get(0));
			System.out.println("Aggregateid:"+targetMap.get("aggregateId").get(0));
			
			for(int i=0;i<targetMap.get("firstname").size();i++)
			{
				System.out.println("Firstname:"+targetMap.get("firstname").get(i));
				
			}
			for(int i=0;i<targetMap.get("lastname").size();i++)
			{
				System.out.println("Lastname:"+targetMap.get("lastname").get(i));
			}
			
			for(int i=0;i<targetMap.get("address").size();i++)
			{
				System.out.println("Address:"+targetMap.get("address").get(i));
			}
			
			for(int i=0;i<targetMap.get("email").size();i++)
			{
				System.out.println("Email:"+targetMap.get("email").get(i));
			}
			
			for(int i=0;i<targetMap.get("url").size();i++)
			{
				System.out.println("Url:"+targetMap.get("url").get(i));
			}
			
			for(int i=0;i<targetMap.get("telephone").size();i++)
			{
				System.out.println("Telephone:"+targetMap.get("telephone").get(i));
			}

		
		} catch (Exception e) {
			System.out.println("Error in linking Patient");
		}
	}
}
