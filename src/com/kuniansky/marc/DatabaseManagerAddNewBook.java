/**
 * Created December 5, 2015 by Marc Kuniansky
 * 
 * Modifications
 * Date					Name				Modifications
 * December 5, 2015		Marc Kuniansky		-Implemented the class. Gave it 3 global variables- url, 
 * 											user, and pass. Implemented a constructor. 
 * 											-Implemented the dbInsertOneBook method, which 
 * 											inserts a book to the database by following these steps:
 * 											1. Connect to the database via JDBC. 2. Getting all information 
 * 											from a BookObject passed as a parameter. 3. Feed the information
 * 											from the BookObject into SQL statements with JDBC to update
 * 											the database. Remember that BookObjects must have an isbn, name,
 * 											and author.
 * 											-IMPORTANT: All tables need to have the ISBN of a new book added. 
 * 											This is critical! They are relational databases.
 * 
 * December 6, 2015 AM	Marc Kuniansky		-Refactored the code responsible for making the SQL statements in
 * 											the dbInsertOneBook method to 3 separate methods, one for each
 * 											table in the database. As each table requires different parts,
 * 											each SQL insert statement needs to be made separately. 
 * 											Refactoring this part of the code will allow me to more easily
 * 											implement a menthod which takes several BookObjects at once and
 * 											injects them all into the database.
 * 
 * December 6, 2015 PM	Marc Kuniansky		-Added the dbInsertManyBooks method, which takes an ArrayList of
 * 											book objects as a parameter and inserts all of the books in the
 * 											list into the database.
 * 
 * December 8, 2015		Marc Kuniansky		-Created a new variable, DB_URL, to standardize the URL being used for each instance
 * 											of the class. Instantiated the variable in the constructor and utilize it in the 
 * 											dbInsertOneBook and dbInseryManyBooks methods. Removed the local variable DB_URL
 * 											from the two methods mentioned.
 */
package com.kuniansky.marc;

import java.sql.*;
import java.util.ArrayList;

import oracle.jdbc.driver.*;
/**
 * Contains methods for handling all INSERT INTO calls to the server. You must construct a new instance of this class for
 * each book you want to update. 
 * @author Marc Kuniansky
 *
 */
public class DatabaseManagerAddNewBook 
{ //Begin class
	//Global variables
	private static String url;
	private static String user;
	private static String pass;
	private Connection conn;
	private String DB_URL;
	//Constructors
	
	/**
	 * Constructs a DatabaseManager object.
	 * @param URL the URL of the database to be accessed
	 * @param username the username needed to access the database
	 * @param password the password needed to access the database
	 */
	public DatabaseManagerAddNewBook(String URL, String username, String password)
	{ //Begin constructor with 3 variables
		//Initialize instance variables
		url = URL;
		user = username;
		pass = password;
		DB_URL = "jdbc:mysql://"+url;
	} //End constructor with 3 variables
	
	//Public Methods
	
