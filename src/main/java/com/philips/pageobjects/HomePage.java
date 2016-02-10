package com.philips.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.philips.utils.Log;

public class HomePage {
	private WebDriver driver;
	WebDriverWait wait;

	public HomePage() {
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void navigateToLinkPatient() {
		try {
			// navigateToHomePage(driver);
			wait.until(
					ExpectedConditions.elementToBeClickable(driver
							.findElement(By.id("linkFragmentPage")))).click();
			System.out.println("Navigating to Patient Link Page");
			Log.info("Navigating to Patient Link Page");
		} catch (Exception e) {
			System.out.println("Error Navigating To Link Page");
			Log.error("Error Navigating To Link Page:"+e.getMessage());
			e.printStackTrace();
		}

	}

	public void navigateToUnLinkPatient() {
		try {
			// navigateToHomePage(driver);
			wait.until(
					ExpectedConditions.elementToBeClickable(driver
							.findElement(By.id("unlinkFragmentPage")))).click();
			System.out.println("Navigating to Patient UnLink Page");
			Log.info("Navigating to Patient UnLink Page");
		} catch (Exception e) {
			System.out.println("Error Navigating To UnLink Page");
			Log.error("Error Navigating To UnLink Page:"+e.getMessage());
			e.printStackTrace();
		}

	}

	public void navigateToHomePage() {
		try {
			wait.until(
					ExpectedConditions.elementToBeClickable(driver
							.findElement(By.id("homePage")))).click();
			System.out.println("Navigating to Home Page");
		} catch (Exception e) {
			System.out.println("Error Navigating to home page");
			Log.error("Error Navigating to home page:"+e.getMessage());
			e.printStackTrace();
		}
	}
}
