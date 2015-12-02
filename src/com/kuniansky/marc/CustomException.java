package com.kuniansky.marc;
/*
 * Created November 28, 2015 by Marc Kuniansky
 * 
 * Modifications
 * Date					Name					Modifications
 * November 28, 2015	Marc Kuniansky			Implemented the class. This class allows a programmer
 * 												to throw a custom exception
 * 
 * Notes:
 * This allows custom exceptions to be thrown. It's really easy to use- just have your method throw a CustomException
 * in its declaration, and somewhere in the method include an if statement which tells it when to throw the exception.
 */

/**
 * Allows a custom exception to be thrown where needed.
 * @author Marc Kuniansky
 *
 */
public class CustomException extends Exception
{ //Begin class
	/**
	 * Constructor with one variable. When constructing a custom exception, 
	 * @param message
	 */
	public CustomException(String message)
	{
		super(message);
	}
} //End class
