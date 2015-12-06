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
 * 											IMPORTANT: All tables need to have the ISBN of a new book added. 
 * 											This is critical!
 * 
 * December 6, 2015 AM	Marc Kuniansky		Refactored the code responsible for making the SQL statements in
 * 											the dbInsertOneBook method to 3 separate methods, one for each
 * 											table in the database. As each table requires different parts,
 * 											each SQL insert statement needs to be made separately. 
 * 											Refactoring this part of the code will allow me to more easily
 * 											implement a menthod which takes several BookObjects at once and
 * 											injects them all into the database.
 */
package com.kuniansky.marc;

import java.sql.*;
import java.util.ArrayList;

import oracle.jdbc.driver.*;
/**
 * Contains methods for handling all INSERT INTO calls to the server. 
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
	{ //Begin dbInsertOneBook
		//Database URL
		final String DB_URL = "jdbc:mysql://"+url;
		Debug.println(DB_URL);
		
		//Create variables for the connection and the statement to be executed
		Connection conn = null;
		Statement statement = null;
		
		//Try statement, need to catch a bunch of exceptions
		try
		{ //Begin try
			//Turn on Debug statement for testing
			Debug.turnOn();
			
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

		    //Create a statement with the connection
		    statement = conn.createStatement();
		    
		    //There are three tables in the database that need to have data added to them:
		    //name_list, book_info, and loan_info. Each table has a private method in this class
		    //which builds the SQL statements needed to add the required data to each table.
		    
		    //First, insert values into the name_list table, 
		    String sql = this.buildNameListSQL(theBook);
		    Debug.println(sql);
		    statement.executeUpdate(sql);

		    //Next, insert to the book_info table
		    sql = this.buildBookInfoListSQL(theBook);
		    Debug.println(sql);
		    statement.executeUpdate("SQL Statement: " + sql);

		    //Finally, insert to the loan_info table
		    sql = this.buildLoanInfoSQL(theBook);
		    Debug.println(sql);
		    statement.executeUpdate(sql);
		    
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
	} //End dbInsertOneBook
	
	public void dbInsertManyBooks(ArrayList<BookObject> bookList)
	{ //Begin dbInsertManyBooks
		//Database URL
		final String DB_URL = "jdbc:mysql://"+url;
		Debug.println(DB_URL);
		
		//Create variables for the connection and the statement to be executed
		Connection conn = null;
		Statement statement = null;
		
		//Try statement, need to catch a bunch of exceptions
		try
		{ //Begin try
			//Turn on Debug statement for testing
			Debug.turnOn();
			
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

		    //Create a statement with the connection
		    statement = conn.createStatement();
		    
		    //There are three tables in the database that need to have data added to them:
		    //name_list, book_info, and loan_info. Each table has a private method in this class
		    //which builds the SQL statements needed to add the required data to each table.
		    
		    //Use a loop to add every BookObject in the passed ArrayList into the database
		    for(BookObject book: bookList)
		    { //Begin for loop
		    	//First, insert values into the name_list table, 
			    String sql = this.buildNameListSQL(book);
			    Debug.println(sql);
			    statement.executeUpdate(sql);

			    //Next, insert to the book_info table
			    sql = this.buildBookInfoListSQL(book);
			    Debug.println(sql);
			    statement.executeUpdate("SQL Statement: " + sql);

			    //Next, insert to the loan_info table
			    sql = this.buildLoanInfoSQL(book);
			    Debug.println(sql);
			    statement.executeUpdate(sql);
			    
			    //Finally, insert to the date_info table
			    sql = this.buildDateInfoSQL(book);
			    Debug.println(sql);
			    statement.executeUpdate(sql);
		    } //End for loop
		    
		    //Tell the user that the insert was completed.
		    System.out.println("Inserted records into the table.");

		   } //End try
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      String errorString = se.toString();
		      if(errorString.contains("Duplicate entry"))
		      { //Begin if
		    	  //If we get a duplicate error entry while going through the array list, remove the 
		    	  //duplicate and any objects in the ArrayList before it (which have already been added)
		    	  //and run the method again with the resulting array list.
		    	  String isbnStopped = errorString.substring(18, errorString.indexOf("'")-1);
		    	  Debug.println(isbnStopped);
		      } //End if
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
	} //End dbInsertManyBooks
	
	//Helping methods
	
	/**
	 * Builds the SQL statement needed to add a book to the name_list table
	 * @param theBook a BookObject, the book beign added to the database
	 * @return a String, the sql statement
	 */
	private String buildNameListSQL(BookObject theBook)
	{ //Begin uildNameListSQL
		//Variables for information to be used
	      //All BookObjects must have an isbn, name, and author. These must be present for this to work.
	      //The name_list table only contains the isbn, name, and author of a book.
	      int isbn = theBook.getISBN();
	      String name = theBook.getBookName();
	      String author = theBook.getAuthorName();

	      //Now, build the SQL statement as a string. 
	      String sqlStatement = "INSERT INTO name_list "
	      							+ "VALUES (" + isbn + ", '" + name + "', '" + author + "');";
	      //Return the SQL statement
	      return sqlStatement;
	} //End uildNameListSQL
	
	
	/**
	 * Builds the SQL statement needed to add information about a new book to the book_info table.
	 * @param theBook a BookObject, the book being added to the database
	 * @return a String, the SQL statement to insert the book into the book_info table
	 */
	private String buildBookInfoListSQL(BookObject theBook)
	{ //Begin buildBookInfoListSQL
		//The book_info table contains the ISBN, owner, location, and times read
		int isbn = theBook.getISBN();
	    String owner = theBook.getOwner();
	    String location = theBook.getLocation();
	    int timesRead = theBook.getNumTimesRead();

	    //Build the sqlStatement to be returned
	    String sqlStatement = "INSERT INTO book_info"
    							+ " VALUES (" + isbn + ", '" + owner + "', '" + location 
    							+ "', " + timesRead + ");";
	    //Return the SQL statement
	    return sqlStatement;
	} //End buildBookInfoListSQL
	
	
	/**
	 * Builds the SQL statement needed to add information about a new book to the loan_info table.
	 * @param theBook a BookObject, the book being added to the database
	 * @return a String, the SQL statement to insert data into the loan_info table
	 */
	private String buildLoanInfoSQL(BookObject theBook)
	{ //Begin buildLoanInfoSQL
		//The loan_info table holds the ISBN, person loaned to, and date loaned to
		int isbn = theBook.getISBN();
	    String loanedTo = theBook.getPersonLoanedTo();

	    String sqlStatement = "INSERT INTO loan_info "
  								+ "VALUES (" + isbn + ", '" + loanedTo + "');";
	    //Return the SQL statement
	    return sqlStatement;
	} //End buildLoanInfoSQL
	
	/**
	 * Builds the Insert Into statement for inserting data into the date_info table.
	 * @param theBook a BookObject, the book being added to the database
	 * @return a STring, the SQL statement for inserting into the date_info table
	 */
	private String buildDateInfoSQL(BookObject theBook)
	{ //Begin buildDateIngoSQL
		//I have been having problems with Date objects- they are different in Java and SQL. I will have to
		//Modify this to work properly when I get them working.
		int isbn = theBook.getISBN();
		//After this there will be 3 date objects, when I get them working
		
		//Build the SQL statement
		String sqlStatement = "INSERT INTO date_info VALUES (" + isbn + "NULL, NULL);";
		//Return the SQL statement
		return sqlStatement;
	} //End buildDateIngoSQL
} //End class
