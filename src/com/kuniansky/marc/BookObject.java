/*
 * Created December 1, 2015 by Marc Kuniansky
 * 
 * Modifications
 * Date					Name				Modifications
 * December 1, 2015		Marc Kuniansky		Implemented the class. 
 * 											This class has many, many constructors to begin with. I felt it best to
 * 											start by giving as many constructors as possible for as many useful 
 * 											combinations of properties as possible. For example, a book object
 * 											does not need a loanedToDate without a person who the book was loaned to.
 * 											Basic implementation of getters and setters for all global variables.
 * 
 * Future stuff:
 * Right now this is a bit of a mess- we need to streamline the constructors so there aren't a zillion constructors. 
 * All of the getters and setters are all ready. 
 * Maybe the getters should throw custom exceptions if there is no value set for the variable being requested?
 */
package com.kuniansky.marc;

import java.util.Date;

/**
 * A book object has the following attributes: A name, id number (hopefully the serial number) and an author 
 * will be present for all books. Books will also have an owner and purchase date. They can also have a location
 * and current holder.
 * 
 * Constructors are available which allow you to set virtually all global variables when creating a BookObject. 
 * @author Marc Kuniansky
 *
 */
public class BookObject 
{ //Begin class
	//Global variables
	//All book objects MUST have a name, author, and isbn number.
	private String name;
	private String author;
	private int isbn;
	//The owner of a book is the person who purchased it, and the purchase date is self explanatory
	private String owner;
	private Date purchaseDate;
	//The location of a book is its location. This is highly customizable- the owner can make this whatever they want
	private String location;
	//The loanedTo variable is the name of a person to which a book has been loaned
	private String loanedTo;
	//The loanedToDate is the date on which the book was loaned to the person loanedTo
	private Date loanedToDate;
	//The number of times read is the number of times a book has been read
	private int timesRead;
	
	
	
	
	/*
	 * Have constructors for:
	 * name, author, isbn (required in all constructors) DONE
	 * owner DONE
	 * owner, purchaseDate DONE
	 * owner, location DONE
	 * owner, location, purchaseDate DONE
	 * owner, loanedTo DONE
	 * owner, loanedTo, loanedToDate DONE
	 * owner, location, loanedTo DONE
	 * owner, location, loanedTo, loanedToDate DONE
	 * owner, location, purchaseDate, loanedTo, loanedToDate DONE
	 */
	//Constructors
	/**
	 * Constructor which allows you to set the name, author, and ISBN of a book. 
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param bookISBN an int, the ISBN of the book
	 */
	public BookObject(String bookName, String bookAuthor, int bookISBN)
	{ //Begin constructor with 3 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
	} //End constructor with 3 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN and owner of a book.
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param bookISBN an int, the ISBN of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 */
	public BookObject(String bookName, String bookAuthor, int bookISBN, 
					  String ownerName)
	{ //Begin constructor with 4 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
	} //End constructor with 4 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN and owner of a book.
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param bookISBN an int, the ISBN of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 * @param numberTimesRead an int, the number of times the book has been read
	 */
	public BookObject(String bookName, String bookAuthor, int bookISBN,
					  String ownerName, int numberTimesRead)
	{ //Begin constructor with 5 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
		timesRead = numberTimesRead;
	} //End constructor with 5 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN, owner and purchase date of a book.
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param bookISBN an int, the ISBN of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 * @param datePurchased a Date, the date on which the book was purchased by the current owner
	 */
	public BookObject(String bookName, String bookAuthor, int bookISBN, 
					  String ownerName, Date datePurchased)
	{ //Begin constructor with 5 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
		purchaseDate = datePurchased;
	} //End constructor with 5 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN, owner and current location of a book.
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param bookISBN an int, the ISBN of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 * @param locationOfBook a String, the current location of the book
	 */
	public BookObject(String bookName, String bookAuthor, int bookISBN, 
					  String ownerName, String locationOfBook)
	{ //Begin constructor with 5 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
		location = locationOfBook;
	} //End constructor with 5 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN, owner, current location 
	 * and purchase date of a book.
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param bookISBN an int, the ISBN of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 * @param locationOfBook a String, the current location of the book
	 * @param datePurchased  a String, the date on which the book was purchased
	 */
	public BookObject(String bookName, String bookAuthor, int bookISBN, 
					  String ownerName, String locationOfBook, Date datePurchased)
	{ //Begin constructor with 6 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
		location = locationOfBook;
		purchaseDate = datePurchased;
	} //End constructor with 6 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN and owner of a book.
	 * @param bookISBN an int, the ISBN of the book
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 * @param personOwnedTo a String, the name of the person or entity to whom the book has been loaned
	 */
	public BookObject(int bookISBN, String bookName, String bookAuthor, 
					  String ownerName, String personLoanedTo)
	{ //Begin constructor with 4 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
		loanedTo = personLoanedTo;
	} //End constructor with 4 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN and owner of a book.
	 * @param bookISBN an int, the ISBN of the book
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 * @param personLoanedTo a String, the name of the person or entity to whom the book has been loaned
	 * @param dateLoaned a Date, the date on which the book was loaned to personLoanedTo
	 */
	public BookObject(int bookISBN, String bookName, String bookAuthor, 
					  String ownerName, String personLoanedTo, Date dateLoaned)
	{ //Begin constructor with 4 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
		loanedTo = personLoanedTo;
		loanedToDate = dateLoaned;
	} //End constructor with 4 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN and owner of a book.
	 * @param bookISBN an int, the ISBN of the book
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 * @param locationOfBook a String, the current location of the book.
	 * @param personLoanedTo a String, the name of the person or entity to whom the book has been loaned
	 */
	public BookObject(int bookISBN, String bookName, String bookAuthor, 
					  String ownerName, String locationOfBook, String personLoanedTo)
	{ //Begin constructor with 4 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
		location = locationOfBook;
		loanedTo = personLoanedTo;
	} //End constructor with 4 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN and owner of a book.
	 * @param bookISBN an int, the ISBN of the book
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 * @param locationOfBook a String, the current location of the book.
	 * @param personLoanedTo a String, the name of the person or entity to whom the book has been loaned
	 * @param dateLoaned a Date, the date on which the book was loaned to personLoanedTo
	 */
	public BookObject(int bookISBN, String bookName, String bookAuthor, 
					  String ownerName, String locationOfBook, String personLoanedTo, Date dateLoaned)
	{ //Begin constructor with 4 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
		location = locationOfBook;
		loanedTo = personLoanedTo;
		loanedToDate = dateLoaned;
	} //End constructor with 4 parameters
	
