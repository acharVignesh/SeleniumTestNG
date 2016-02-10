package com.philips.customasserts;

import org.testng.Assert;
import org.testng.Reporter;

import com.philips.utils.Log;

public class CustomAssert extends Assert {
	public static void verifyEqual(String actual, String expected) {
		try {
			assertEquals(actual, expected);
		} catch (Error e) {
			Reporter.log("Error" + e.getLocalizedMessage());
			Log.error("Error" + e.getLocalizedMessage());
			throw (e);
		}
	}

}
