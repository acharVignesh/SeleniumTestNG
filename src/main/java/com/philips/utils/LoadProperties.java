package com.philips.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.philips.exceptions.IHEExceptions;

public class LoadProperties {
	public static String TEST_DATA_DIR = null;
	public static String DATA_FILE = null;
	public static String TIME_OUT = null;
	public static String BROWSER_NAME = null;
	public static String BROWSER_SERVERS_DIR = null;
	public static String PROXY_URL = null;
	public static String SCRSHOT_PATH = null;
	public static String BASE_URL = null;
	public static String USERNAME = null;
	public static String PASSWORD = null;
	public static String CHROME_USR_DIR;
	public static String SOURCE_AGGREGATE = null;
	public static String TARGET_AGGREGATE = null;
	public static String EMPI_ROOT = null;
	public static String TEST_PROPERTIES_FILE = null;

	public static void getProperties() {
		// File file = new File(propertiesFile);
		File file = new File("C:/GUI-Automation/IHE.properties");
		if (!file.exists()) {
			Log.info("File Does Not Exist");
			throw new IHEExceptions("file does not exist");
		}
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new IHEExceptions("Error loading file : ");
		} catch (IOException e) {
			throw new IHEExceptions("Error loading file : ");
		}

		TEST_DATA_DIR = prop.getProperty("TEST_DATA_DIR");
		if ((TEST_DATA_DIR == null) || (TEST_DATA_DIR.equals(""))) {
			throw new IHEExceptions("TEST_DATA_DIR property is not defined");
		}

		SCRSHOT_PATH = prop.getProperty("SCRSHOT_PATH");
		if ((SCRSHOT_PATH == null) || (SCRSHOT_PATH.equals(""))) {
			throw new IHEExceptions("SCRSHOT_PATH property is not defined");
		}

		BROWSER_NAME = prop.getProperty("BROWSER_NAME");
		if ((BROWSER_NAME == null) || (BROWSER_NAME.equals(""))) {
			throw new IHEExceptions("BROWSER_NAME property is not defined");
		}
		TIME_OUT = prop.getProperty("TIME_OUT");
		if ((TIME_OUT == null) || (TIME_OUT.equals(""))) {
			TIME_OUT = "60";
			System.out
					.println("TIME_OUT property is not defined, setting it to 60sec");
		}
		BASE_URL = prop.getProperty("BASE_URL");
		if ((BASE_URL == null) || (BASE_URL.equals(""))) {
			TIME_OUT = "60";
			System.out
					.println("TIME_OUT property is not defined, setting it to 60sec");
		}
		USERNAME = prop.getProperty("USERNAME");
		PASSWORD = prop.getProperty("PASSWORD");

		DATA_FILE = prop.getProperty("DATA_FILE");

		BROWSER_SERVERS_DIR = prop.getProperty("BROWSER_SERVERS_DIR");
		if ((BROWSER_SERVERS_DIR == null) || (BROWSER_SERVERS_DIR.equals(""))) {
			throw new IHEExceptions(
					"BROWSER_SERVERS_DIR property is not defined");
		}
		CHROME_USR_DIR = prop.getProperty("CHROME_USR_DIR");
		if (CHROME_USR_DIR == null) {
			throw new IHEExceptions("Chrom User Directory is not been Set");
		}
		SOURCE_AGGREGATE = prop.getProperty("SOURCE_AGGREGATE");
		if (SOURCE_AGGREGATE == null) {
			throw new IHEExceptions("Source Aggregate Id is Null");
		}
		TARGET_AGGREGATE = prop.getProperty("TARGET_AGGREGATE");
		if (TARGET_AGGREGATE == null) {
			throw new IHEExceptions("Target Aggregate Id is Null");
		}
		EMPI_ROOT = prop.getProperty("EMPI_ROOT");
		if (EMPI_ROOT == null) {
			throw new IHEExceptions("EMPi Root is Null");
		}
	}

	public static void getProperties(String propertiesFile) {
		// File file = new File(propertiesFile);
		File file = new File("C:/GUI-Automation/IHE.properties");
		if (!file.exists()) {
			Log.info("File Does Not Exist");
			throw new IHEExceptions(propertiesFile + ": file does not exist");
		}
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new IHEExceptions("Error loading file : " + propertiesFile);
		} catch (IOException e) {
			throw new IHEExceptions("Error loading file : " + propertiesFile);
		}

		TEST_DATA_DIR = prop.getProperty("TEST_DATA_DIR");
		if ((TEST_DATA_DIR == null) || (TEST_DATA_DIR.equals(""))) {
			throw new IHEExceptions("TEST_DATA_DIR property is not defined");
		}

		SCRSHOT_PATH = prop.getProperty("SCRSHOT_PATH");
		if ((SCRSHOT_PATH == null) || (SCRSHOT_PATH.equals(""))) {
			throw new IHEExceptions("SCRSHOT_PATH property is not defined");
		}

		BROWSER_NAME = prop.getProperty("BROWSER_NAME");
		if ((BROWSER_NAME == null) || (BROWSER_NAME.equals(""))) {
			throw new IHEExceptions("BROWSER_NAME property is not defined");
		}
		TIME_OUT = prop.getProperty("TIME_OUT");
		if ((TIME_OUT == null) || (TIME_OUT.equals(""))) {
			TIME_OUT = "60";
			System.out
					.println("TIME_OUT property is not defined, setting it to 60sec");
		}
		BASE_URL = prop.getProperty("BASE_URL");
		if ((BASE_URL == null) || (BASE_URL.equals(""))) {
			TIME_OUT = "60";
			System.out
					.println("TIME_OUT property is not defined, setting it to 60sec");
		}
		USERNAME = prop.getProperty("USERNAME");
		PASSWORD = prop.getProperty("PASSWORD");

		DATA_FILE = prop.getProperty("DATA_FILE");

		BROWSER_SERVERS_DIR = prop.getProperty("BROWSER_SERVERS_DIR");
		if ((BROWSER_SERVERS_DIR == null) || (BROWSER_SERVERS_DIR.equals(""))) {
			throw new IHEExceptions(
					"BROWSER_SERVERS_DIR property is not defined");
		}
		CHROME_USR_DIR = prop.getProperty("CHROME_USR_DIR");
		if (CHROME_USR_DIR == null) {
			throw new IHEExceptions("Chrom User Directory is not been Set");
		}
		SOURCE_AGGREGATE = prop.getProperty("SOURCE_AGGREGATE");
		if (SOURCE_AGGREGATE == null) {
			throw new IHEExceptions("Source Aggregate Id is Null");
		}
		TARGET_AGGREGATE = prop.getProperty("TARGET_AGGREGATE");
		if (TARGET_AGGREGATE == null) {
			throw new IHEExceptions("Target Aggregate Id is Null");
		}
		EMPI_ROOT = prop.getProperty("EMPI_ROOT");
		if (EMPI_ROOT == null) {
			throw new IHEExceptions("EMPi Root is Null");
		}
		TEST_PROPERTIES_FILE = prop.getProperty("TEST_PROPERTIES_FILE");
		if (TEST_PROPERTIES_FILE == null) {
			throw new IHEExceptions(
					"Test Case Properties file directory is null");
		}
		TEST_DATA_DIR=prop.getProperty("TEST_DATA_DIR");
		if(TEST_DATA_DIR==null)
		{
			throw new IHEExceptions("TEST_DATA_DIR property is null");
		}
	}

}