	/**
	 * Constructor which allows you to set the name, author, ISBN and owner of a book.
	 * @param bookISBN an int, the ISBN of the book
	 * @param bookName a String, the name of the book
	 * @param bookAuthor a String, the author of the book
	 * @param ownerName a String, the name of the person or entity that owns the book
	 * @param datePurchased a Date, the date on which the book was purchased
	 * @param locationOfBook a String, the current location of the book.
	 * @param personLoanedTo a String, the name of the person or entity to whom the book has been loaned
	 * @param dateLoaned a Date, the date on which the book was loaned to personLoanedTo
	 */
	public BookObject(int bookISBN, String bookName, String bookAuthor,
					  String ownerName, Date datePurchased, String locationOfBook, String personLoanedTo, Date dateLoaned)
	{ //Begin constructor with 4 parameters
		name = bookName;
		author = bookAuthor;
		isbn = bookISBN;
		owner = ownerName;
		purchaseDate = datePurchased;
		location = locationOfBook;
		loanedTo = personLoanedTo;
		loanedToDate = dateLoaned;
	} //End constructor with 4 parameters
	
	
	
	
	//Getters for global variables
	/**
	 * Gets the name of the book
	 * @return a String, the name of the book
	 */
	public String getBookName()
	{ //Begin getBookName
		return name;
	} //End getBookName
	
	/**
	 * Gets the name of the author of the book
	 * @return a String, the author of the book
	 */
	public String getAuthorName()
	{ //Begin getAuthorName
		return author;
	} //End getAuthorName
	
