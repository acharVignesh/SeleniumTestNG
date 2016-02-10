package com.philips.SATscripts;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;

import com.philips.actionmodules.LinkPatientTest;
import com.philips.actionmodules.SignInAction;
import com.philips.customasserts.CustomAssert;

public class TestScripts {
	@Test
	public void test1()
	{
		CustomAssert.verifyEqual("pass", "pass");
	}
	@Test
	public void test2()
	{
		CustomAssert.verifyEqual("Hello", "HELLO");
	}
	@Test
	public void test3()
	{
		CustomAssert.verifyEqual("pass", "fail");
	}
	
	/*@Test
	public void signiInStep(){
	TestListenerAdapter tla = new TestListenerAdapter();
	TestNG testng = new TestNG();
	testng.setTestClasses(new Class[] {SignInAction.class});
	testng.addListener(tla);
	testng.run(); 
	}
	@Test
	public void linkingFragmentsStep(){
	TestListenerAdapter tla = new TestListenerAdapter();
	TestNG testng = new TestNG();
	testng.setTestClasses(new Class[] {LinkPatientTest.class});
	testng.addListener(tla);
	testng.run(); 
	}*/
	

}
