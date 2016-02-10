package com.philips.utils;

import java.io.File;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.philips.exceptions.IHEExceptions;

public class DriverConfig {
	static ProfilesIni profile = null;
	static FirefoxProfile ffprofile = null;
	private static WebDriver driver = null;
	private static String browser = null;

	private DriverConfig() {
	}

	public static WebDriver getDriver() throws Exception {
		if (driver == null)
			driver = DriverConfig.createDriver();
		return driver;
	}

	public static WebDriver createDriver() throws Exception {
		// TODO Auto-generated method stub
		Proxy proxy = null;
		LoadProperties.getProperties("IHE.properties");
		if ((LoadProperties.PROXY_URL != null)
				&& (LoadProperties.PROXY_URL.equals(""))) {
			proxy = new Proxy();
			proxy.setProxyAutoconfigUrl(LoadProperties.PROXY_URL);
		}
		browser = LoadProperties.BROWSER_NAME;
		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Testing in ForeFox browser");
			Log.info("Testing in ForeFox browser");
			profile = new ProfilesIni();
			ffprofile = profile.getProfile("SELENIUM");
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("proxy", proxy);
			cap.setJavascriptEnabled(true);
			driver = new FirefoxDriver(ffprofile);
			new ChromeDriver();
			new SafariDriver();
			new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Testing in Chrome browser");
			Log.info("Testing in Chrome browser");
			/*
			 * File f = new File(System.getProperty("user.dir") +
			 * System.getProperty("file.separator") +
			 * LoadProperties.BROWSER_SERVERS_DIR +
			 * System.getProperty("file.separator") + "chromedriver.exe");
			 */
			File f = new File(LoadProperties.BROWSER_SERVERS_DIR
					+ "chromedriver.exe");
			System.out.println(f.getAbsolutePath());
			if (!f.exists()) {
				throw new IHEExceptions(
						"Cannot launch Chrome browser, chromedriver.exe cannot be found in dir : "
								+ LoadProperties.BROWSER_SERVERS_DIR);
			} else {
				System.setProperty("webdriver.chrome.driver",
						f.getAbsolutePath());
				ChromeOptions options = new ChromeOptions();
				options.addArguments("user-data-dir="
						+ LoadProperties.CHROME_USR_DIR);
				options.addArguments("--start-maximized");
				options.addArguments("--use-fake-ui-for-media-stream");
				options.addArguments("--test-type");
				options.addArguments("detach=false");
				driver = new org.openqa.selenium.chrome.ChromeDriver(options);

			}
		} else {
			System.out.println("Testing in IE browser");
			Log.info("Testing in IE browser");
			File f = new File(System.getProperty("user.dir")
					+ System.getProperty("file.separator")
					+ LoadProperties.BROWSER_SERVERS_DIR
					+ System.getProperty("file.separator")
					+ "IEDriverServer.exe");
			if (!f.exists())
				throw new IHEExceptions(
						"Cannot launch Internet Explorer browser, IEDriverServer.exe cannot be found in dir : "
								+ LoadProperties.BROWSER_SERVERS_DIR);
			System.setProperty("webdriver.ie.driver", f.getAbsolutePath());
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			if (proxy != null)
				cap.setCapability("proxy", proxy);
			cap.setJavascriptEnabled(true);
			cap.setCapability("nativeEvents", true);
			cap.setCapability("requireWindowFocus", false);
			cap.setCapability("unexpectedAlertBehaviour",
					UnexpectedAlertBehaviour.IGNORE);
			driver = new InternetExplorerDriver(cap);
			driver.manage().window().maximize();
		}

		return driver;
	}

}