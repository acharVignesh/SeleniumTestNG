package com.philips.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.philips.utils.Log;

public class LoginPage {
	WebDriver driver;
	By userName = By.id("userName");
	By password = By.id("password");
	By loginButton = By.cssSelector("input.loginSubmit");
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void setUserName(String strUserName) {
		// wait.until(ExpectedConditions.elementToBeSelected(userName));
		driver.findElement(userName).clear();
		driver.findElement(userName).sendKeys(strUserName);
	}

	public void setPassword(String strPassword) {
		// wait.until(ExpectedConditions.elementToBeSelected(password));
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(strPassword);
	}

	public void clickLogin() {
		(wait.until(ExpectedConditions.elementToBeClickable(driver
				.findElement(loginButton)))).click();
	}

	public void login(String userName, String password) {
		try {
			Log.info("Log-in to IHE GUI Application");
			setUserName(userName);
			setPassword(password);
			clickLogin();
		} catch (Exception e) {
			Log.error("Error in Logging in to IHE Application:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

}
