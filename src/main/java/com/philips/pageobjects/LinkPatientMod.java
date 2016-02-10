package com.philips.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkPatientMod 

{
	WebDriver driver;
	WebDriverWait wait;
	By sourceRootId = By.id("sourcerootid");
	By sourceExtensionId = By.id("sourceextensionid");
	By targetExtensionId = By.id("targetextensionid");
	By search = By.name("search");
	By clear = By.id("clearButtonID");
	By link = By.id("link");
	By successMessage=By.id("successMessage");//check the success message
	public LinkPatientMod(WebDriver driver) 
	{
		this.driver = driver;
		wait=new WebDriverWait(driver, 10);
	}

	public void setSourceRootId(String empOid) {
		driver.findElement(sourceRootId).clear();
		driver.findElement(sourceRootId).sendKeys(empOid);
	}

	public void setSourceExtensionId(String extensionId) {
		driver.findElement(sourceExtensionId).clear();
		driver.findElement(sourceExtensionId).sendKeys(extensionId);
	}

	public void setTargetExtensionId(String extensionId) {
		driver.findElement(targetExtensionId).clear();
		driver.findElement(targetExtensionId).sendKeys(extensionId);
	}

	public void searchFragments() {
		driver.findElement(search).click();
	}

	public void linkFragment() {
		try{
		driver.findElement(link).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
		}
		catch(Exception e)
		{
			System.out.println("Unable to link the fragment:");
			e.printStackTrace();
		}
	}
	
	public Map<String, List<String>> getTargetAggregateDemographic()
	{
		Map<String, List<String>> targetPatientDetailsMap=new HashMap<String, List<String>>();
		try
		{
			int fragmentsNumber = driver.findElements(
					By.xpath("//table[@id='targetFragmentdataTable']/tbody/tr")).size();
			//fragmentsNumber=fragmentsNumber-1;
			String mainwindow=driver.getWindowHandle();
			System.out.println("Main Window:"+mainwindow);
			driver.findElement(
					By.xpath("//*[@id='targetFragmentdataTable']/tbody/tr[" + fragmentsNumber
							+ "]/td[6]")).click();
			for(String winHandle :driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
				System.out.println(driver.getTitle());
			}
			(wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("ui-id-2"))))).click();//Click the Basic Details Page-before capturing values
			List<WebElement> fnamelist = driver.findElements(By.xpath("//ul[@id='firstname']/li"));
			List<String> firstName=new ArrayList<String>();
			List<String> lastName=new ArrayList<String>();
			List<String> gender=new ArrayList<String>();
			List<String> dob=new ArrayList<String>();
			List<String> rootid=new ArrayList<String>();
			List<String> aggregateId=new ArrayList<String>();
			List<String> mmn=new ArrayList<String>();
			
			
			for(WebElement e: fnamelist)
			{
				//System.out.println("first"+e.getText());
				firstName.add(e.getText());
			}
			List<WebElement> lnamelist = driver.findElements(By.xpath("//ul[@id='lastname']/li"));
			
			for(WebElement d: lnamelist)
			{
				//System.out.println("last"+d.getText());
				lastName.add(d.getText());
			}
			
			String gendertemp=new String();
			gendertemp=driver.findElement(By.xpath("//span[@id='gender']")).getText();
			if(gendertemp.equals("Male"))
			{
				gender.add("M");
			}
			else if(gendertemp.equals("Female"))
			{
				gender.add("F");
			}
			dob.add(driver.findElement(By.xpath("//span[@id='dob']")).getText().replaceAll("-",""));
			rootid.add(driver.findElement(By.xpath("//span[@id='root']")).getText());
			aggregateId.add(driver.findElement(By.xpath("//span[@id='extension']")).getText());
			mmn.add(driver.findElement(By.xpath("//span[@id='mothersmaidenname']")).getText());
			/*Get all the demographic details from the Basic Details tab-->End*/
			
			/*Get all the details from the address tab-->Start*/
			driver.findElement(By.id("ui-id-3")).click();
			List<WebElement> addresslist = driver.findElements(By.xpath("//ul[@id='stuff']/li"));
			List<String> address=new ArrayList<String>();
			for(WebElement e: addresslist)
			{
				address.add(e.getText());
			}
			List<WebElement> emaillist = driver.findElements(By.xpath("//ul[@id='emailId']/li"));
			List<String> emailId=new ArrayList<String>();
			for(WebElement e: emaillist)
			{
				emailId.add(e.getText());
			}
			List<WebElement> urllist = driver.findElements(By.xpath("//ul[@id='urlText']/li"));
			List<String> url=new ArrayList<String>();
			for(WebElement e: urllist)
			{
				url.add(e.getText());
			}
			List<WebElement> telephonelist = driver.findElements(By.xpath("//ul[@id='telephoneId']/li"));
			List<String> telephone=new ArrayList<String>();
			for(WebElement e: telephonelist)
			{
				telephone.add(e.getText());
			}
			/*Get all the details from the address tab-->End*/
			
					
			/*Get all the details from the Other Identifiers tab-->Start*/
			driver.findElement(By.id("ui-id-4")).click();
			List <WebElement> Scopingorgtablelist=driver.findElements(By.xpath("//table[@id='oidTable']/tbody/tr"));
			List <String> scopingOid=new ArrayList<String>();
			List <String> AssigningAuthorityName=new ArrayList<String>();
			List <String> AssigningAuthorityExtension=new ArrayList<String>();
			
			if (!Scopingorgtablelist.isEmpty())
			{
				for(WebElement d: Scopingorgtablelist) 
				{
						List <WebElement> columns=d.findElements(By.tagName("td"));
						if (columns.get(0).getText().equals("No data available in table"))
						{
							break;
						}
						else
						{
							for(int i=0;i<=columns.size();i+=3)
							{
							scopingOid.add(columns.get(i).getText());
							AssigningAuthorityName.add(columns.get(i+1).getText());
							AssigningAuthorityExtension.add(columns.get(i+2).getText());
							}
						}
				}
			}	
			/*Get all the details from the Other Identifiers tab-->End*/
			
			driver.findElement(By.xpath("//button[@type='button']/span[@class='ui-button-icon-primary ui-icon ui-icon-closethick']")).click();
		
			
			targetPatientDetailsMap.put("firstname", firstName);
			targetPatientDetailsMap.put("lastname", lastName);
			targetPatientDetailsMap.put("gender", gender);
			targetPatientDetailsMap.put("dob", dob);
			targetPatientDetailsMap.put("rootid", rootid);
			targetPatientDetailsMap.put("aggregateId", aggregateId);
			targetPatientDetailsMap.put("mmn", mmn);
			targetPatientDetailsMap.put("address", address);
			targetPatientDetailsMap.put("email", emailId);
			targetPatientDetailsMap.put("url", url);
			targetPatientDetailsMap.put("telephone", telephone);
			targetPatientDetailsMap.put("scopingoid", scopingOid);
			targetPatientDetailsMap.put("assigningauthorityname", AssigningAuthorityName);
			targetPatientDetailsMap.put("assigningauthorityextension", AssigningAuthorityExtension);
			driver.switchTo().window(mainwindow);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting target Aggregate demographics");
			e.printStackTrace();
		}
		return targetPatientDetailsMap;
	}		
		

	public Map<String, List<String>> getSourceAggregateDemographic()
	{
		Map<String, List<String>> sourcePatientDetailsMap=new HashMap<String, List<String>>();
		try
		{
			int fragmentsNumber = driver.findElements(
					By.xpath("//table[@id='dataTable']/tbody/tr")).size();
			String mainwindow=driver.getWindowHandle();
			System.out.println("Main Window:"+mainwindow);
			driver.findElement(
					By.xpath("//*[@id='dataTable']/tbody/tr[" + fragmentsNumber
							+ "]/td[7]")).click();
			for(String winHandle :driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
				System.out.println(driver.getTitle());
			}
			(wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("ui-id-2"))))).click();//Click the Basic Details Page-before capturing values
			
			/*Get all the demographic details from the Basic Details tab-->Start*/
			List<WebElement> fnamelist = driver.findElements(By.xpath("//ul[@id='firstname']/li"));
			List<String> firstName=new ArrayList<String>();
			List<String> lastName=new ArrayList<String>();
			List<String> gender=new ArrayList<String>();
			List<String> dob=new ArrayList<String>();
			List<String> rootid=new ArrayList<String>();
			List<String> aggregateId=new ArrayList<String>();
			List<String> mmn=new ArrayList<String>();
			
			
			for(WebElement e: fnamelist)
			{
				firstName.add(e.getText());
			}
			List<WebElement> lnamelist = driver.findElements(By.xpath("//ul[@id='lastname']/li"));
			
			for(WebElement d: lnamelist)
			{
				lastName.add(d.getText());
			}
			
			String gendertemp=new String();
			gendertemp=driver.findElement(By.xpath("//span[@id='gender']")).getText();
			if(gendertemp.equals("Male"))
			{
				gender.add("M");
			}
			else if(gendertemp.equals("Female"))
			{
				gender.add("F");
			}
			
			dob.add(driver.findElement(By.xpath("//span[@id='dob']")).getText().replaceAll("-",""));
			rootid.add(driver.findElement(By.xpath("//span[@id='root']")).getText());
			aggregateId.add(driver.findElement(By.xpath("//span[@id='extension']")).getText());
			mmn.add(driver.findElement(By.xpath("//span[@id='mothersmaidenname']")).getText());
			/*Get all the demographic details from the Basic Details tab-->End*/
			
			/*Get all the details from the address tab-->Start*/
			driver.findElement(By.id("ui-id-3")).click();
			List<WebElement> addresslist = driver.findElements(By.xpath("//ul[@id='stuff']/li"));
			List<String> address=new ArrayList<String>();
			for(WebElement e: addresslist)
			{
				address.add(e.getText());
			}
			List<WebElement> emaillist = driver.findElements(By.xpath("//ul[@id='emailId']/li"));
			List<String> emailId=new ArrayList<String>();
			for(WebElement e: emaillist)
			{
				emailId.add(e.getText());
			}
			List<WebElement> urllist = driver.findElements(By.xpath("//ul[@id='urlText']/li"));
			List<String> url=new ArrayList<String>();
			for(WebElement e: urllist)
			{
				url.add(e.getText());
			}
			List<WebElement> telephonelist = driver.findElements(By.xpath("//ul[@id='telephoneId']/li"));
			List<String> telephone=new ArrayList<String>();
			for(WebElement e: telephonelist)
			{
				telephone.add(e.getText());
			}
			/*Get all the details from the address tab-->End*/
			
					
			/*Get all the details from the Other Identifiers tab-->Start*/
			driver.findElement(By.id("ui-id-4")).click();
			List <WebElement> Scopingorgtablelist=driver.findElements(By.xpath("//table[@id='oidTable']/tbody/tr"));
			List <String> scopingOid=new ArrayList<String>();
			List <String> AssigningAuthorityName=new ArrayList<String>();
			List <String> AssigningAuthorityExtension=new ArrayList<String>();
			
			if (!Scopingorgtablelist.isEmpty())
			{
				for(WebElement d: Scopingorgtablelist) 
				{
						List <WebElement> columns=d.findElements(By.tagName("td"));
						if (columns.get(0).getText().equals("No data available in table"))
						{
							break;
						}
						else
						{
							for(int i=0;i<=columns.size();i+=3)
							{
							scopingOid.add(columns.get(i).getText());
							AssigningAuthorityName.add(columns.get(i+1).getText());
							AssigningAuthorityExtension.add(columns.get(i+2).getText());
							}
						}
				}
			}	
			/*Get all the details from the Other Identifiers tab-->End*/
			
			driver.findElement(By.xpath("//button[@type='button']/span[@class='ui-button-icon-primary ui-icon ui-icon-closethick']")).click();
			
			sourcePatientDetailsMap.put("firstname", firstName);
			sourcePatientDetailsMap.put("lastname", lastName);
			sourcePatientDetailsMap.put("gender", gender);
			sourcePatientDetailsMap.put("dob", dob);
			sourcePatientDetailsMap.put("rootid", rootid);
			sourcePatientDetailsMap.put("aggregateId", aggregateId);
			sourcePatientDetailsMap.put("mmn", mmn);
			sourcePatientDetailsMap.put("address", address);
			sourcePatientDetailsMap.put("email", emailId);
			sourcePatientDetailsMap.put("url", url);
			sourcePatientDetailsMap.put("telephone", telephone);
			sourcePatientDetailsMap.put("scopingoid", scopingOid);
			sourcePatientDetailsMap.put("assigningauthorityname", AssigningAuthorityName);
			sourcePatientDetailsMap.put("assigningauthorityextension", AssigningAuthorityExtension);
			
			driver.switchTo().window(mainwindow);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting source Aggregate demographics");
			e.printStackTrace();
		}
		return sourcePatientDetailsMap;
	}

	public void selectFragmentToLink(String root, String extention) {
		String fragementRoot;
		String fragmentExtn;
		boolean flag=false;
		try {
			int fragmentsNumber = driver.findElements(
					By.xpath("//table[@id='dataTable']/tbody/tr")).size();
			System.out.println("No of source Fragment Rows:" + fragmentsNumber);
			for (int i = 1; i < fragmentsNumber; i++) {
				String patientIdText = driver.findElement(
						By.xpath("//*[@id='dataTable']/tbody/tr[" + i
								+ "]/td[5]")).getText();
				System.out.println(patientIdText);
				fragementRoot = patientIdText.split("/")[0];
				fragmentExtn = patientIdText.split("/")[1];
				if (fragementRoot.equalsIgnoreCase(root)
						&& fragmentExtn.equalsIgnoreCase(extention)) {
					flag=true;
					System.out.println("FragmentRoot:" + fragementRoot
							+ " Fragment Extention:" + fragmentExtn
							+ " Will be selected for link");
					driver.findElement(
							By.xpath("//*[@id='dataTable']/tbody/tr[" + i
									+ "]/td[1]/input")).click();

				}
				
			}
			if(!flag)
			{
				System.out.println("Fragement Does not exist to select");
			}

		} catch (Exception e) {
			System.out.println("Exception Selecting the fragment:");
			e.printStackTrace();
		}

	}

	public void selectFragmentToLink() {
		try {
			int fragmentsNumber = driver.findElements(
					By.xpath("//table[@id='dataTable']/tbody/tr")).size();
			System.out.println("No of source Fragment Rows:" + fragmentsNumber);
			for (int i = 0; i < fragmentsNumber; i++) {
				String patientIdText = driver.findElement(
						By.xpath("//*[@id='dataTable']/tbody/tr[1]/td[5]"))
						.getText();
				System.out.println(patientIdText);
			}
		} catch (Exception e) {
			System.out.println("Exception Selecting the fragment:");
			e.printStackTrace();
		}

	}
}