	/**
	 * Gets the ISBN of the book
	 * @return an int, the ISBN
	 */
	public int getISBN()
	{ //Begin getISBN
		return isbn;
	} //End getISBN
	
	/**
	 * Gets the name of the name of the owner of the book.
	 * @return a String, the name of the owner of the book
	 */
	public String getOwner()
	{ //Begin getOwner
			return owner;
	} //End getOwner
	
	/**
	 * Gets the date on which the book was purchased.
	 * @return a Date, the date on which the book was purchased
	 */
	public Date getPurchaseDate()
	{ //Begin getPurchaseDate
			return purchaseDate;
	} //End getPurchaseDate
	
	/**
	 * Gets the current location of the book
	 * @return a String, the location of the book
	 */
	public String getLocation()
	{ //Begin getLocation
		 return location;
	} //End getLocation
	
	/**
	 * Gets the name of the person to whom the book has been loaned
	 * @return a String, the name of the person to whom the book was loaned
	 */
	public String getPersonLoanedTo()
	{ //Begin getPersonLoanedTo
		return loanedTo;
	} //End getPersonLoanedTo
	
	/**
	 * Gets the date on which a book was loaned.
	 * @return a Date, the date on which the book was loaned
	 */
	public Date getDateLoaned()
	{ //Begin getDateLoaned
		return loanedToDate;
	} //End getDateLoaned
	
	/**
	 * Gets the number of times the book has been read
	 * @return an int, the number of times the book has been read
	 */
	public int getNumTimesRead()
	{ //Begin getNumTimesRead
		return timesRead;
	} //End getNumTimesRead
	
	
	
	//Setters for global variables
	/**
	 * Sets the name of the book to the desired name
	 * @param bookName a String, the desired name
	 */
	public void setBookName(String bookName)
	{ //Begin setBookName
		name = bookName;
	} //End setBookName
	
	/**
	 * Sets the name of the author of the book
	 * @param bookAuthor a String, the name of the author of the book
	 */
	public void setBookAuthor(String bookAuthor)
	{ //Begin setBookAuthor
		author = bookAuthor;
	} //End setBookAuthor
	
	/**
	 * Sets the ISBN of the book
	 * @param bookISBN an int, the desired ISBN
	 */
	public void setBookISBN(int bookISBN)
	{ //Begin setBookISBN
		isbn = bookISBN;
	} //End setBookISBN
	
	/**
	 * Sets the name of the owner of the book
	 * @param bookOwner a String, the owner of the book
	 */
	public void setBookOwner(String bookOwner)
	{ //Begin setBookOwner
		owner = bookOwner;
	} //End setBookOwner
	
	/**
	 * Set the date on which the book was purchased
	 * @param dateOfPurchase a Date, the date of purchase
	 */
	public void setBookPurchaseDate(Date dateOfPurchase)
	{ //Begin setBookPurchaseDate
		purchaseDate = dateOfPurchase;
	} //End setBookPurchaseDate
	
	/**
	 * Sets the current location of the book
	 * @param currentBookLocation a String, the current location of the book
	 */
	public void setCurrentBookLocation(String currentBookLocation)
	{ //Begin setCurrentBookLocation
		location = currentBookLocation;
	} //End setCurrentBookLocation
	
	/**
	 * Set the name of the person to whom the book was loaned
	 * @param personLoanedTo a String, the name of the person to whom the book was loaned
	 */
	public void setPersonLoanedTo(String personLoanedTo)
	{ //Begin setPersonLoanedTo
		loanedTo = personLoanedTo;
	} //End setPersonLoanedTo
	
	/**
	 * Sets the date on which the book was loaned
	 * @param dateLoaned a Date, the date on which the book was loaned
	 */
	public void setLoanDate(Date dateLoaned)
	{ //Begin setLoanDate
		loanedToDate = dateLoaned;
	} //End setLoanDate

	/**
	 * Set the number of times the book has been read
	 * @param numTimesRead an int, the number of times the book has been read
	 */
	public void setNumTimesRead(int numTimesRead)
	{ //Begin setNumTimesRead
		timesRead = numTimesRead;
	} //End setNumTimesRead
} //End class