	/**
	 * Inserts a single book into the database.
	 * @param theBook a BookObject, the book to be inserted into the database.
	 */
	public void dbInsertOneBook(BookObject theBook)
	{ //Begin dbInsertOneBook
		//Try statement, need to catch a bunch of exceptions
		try
		{ //Begin try
			//Turn on Debug statement for testing
			Debug.turnOn();
			//Database URL
			Debug.println(DB_URL);
			//Tell the user that they are being connected
		    System.out.println("Connecting to a selected database...");
		    
		    //To open a connection with JDBC, you have to register a driver, then connect.
		    //Register the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
		    //Connect to the database
		    conn = DriverManager.getConnection(DB_URL, user, pass);
		    //Tell the user the connection succeeded if it did
		    System.out.println("Connected database successfully!");
		    
		    //Now to insert the BookObject into the database
		    //Debug line
		    Debug.println("Inserting records into the table...");
		    
		    //There are three tables in the database that need to have data added to them:
		    //name_list, book_info, and loan_info. Each table has a private method in this class
		    //which inserts the required data to each table.
		    //First, insert values into the name_list table, 
		    this.insertIntoNameList(theBook);

		    //Next, insert to the book_info table
		    this.insertIntoBookInfo(theBook);

		    //Finally, insert to the loan_info table
		    this.insertIntoLoanInfo(theBook);
		    
		    //Tell the user that the insert was completed.
		    System.out.println("Inserted records into the table.");

		   } //End try
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //Finally block used to close resources
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//End finally try
		   }//End try
		   System.out.println("Successfully added a book to the database.");
	} //End dbInsertOneBook

	/**
	 * Inserts an array list of books into the database. This will be useful for allowing the user to initialize the database
	 * by inserting all of their books at the same time. 
	 * @param bookList an ArrayList of BookObjects
	 */
	public void dbInsertManyBooks(ArrayList<BookObject> bookList)
	{ //Begin dbInsertManyBooks
		//Turn on Debug statement for testing
		Debug.turnOn();
		Debug.println(DB_URL);
		
		//Try statement, need to catch a bunch of exceptions
		try
		{ //Begin try
			
			//Tell the user that they are being connected
		    System.out.println("Connecting to a selected database...");
		    
		    //To open a connection with JDBC, you have to register a driver, then connect.
		    //Register the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
		    //Connect to the database
		    conn = DriverManager.getConnection(DB_URL, user, pass);
		    //Tell the user the connection succeeded if it did
		    System.out.println("Connected database successfully!");
		    
		    //Now to insert the BookObject into the database
		    //Debug line
		    Debug.println("Inserting records into the table...");
		    
		    //There are three tables in the database that need to have data added to them:
		    //name_list, book_info, and loan_info. Each table has a private method in this class
		    //which builds the SQL statements needed to add the required data to each table.
		    
		    //Use a loop to add every BookObject in the passed ArrayList into the database
		    for(BookObject book: bookList)
		    { //Begin for loop
		    	//First, insert values into the name_list table, 
		    	this.insertIntoNameList(book);
			    
			    //Next, insert to the book_info table
		    	this.insertIntoBookInfo(book);
			    
			    //Next, insert to the loan_info table
		    	this.insertIntoLoanInfo(book);
			    
			    //Finally, insert to the date_info table
			    this.insertIntoDateInfo(book);
		    } //End for loop
		    
		    //Tell the user that the insert was completed.
		    System.out.println("Inserted records into the table.");

		   } //End try
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //Finally block used to close resources
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//End finally try
		   }//End try
		   System.out.println("Goodbye!");
	} //End dbInsertManyBooks
	
	//Helping methods
	
	/**
	 * Executes the INSERT INTO statement that adds information about the BookObject into the 
	 * name_list table. 
	 * @param theBook a BookObject, the book being added to the database
	 */
	private void insertIntoNameList(BookObject theBook)
	{ //Begin insertToNameList
		try
		{ //Begin try
			//Build a Prepared Statement. These are really cool, they help prevent SQL injection by
			//Pre-loading a statement. Each question mark needs to be made something, and count up 
			//numerically left to right from 1. 
			//?1=isbn, ?2=name, ?3=author
			PreparedStatement statement = conn.prepareStatement("INSERT INTO name_list"
												+" VALUES(?,?,?);");
			//Variables for information to be used
		    //All BookObjects must have an isbn, name, and author. These must be present for this to work.
		    //The name_list table only contains the isbn, name, and author of a book.
		    int isbn = theBook.getISBN();
		    String name = theBook.getBookName();
		    String author = theBook.getAuthorName();
	
		    //Set the information needed in the PreparedStatement. 
		    statement.setInt(1, isbn);
		    statement.setString(2, name);
		    statement.setString(3, author);
		    Debug.println(statement.toString());
		    
		    //Execute the statement
		    statement.executeUpdate();
		} //End try
		catch(SQLException se)
		{ //Begin catch
			se.printStackTrace();
		} //End catch
	} //End insertIntoNameList
	
	
	/**
	 * Executes the INSERT INTO statement that adds information about the BookObject into the 
	 * book_info table.
	 * @param theBook a BookObject, the book being added to the database
	 */
	private void insertIntoBookInfo(BookObject theBook)
	{ //Begin buildBookInfoListSQL
		try
		{ //Begin try
			//Pepared statement to be executed. 1=isbn, 2=owner, 3=location, 4=timesRead
			PreparedStatement statement = conn.prepareStatement("INSERT INTO book_info"
																	+" VALUES(?,?,?,?);");
			//The book_info table contains the ISBN, owner, location, and times read
			int isbn = theBook.getISBN();
		    String owner = theBook.getOwner();
		    String location = theBook.getLocation();
		    int timesRead = theBook.getNumTimesRead();
	
		    statement.setInt(1, isbn);
		    statement.setString(2, owner);
		    statement.setString(3, location);
		    statement.setInt(4, timesRead);
		    Debug.println(statement.toString());
		    
		    //Execute the statement
		    statement.executeUpdate();
		} //End try
		catch(SQLException se)
		{ //Begin catch
			se.printStackTrace();
		} //End catch
	} //End buildBookInfoListSQL
	
	
	/**
	 * Builds the SQL statement needed to add information about a new book to the loan_info table.
	 * @param theBook a BookObject, the book being added to the database
	 * @return a String, the SQL statement to insert data into the loan_info table
	 */
	private void insertIntoLoanInfo(BookObject theBook)
	{ //Begin buildLoanInfoSQL
		try
		{ //Begin try statement
			//Prepared statement to be executed. ?1=isbn, ?2=loanedTo
			PreparedStatement statement = conn.prepareStatement("INSERT INTO loan_info"
																+" VALUES(?,?);");
			//The loan_info table holds the ISBN, person loaned to, and date loaned to
			int isbn = theBook.getISBN();
		    String loanedTo = theBook.getPersonLoanedTo();
	
		    //Add information to the PreparedStatement
		    statement.setInt(1, isbn);
		    statement.setString(2, loanedTo);
		    
		    //Execute the statement
		    statement.executeUpdate();
		} //End try statement
		catch(SQLException se)
		{ //Begin catch
			se.printStackTrace();
		} //End catch
	} //End buildLoanInfoSQL
	
	/**
	 * Builds the Insert Into statement for inserting data into the date_info table.
	 * @param theBook a BookObject, the book being added to the database
	 * @return a STring, the SQL statement for inserting into the date_info table
	 */
	private void insertIntoDateInfo(BookObject theBook)
	{ //Begin buildDateIngoSQL
		try
		{ //Begin try
			PreparedStatement statement = conn.prepareStatement("INSERT INTO date_info"
								+" VALUES(?,?,?,?);");
			//I have been having problems with Date objects- they are different in Java and SQL. I will 
			//have to modify this to work properly when I get them working.
			int isbn = theBook.getISBN();
			java.util.Date datePurchased = theBook.getPurchaseDate();
			java.util.Date dateLoanedTo = theBook.getDateLoaned();
			java.util.Date dateLoanedFrom = theBook.getLoanedFromDate();
			
			//Populate the statement with necessary information
			statement.setInt(1, isbn);
			//Because date objects are weird, we have to do this one conditionally. Null Dates cannot be
			//converted, that's why this is important.
			//These need to work in parallel, so they don't break.
			if(datePurchased!=null)
			{ 
				java.sql.Date purchaseDate = this.convertUtilDateToSQLDate(datePurchased);
				statement.setDate(2, purchaseDate);
			}
			else
			{
				statement.setDate(2,  null);
			}
			if(dateLoanedTo!=null)
			{
				java.sql.Date loanedToDate = this.convertUtilDateToSQLDate(dateLoanedTo);
				statement.setDate(3, loanedToDate);
			}
			else
			{
				statement.setDate(3, null);
			}
			if(dateLoanedFrom !=null)
			{
				java.sql.Date loanedFromDate = this.convertUtilDateToSQLDate(dateLoanedFrom);
				statement.setDate(4, loanedFromDate);
			}
			else
			{
				statement.setDate(4,  null);
			}
			
			Debug.println(statement.toString());
			
			//Execute the statement
			statement.executeUpdate();
		} //End try
		catch(SQLException se)
		{ //Begin catch
			se.printStackTrace();
		} //End catch
	} //End buildDateIngoSQL
	
	/**
	 * Converts a java.util.Date object to a java.sql.Date object.
	 * @param utilDate a java.util.Date, the date to be converted
	 * @return a java.sql.Date object, the converted date
	 */
	private java.sql.Date convertUtilDateToSQLDate(java.util.Date utilDate)
	{ //Begin convertUtilDateToSQLDate
		//Convert the date passed to the method to a java.sql.Date object
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		//Return the date
		return sqlDate;
	} //End convertUtilDateToSQLDate
} //End class
