/*
 * Created December 1, 2015 by Marc Kuniansky
 * 
 * Modifications
 * Date					Name				Modifications
 * December 1, 2015		Marc Kuniansky		Implemented the constructor with no parameters
 * 
 * December 5, 2015		Marc Kuniansky		Implemented the methodTester method, which was made to test 
 * 											my methods. Modified the constructor to call methodTester when
 * 											used. The methodTester method will be constantly modified, and
 * 											explaining it here won't be helpful. It will not be in the final 
 * 											product.
 * 
 * Future stuff:
 * This hasn't actually been implemented yet. This class should be the one which makes everything work.
 * Think of this like the main method- lots of progressive steps which make the program run. 
 * After we get the basic functionality set up, we should refactor this into the GUI and have the GUI handle most
 * everything, I think. I could be wrong, though.
 */
package com.kuniansky.marc;

/**
 * This class handles all of the important functions behind the running of the program.
 * @author Marc Kuniansky
 *
 */
public class BookProgramWorker 
{ //Begin class

	//Constructor
	/**
	 * Constructor with no parameters
	 */
	public BookProgramWorker()
	{ //Begin constructor with no parameters
		//Run a few methods when this class is constructed
		this.methodTester();
	} //End constructor with no parameters
	
	/**
	 * A method for testing Marc's code, to make sure it all works as intended.
	 */
	private void methodTester()
	{ //Begin methodTester
		BookObject testBook = new BookObject("Name", "Author", 321, "Marc");
		DatabaseManager dbman = new DatabaseManager("localhost/books_database", "root", "3052809");
		dbman.dbInsertOneBook(testBook);
	} //End methodTester
} //End class
