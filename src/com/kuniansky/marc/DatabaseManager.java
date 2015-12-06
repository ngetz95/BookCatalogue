/**
 * Created December 5, 2015 by Marc Kuniansky
 * 
 * Modifications
 * Date					Name				Modifications
 * December 5, 2015		Marc Kuniansky		Implemented the class. Gave it 3 global variables- url, 
 * 											user, and pass. Implemented a constructor. 
 * 											Implemented the dbInsertOneBook method, which 
 * 											inserts a book to the database by following these steps:
 * 											1. Connect to the database via JDBC. 2. Getting all information 
 * 											from a BookObject passed as a parameter. 3. Feed the information
 * 											from the BookObject into SQL statements with JDBC to update
 * 											the database. Remember that BookObjects must have an isbn, name,
 * 											and author.
 */
package com.kuniansky.marc;

import java.sql.*;
import oracle.jdbc.driver.*;
/**
 * Handles 
 * @author Marc Kuniansky
 *
 */
public class DatabaseManager 
{ //Begin class
	//Global variables
	private static String url;
	private static String user;
	private static String pass;
	
	//Constructors
	
	/**
	 * Constructs a DatabaseManager object.
	 * @param URL the URL of the database to be accessed
	 * @param username the username needed to access the database
	 * @param password the password needed to access the database
	 */
	public DatabaseManager(String URL, String username, String password)
	{ //Begin constructor with 3 variables
		//Initialize instance variables
		url = URL;
		user = username;
		pass = password;
	} //End constructor with 3 variables

	//Methods
	
	/**
	 * Inserts a single book into the database.
	 * @param theBook a BookObject, the book to be inserted into the database.
	 */
	public void dbInsertOneBook(BookObject theBook)
	{
		//JDBC driver name and database URL
		final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
		final String DB_URL = "jdbc:mysql://"+url;
		System.out.println(DB_URL);
		
		//Create variables for the connection and the statement to be executed
		Connection conn = null;
		Statement statement = null;
		//Try statement, need to catch a bunch of exceptions
		try{
			//Print something to tell the user, for testing purposes
		      System.out.println("Connecting to a selected database...");
		      //Register the JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
		      //Open a connection
		      
		      //Connect to the database
		      conn = DriverManager.getConnection(DB_URL, user, pass);
		      //Tell the user the connection succeeded if it did
		      System.out.println("Connected database successfully...");
		      
		      //We want to insert into the database for this method.
		      //First, get all of the info possible from the BookObject fed to this method
		      
		      //Debug line
		      System.out.println("Inserting records into the table...");
		      //Create a statement with the connection
		      statement = conn.createStatement();
		      
		      //Variables for information to be used
		      //All BookObjects must have an isbn, name, and author. These must be present for this to work.
		      //First, variables needed for the name_list table, the primary table
		      int isbn = theBook.getISBN();
		      String name = theBook.getBookName();
		      String author = theBook.getAuthorName();
		      
		      //The rest of the possible variables are all optional. 
		      //Next, the variables for the book_info table
		      String owner = theBook.getOwner();
		      String location = theBook.getLocation();
		      //java.sql.Date purchaseDate = new java.sql.Date(theBook.getPurchaseDate().getTime());
		      int timesRead = theBook.getNumTimesRead();
		      
		      //Next, the variables for the loan_info table
		      String loanedTo = theBook.getPersonLoanedTo();
		      //java.sql.Date loanedToDate = new java.sql.Date(theBook.getDateLoaned().getTime());
		      
		      //Now, insert values into the name_list table, 
		      String sql = "INSERT INTO name_list "
		      				+ "VALUES (" + isbn + ", '" + name + "', '" + author + "');";
		      System.out.println(sql);
		      statement.executeUpdate(sql);
		      
		      //Next, insert to the book_info table
		      sql = "INSERT INTO book_info (isbn, owner, location, times_read)"
		      				+ " VALUES (" + isbn + ", '" + owner + "', '" + location 
		      				+ "', " + timesRead + ");";
		      System.out.println(sql);
		      statement.executeUpdate(sql);
		      
		      //Finally, insert to the loan_info table
		      sql = "INSERT INTO loan_info (isbn, loaned_to) "
	      				+ "VALUES (" + isbn + ", '" + loanedTo + "');";
		      System.out.println(sql);
		      statement.executeUpdate(sql);

		      System.out.println("Inserted records into the table...");

		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //Finally block used to close resources
		      try{
		         if(statement!=null)
		            conn.close();
		      }catch(SQLException se){
		      }//Do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//End finally try
		   }//End try
		   System.out.println("Goodbye!");
	}
} //End class
