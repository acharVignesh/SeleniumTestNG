package com.philips.actionmodules;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.philips.exceptions.IHEExceptions;
import com.philips.pageobjects.HomePage;
import com.philips.pageobjects.LinkPatient;
import com.philips.pageobjects.LoginPage;
import com.philips.utils.DriverConfig;
import com.philips.utils.LoadProperties;
import com.philips.utils.Log;

public class LinkPatientTest {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	LinkPatient linkPatient;
	String sourceAggregateId;
	String targetAggregateId;
	String empiRoot;
	Properties prop;

	@Test
	public void searchFragment() throws Exception {
		sourceAggregateId = LoadProperties.SOURCE_AGGREGATE;
		targetAggregateId = LoadProperties.TARGET_AGGREGATE;
		empiRoot = LoadProperties.EMPI_ROOT;
		LoadProperties.getProperties();
		String URL = LoadProperties.BASE_URL;
		System.out.println(URL);
		Log.info("This is the search patient method");
		driver = DriverConfig.getDriver();
		linkPatient = new LinkPatient(driver);
		linkPatient.setSourceExtensonId(sourceAggregateId);
		linkPatient.setTargetExtensonId(targetAggregateId);
		linkPatient.setSourceRootId(empiRoot);
		linkPatient.searchFragments();
	}

	@Test
	public void linkFragments() throws Exception {
		// This method will select the fragment patient1IdExtension by default
		// to Link
		LoadProperties.getProperties();
		Properties testProperties = new Properties();
		FileInputStream fin = new FileInputStream(
				LoadProperties.TEST_PROPERTIES_FILE);
		testProperties.load(fin);
		Log.info("This is the link patient method");
		driver = DriverConfig.getDriver();
		linkPatient = new LinkPatient(driver);
		linkPatient.selectFragmentToLink(
				testProperties.getProperty("patient1IdRoot"),
				testProperties.getProperty("patient1IdExtension"));
		linkPatient.linkFragment();
	}

	@Test
	public void linkFragments(String root, String extention) throws Exception {
		// This method will select the fragment patient1IdExtension by default
		// to Link
		Log.info("Method to link the patient fragments");
		LoadProperties.getProperties();
		/*Properties testProperties = new Properties();
		FileInputStream fin = new FileInputStream(
				LoadProperties.TEST_PROPERTIES_FILE);
		testProperties.load(fin);*/
		driver = DriverConfig.getDriver();
		linkPatient = new LinkPatient(driver);
		linkPatient.selectFragmentToLink(root, extention);
		linkPatient.linkFragment();
		// Map<String, List<String>>
		// sourceDemographics=linkPatient.getSourceAggregateDemographic();
	}

	@Test
	public Map<String, List<String>> getSourceDemographics() {
		try {
			Log.info("Getting the Source aggregate demographics");
			driver = DriverConfig.getDriver();
			Map<String, List<String>> sourceDemographics = linkPatient
					.getSourceAggregateDemographic();
			return sourceDemographics;
		} catch (Exception e) {
			throw new IHEExceptions("Error getting demographics"
					+ e.getMessage());
		}

	}

	public Map<String, List<String>> getTargeteDemographics() {
		try {
			Log.info("Getting the Source aggregate demographics");
			driver = DriverConfig.getDriver();
			Map<String, List<String>> targetDemographics = linkPatient
					.getTargetAggregateDemographic();
			return targetDemographics;
		} catch (Exception e) {
			throw new IHEExceptions("Error getting demographics"
					+ e.getMessage());
		}

	}

}
