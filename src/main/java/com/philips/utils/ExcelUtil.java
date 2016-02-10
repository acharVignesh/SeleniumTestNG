package com.philips.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static String excelFilePath;
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFCell cell;
	private static XSSFRow row;

	public static void setExcelFile(String Path, String sheetName)
			throws Exception {
		try {
			excelFilePath = Path;
			FileInputStream fis = new FileInputStream(new File(Path));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			Log.info("Excel file is set sucessfully:"
					+ workbook.getNumberOfSheets());
		} catch (FileNotFoundException e) {
			System.out.println("Exel File Not Found" + e);
			Log.error("Excel file not found" + e);
			throw e;
		} catch (Exception e) {
			System.out.println("Exception in setting excel file" + e);
			Log.error("Exception in setting excel file" + e);
			e.printStackTrace();
			throw e;
		}
	}

	public static String getCellData(int rowNum, int colNum) throws Exception {
		String cellData="";
		try {
			cell = sheet.getRow(rowNum).getCell(colNum);
			if(cell.getCellType()==0)
			{
				cellData=Double.toString(cell.getNumericCellValue());
			}
			else if(cell.getCellType()==1)
			{
				cellData=cell.getStringCellValue();
			}

		} catch (Exception e) {
			System.out.println("Exception in getting cell data" + e);
			Log.error("Exception in getting cell data" + e);
			e.printStackTrace();
			throw e;
		}
		return cellData;
	}

	public static void setCellData(String Result, int rowNum, int colNum) {
		try {
			cell = sheet.getRow(rowNum).getCell(colNum);
			if (cell == null) {
				cell = sheet.getRow(rowNum).createCell(colNum);
				cell.setCellValue(Result);
			} else {
				cell.setCellValue(Result);
			}
			FileOutputStream fout = new FileOutputStream(excelFilePath);
			workbook.write(fout);
			fout.flush();
			fout.close();
		} catch (Exception e) {
			Log.error("Error setting the result:" + e);
		}
	}
	public static String[] getHeaders()
	{
		String header[]=new String[0];
		int colNum=0;
		while(true)
		{	
			XSSFCell headerCell=sheet.getRow(0).getCell(colNum);
			if(headerCell==null)
			{
				break;
			}
			else
			{
				header=Arrays.copyOf(header, colNum+1);
				String cellData=headerCell.getStringCellValue();
				header[colNum]=cellData;
			}
			colNum=colNum+1;
		}
		return header;
	}
}
