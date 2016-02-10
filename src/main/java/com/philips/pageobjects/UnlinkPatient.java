package com.philips.pageobjects;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UnlinkPatient 
{
	WebDriver driver;
	WebDriverWait wait;
	By sourceRootId = By.id("sourcerootid");
	By sourceExtensionid=By.id("sourceextensionid");
	By search = By.name("search");
	By clear = By.id("clearButtonID");
	By unlink = By.id("UnLink");
	//By successMessage=By.id("successMessage");
	By successMessage=By.xpath("//*[@id='successmessage']");
	
	public UnlinkPatient(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, 10);
	}

	public void setSourceRootId(String empOid) 
	{
		driver.findElement(sourceRootId).clear();
		driver.findElement(sourceRootId).sendKeys(empOid);
	}

	public void setSourceExtensionId(String extensionId) 
	{
		driver.findElement(sourceExtensionid).clear();
		driver.findElement(sourceExtensionid).sendKeys(extensionId);
	}
	
	public void searchFragments() 
	{
		driver.findElement(search).click();
	}
	
	public void unlinkFragment() 
	{
		try
		{
		driver.findElement(unlink).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
		wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
		}
		catch(Exception e)
		{
			System.out.println("Unable to unlink the fragment:");
			e.printStackTrace();
		}
	}

	public void selectFragmentToUnLink(String root, String extension) 
	{
		String fragementRoot;
		String fragmentExtn;
		boolean flag=false;
		try 
		{
			int fragmentsNumber = driver.findElements(
					By.xpath("//table[@id='dataTable']/tbody/tr")).size();
			System.out.println("No of Fragment Rows:" + fragmentsNumber);
				for (int i = 1; i < fragmentsNumber; i++) 
				{
				String patientIdText = driver.findElement(
						By.xpath("//*[@id='dataTable']/tbody/tr[" + i
								+ "]/td[5]")).getText();
				System.out.println(patientIdText);
				fragementRoot = patientIdText.split("/")[0];
				fragmentExtn = patientIdText.split("/")[1];
					if (fragementRoot.equalsIgnoreCase(root)
						&& fragmentExtn.equalsIgnoreCase(extension)) 
					{
					flag=true;
					System.out.println("FragmentRoot:" + fragementRoot
							+ " Fragment Extension:" + fragmentExtn
							+ " Will be selected for unlink");
					driver.findElement(
							By.xpath("//*[@id='dataTable']/tbody/tr[" + i
									+ "]/td[1]/input")).click();

					}
				
				}
			if(!flag)
			{
				System.out.println("Fragement does not exist to select");
			}

		} 
		
		catch (Exception e) 
		{
			System.out.println("Exception Selecting the fragment:");
			e.printStackTrace();
		}

	}

	public Map<String, List<String>> getSourceAggregateDemographic()
	{
		Map<String, List<String>> sourcePatientDetailsMap=new HashMap<String,List<String>>();
		try
		{
			int fragmentsNumber = driver.findElements(
					By.xpath("//table[@id='dataTable']/tbody/tr")).size();
			String mainwindow=driver.getWindowHandle();
			System.out.println("Main Window:"+mainwindow);
			driver.findElement(
					By.xpath("//*[@id='dataTable']/tbody/tr[" + fragmentsNumber
							+ "]/td[7]")).click();
			for(String winHandle :driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle);
				System.out.println(driver.getTitle());
			}
						
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


public Map<String, List<String>> getTargetAggregateDemographic()
{
	Map<String, List<String>> targetPatientDetailsMap=new HashMap<String,List<String>>();
	try
	{
		int fragmentsNumber = driver.findElements(
				By.xpath("//table[@id='targetFragmentdataTable']/tbody/tr")).size();
		String mainwindow=driver.getWindowHandle();
		System.out.println("Main Window:"+mainwindow);
		driver.findElement(
				By.xpath("//*[@id='targetFragmentdataTable']/tbody/tr[" + fragmentsNumber
						+ "]/td[6]")).click();
		for(String winHandle :driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle);
			System.out.println("target aggregate::"+driver.getTitle());
		}
		
		/*Get all the demographic details from the Basic Details tab-->Start*/
		(wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("ui-id-2"))))).click();
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

}
