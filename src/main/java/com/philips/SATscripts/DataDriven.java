package com.philips.SATscripts;

import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.philips.utils.ExcelUtil;
import com.philips.utils.LoadProperties;

public class DataDriven {

	String str = "C:/GUI-Automation/TestData.xlsx";
	@BeforeMethod
	public void beforeStep()
	{
		System.out.println("______________________________________________________________________________________________________");
	}
	@Test
	public void getHeaders() throws Exception {
		ExcelUtil.setExcelFile(str, "XDS-ProvideAndRegister_Patients");
		String arr[] = ExcelUtil.getHeaders();
		System.out.println("Length of array:" + arr.length);
		for (int i = 0; i < arr.length - 1; i++) {
			System.out.print(arr[i] + " - ");
		}
	}

	@Test
	public void getTestData() throws Exception {

		@SuppressWarnings("unused")
		String s = LoadProperties.TEST_DATA_DIR;
		System.out.println(LoadProperties.TEST_DATA_DIR);
		ExcelUtil.setExcelFile(str, "XDS-ProvideAndRegister_Patients");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(" " + ExcelUtil.getCellData(i, j));
			}
			System.out.println();
		}
	}

	@DataProvider
	public String[][] getData() {
		/*
		 * for (int i = 0; i < 10; i++) { for (int j = 0; j < 9; j++) {
		 * 
		 * } }
		 */
		String a[][] = { { "p1", "a1", "r1" }, { "p2", "a2", "r2" },
				{ "p3", "a3", "r3" } };
		return a;
	}

	@Test(dataProvider = "getData")
	public void testDataProvider(String a1, String a2, String a3) {
		System.out.println(a1 + "- " + a2 + "- " + a3);

	}

	@DataProvider
	public String[][] getPatientData() {
		String a[][] = new String[10][9];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				try {
					ExcelUtil.setExcelFile(str,
							"XDS-ProvideAndRegister_Patients");
					a[i][j] = ExcelUtil.getCellData(i, j);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return a;
	}

	@Test(dataProvider = "getPatientData")
	public void testPatientData(String... str) {
		for (String s : str) {
			System.out.print(s + "-");
		}
		System.out.println();
	}

}
