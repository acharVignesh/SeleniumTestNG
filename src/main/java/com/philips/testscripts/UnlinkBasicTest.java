package com.philips.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.philips.pageobjects.HomePage;
import com.philips.pageobjects.LoginPage;
import com.philips.pageobjects.UnlinkPatient;
import com.philips.utils.DriverConfig;
import com.philips.utils.LoadProperties;
public class UnlinkBasicTest 
{
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	UnlinkPatient unlinkPat;
	String sourceAggregateId;
	String targetAggregateId;
	String empiRoot;
	Properties inputprop;

	@BeforeClass
	public void setUp() 
	{
		try 
		{
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
			inputprop=new Properties();
			inputprop.load(fin);
			System.out.println("**Set Up is Successfull**");
			 
		} 
		catch (Exception e) 
		{
			System.out.println("Exception While Setting Up*");
		}
	}

	@AfterClass
	public void tearDown() 
	{
		try 
		{
			Thread.sleep(5000);
			driver.quit();
			System.out.println("Completed Executing the Test Suit!!");
		} 
		catch (Exception e) 
		{
			System.out.println("Error while closing the browser");
		}
	}

	@BeforeMethod
	public void setAggregateID() 
	{
		sourceAggregateId = LoadProperties.SOURCE_AGGREGATE;
		targetAggregateId = LoadProperties.TARGET_AGGREGATE;
		empiRoot =LoadProperties.EMPI_ROOT;
	}

	@Test
	public void testUnLinkingSingleFragment() 
	{
		try 
		{
			homePage.navigateToUnLinkPatient();
			unlinkPat = new UnlinkPatient(driver);
			unlinkPat.setSourceExtensionId(sourceAggregateId);
			unlinkPat.setSourceExtensionId(targetAggregateId);
			unlinkPat.setSourceRootId(empiRoot);
			unlinkPat.searchFragments();
			unlinkPat.selectFragmentToUnLink(inputprop.getProperty("patient1IdRoot"),inputprop.getProperty("patient1IdExtension"));
			unlinkPat.unlinkFragment();
			Thread.sleep(2000);
			Map<String, List<String>> sourceMap = unlinkPat.getSourceAggregateDemographic();
			Thread.sleep(2000);
			Map<String, List<String>> targetMap = unlinkPat.getTargetAggregateDemographic();
			
			/*
			System.out.println("Source details start----------");
			System.out.println("Gender:"+sourceMap.get("gender").get(0));
			System.out.println("DOB:"+sourceMap.get("dob").get(0));
			System.out.println("Rootid:"+sourceMap.get("rootid").get(0));
			System.out.println("MMN:"+sourceMap.get("mmn").get(0));
			System.out.println("Aggregateid:"+sourceMap.get("aggregateId").get(0));
			
			System.out.println("First name size"+sourceMap.get("firstname").size());
			System.out.println("Last name size"+sourceMap.get("lastname").size());
			System.out.println("address size"+sourceMap.get("address").size());
			System.out.println("email size"+sourceMap.get("email").size());
			System.out.println("url size"+sourceMap.get("url").size());
			System.out.println("telephone size"+sourceMap.get("telephone").size());
						
			for(int i=0;i<sourceMap.get("firstname").size();i++)
			{
				System.out.println("Firstname:"+sourceMap.get("firstname").get(i));
				
			}
			for(int i=0;i<sourceMap.get("lastname").size();i++)
			{
				System.out.println("Lastname:"+sourceMap.get("lastname").get(i));
			}
			
			for(int i=0;i<sourceMap.get("address").size();i++)
			{
				System.out.println("Address:"+sourceMap.get("address").get(i));
			}
			
			for(int i=0;i<sourceMap.get("email").size();i++)
			{
				System.out.println("Email:"+sourceMap.get("email").get(i));
			}
			
			for(int i=0;i<sourceMap.get("url").size();i++)
			{
				System.out.println("Url:"+sourceMap.get("url").get(i));
			}
			
			for(int i=0;i<sourceMap.get("telephone").size();i++)
			{
				System.out.println("Telephone:"+sourceMap.get("telephone").get(i));
			}
			
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
*/
			
			Assert.assertEquals(targetMap.get("gender").get(0), inputprop.getProperty("patient1Gender"));
			Assert.assertEquals(targetMap.get("dob").get(0), inputprop.getProperty("patient1BirthTime"));
			Assert.assertEquals(targetMap.get("aggregateId").get(0), inputprop.getProperty("LoadProperties.SOURCE_AGGREGATE"),"Aggregateid assertion failing");
			
			
		} 
		catch (Exception e) 
		{
			System.out.println("Error in unlinking Patient");
		}
	}
}