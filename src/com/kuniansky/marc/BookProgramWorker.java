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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		BookObject testBook = new BookObject(987655678, "Name", "Author");
		BookObject testBook2 = new BookObject(45678, "TestName", "Noone Wrote This");
		BookObject testBook3 = new BookObject (12345, "Fuck this book", "Who cares about it?");
		
		DatabaseManagerAddNewBook dbman = new DatabaseManagerAddNewBook
											("localhost/books_database", "root", "");
		dbman.dbInsertOneBook(testBook);
		//dbman.dbInsertManyBooks(bookList);
		
		//I need to make some dates for testing
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		Date purchaseDate = null;
		Date loanedToDate = null;
		Date loanedFromDate = null;
		try {
			purchaseDate = format.parse("12/05/1994");
			loanedToDate = format.parse("18/12/2005");
			loanedFromDate = format.parse("05/09/2014");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Debug.turnOn();
		//Let's test all of the setters got the BookObject class, then all of the updaters 
		testBook.setBookName("A name for the book");
		Debug.println("Set the book name");
		testBook.setBookAuthor("Some dude");
		Debug.println("Set the book author");
		testBook.setBookOwner("Me");
		Debug.println("Set the book owner");
		testBook.setBookPurchaseDate(purchaseDate);
		Debug.println("Set the book purchase date");
		testBook.setLoanDate(loanedToDate);
		Debug.println("Set the book loan to date");
		testBook.setDateLoanedFrom(loanedFromDate);
		Debug.println("Set the book loaned from date");
		testBook.setPersonLoanedTo("Ian");
		Debug.println("Set the person the book was loaned to");
		testBook.setCurrentBookLocation("Home");
		Debug.println("Set the location of the book");
		testBook.setNumTimesRead(10);
		Debug.println("Set the number of times read");
		
		DatabaseManagerUpdateBook updateBook = new DatabaseManagerUpdateBook("localhost/books_database", "root", "", testBook);
		Debug.println("Connected to the DatabaseManagerUpdateBook class");
		updateBook.updateNameListName(testBook.getBookName());
		Debug.println("Updated the name_list NAME in the database");
		updateBook.updateNameListAuthor(testBook.getAuthorName());
		Debug.println("Updated the name_list AUTHOR in the database");
		updateBook.updateBookInfoOwner(testBook.getOwner());
		Debug.println("Updated the book_info OWNER in the database");
		updateBook.updateBookInfoLocation(testBook.getLocation());
		Debug.println("Updated the book_info LOCATION in the database");
		updateBook.updateBookInfoTimesRead(testBook.getNumTimesRead());
		Debug.println("Updated the book_info TIMES READ in the database");
		updateBook.updateLoanInfoLoanedTo(testBook.getPersonLoanedTo());
		Debug.println("Updated the loan_info PERSON LOANED TO in the database");
		updateBook.updateDateInfoPurchaseDate(testBook.getPurchaseDate());
		Debug.println("Updated the date_info PURCHASE DATE in the database");
		updateBook.updateDateInfoLoanedToDate(testBook.getDateLoaned());
		Debug.println("Updated the date_info LOANED TO DATE in the database");
		updateBook.updateDateInfoLoanedFromDate(testBook.getLoanedFromDate());
		Debug.println("Updated the date_info LOANED FROM DATE in the database");
		updateBook.closeConnection();
		
		
		
	} //End methodTester
} //End class
