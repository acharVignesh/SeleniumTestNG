package com.philips.exceptions;

import com.philips.utils.Log;

public class IHEExceptions extends RuntimeException
{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -5009425716565526689L;
	private String message;
	
	public IHEExceptions()
	{
		System.out.println("Error Occured in performing required operation");
		Log.error("Error Occured in performing required operation");
	}
	public IHEExceptions(Exception e)
	{
		super(e);
	}
	public IHEExceptions(String message)
	{
		System.out.println(message);
		Log.error(message);
	}
	
	@Override
	public String toString()
	{
		return message;
	}

}
