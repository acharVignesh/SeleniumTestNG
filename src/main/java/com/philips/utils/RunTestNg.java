package com.philips.utils;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.thoughtworks.selenium.webdriven.commands.GetXpathCount;

public class RunTestNg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestNG testNG=new TestNG();
		XmlSuite xmlSuite=new XmlSuite();
		xmlSuite.setName("My Sample Suite");
		XmlTest myXmlTest=new XmlTest(xmlSuite);
		myXmlTest.setName("Sample Test");
		List<XmlClass> myClasses=new ArrayList<XmlClass>();
		myClasses.add(new XmlClass("com.philips.actionmodules.SignInAction"));
		myXmlTest.setXmlClasses(myClasses);
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		myTests.add(myXmlTest);
		xmlSuite.setTests(myTests);
		
		List<XmlSuite> mySuites=new ArrayList<XmlSuite>();
		mySuites.add(xmlSuite);
		
		testNG.setXmlSuites(mySuites);
		testNG.run();
		//myClasses.remove(0);
		myClasses.add(new XmlClass("com.philips.actionmodules.LinkPatientTest"));
		/*myXmlTest.setXmlClasses(myClasses);
		myTests.add(myXmlTest);
		xmlSuite.setTests(myTests);
		mySuites.remove(0);
		mySuites.add(xmlSuite);
		testNG.setXmlSuites(mySuites);*/
		testNG.run();
		
		
		
	}

}
