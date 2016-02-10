package com.philips.testscripts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
		{
		File file=new File("TestCase.properties");
		BufferedReader br=new BufferedReader(new FileReader(file));
		String line=br.readLine();
		StringBuilder sb=new StringBuilder();
		while(line!=null)
		{
			sb.append(line);
			sb.append(System.lineSeparator());
			line=br.readLine();
		}
		System.out.println(sb);
		sb.append("New Property=New Value");
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		bw.write(sb.toString());
		bw.flush();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

}
