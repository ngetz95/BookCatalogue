/*
 * Created December 8, 2015 by Marc Kuniansky
 * 
 * Modifications
 * Date					Name
 * December 8, 2015		Marc Kuniansky
 * 		Modifications Made:
 * 		- Implemented the class. Gave it 5 global variables: url to store the URL of the database, user to store the username for 
 * 		database access, pass to store the password for database access, conn to store the connection status, and theBook to store
 * 		the book being modified.
 * 		- Implemented the constructor, which takes 4 variables- theURL, userName, Password, and book. The constructor initializes
 * 		the global variables and does nothing else.
 * 		- Implemented the updateNameListName method, which uses PreparedStatements executes the SQL code to update the name of a book
 * 		in the name_list table.
 * 		- Implemented the updateNameListAuthor method, which uses PreparedStatements executes the SQL code to update the author of
 * 		a book in the name_list table.
 * 		- Implemented the closeConnection method, which closes the connection when the 
 * 
 */
package com.kuniansky.marc;

import java.sql.*;

/**
 * Contains methods necessary to update individual book objects held in the database
 * @author Marc Kuniansky
 *
 */
public class DatabaseManagerUpdateBook 
{ //Begin class
	
	//Global variables
	//The url is the exact location of the database being modified. It includes the prefix JDBC needs: "jdbc:mysql://"
	private static String url;
	//The user is the name of the user authorized to modify the database
	private static String user;
	//The password is the password for the account authorized to modify the database
	private static String pass;
	//A connection object to hold the status of the connection
	private Connection conn;
	//The book object to be modified in the database.
	private BookObject theBook;
	
	//Constructors
	
	/**
	 * Constructs a new DatabaseManagerUpdateBook object. The paramenters allow the programmer to define the URL of the user's 
	 * database, their username, password, and the book that needs to be updated. 
	 * 
	 * This also connects to the database. The connection will persist until the closeConnection() method is called, allowing
	 * you to make multiple updates to a book object without closing/reopening the connection for each call.
	 * @param a String, theURL the URL of the database. 
	 * @param userName a String, the username needed to access the database
	 * @param Password a String, the password needed to access the database
	 * @param book a BookObject, the book being updated in the database
	 */
	public DatabaseManagerUpdateBook(String theURL, String userName, String thePassword, BookObject book)
	{ //Begin constructor
		//Initialize the instance variables.
		url = "jdbc:mysql://"+theURL;
		user = userName;
		pass = thePassword;
		theBook = book;
		//Connect to the database, and store the connection in conn. This requires try/catch statements.
		try
		{ //Begin try
			//Tell the user they are being connected
			System.out.println("Connecting to a selected database...");
			//Set the driver for the connection
			Class.forName("com.mysql.jdbc.Driver");
		    //Connect to the database
		    conn = DriverManager.getConnection(url, user, pass);
		    //Tell the user that they connected if the connection was successful
		    System.out.println("Connected database successfully!");
		} //End try
		catch(ClassNotFoundException e)
		{ //Begin catch
			e.printStackTrace();
		} //End catch
		catch(SQLException sq)
		{ //Begin catch
			sq.printStackTrace();
		} //End catch
	} //End constructor
	
	/**
	 * Closes the connection with the database which the class established upon construction.
	 */
	public void closeConnection()
	{ //Begin closeConnection
		try
		{ //Begin try
			//Close the connection to the database
			conn.close();
		} //End try
		catch(SQLException sq)
		{ //Begin catch
			sq.printStackTrace();
		} //End catch
	} //End closeConnection
	//Methods
	
	/*
	 * The ISBN of a book cannot be updated at this time. 
	 * Need methods to update the following fields in each table:
	 * name_list
	 * 		name X
	 * 		author X
	 * 
	 * book_info
	 * 		owner X
	 * 		location
	 * 		times_read
	 * 
	 * loan_info
	 * 		loaned_to
	 * 
	 * date_info
	 * 		purchase_date
	 * 		loaned_to_date
	 * 		loaned_from_date
	 */
	
	//Updaters for the name_list table
	
	/**
	 * Updates the name of a book in the name_list table.
	 * @param desiredName a String, the new name for the book.
	 */
	public void updateNameListName(String desiredName)
	{ //Begin updateNameListName
		//Database URL
		Debug.println(url);
		
		//Get the needed information for updating the table
		//First need the ISBN, to tell SQL which entry to update
		int ISBN = theBook.getISBN();

		try
		{ //Begin try
			//Debug line
			Debug.println("Updating record in the table...");
			//Build a Prepared Statement. These are really cool, they help prevent SQL injection by
			//Pre-loading a statement. Each question mark needs to be made something, and count up 
			//numerically left to right from 1. 
			//?1=the name of the book, ?2=ISBN
			PreparedStatement statement = conn.prepareStatement
											("UPDATE name_list"
												+" SET name=?"
												+" WHERE isbn=?");
			
			//Set the fields in the prepared statement
			statement.setString(1, desiredName);
			statement.setInt(2, ISBN);
	    
			//Execute the SQL statement
			statement.executeUpdate();
			//Tell the user that the insert was completed.
			System.out.println("Updated record in the name_list table.");
		} //End try
		catch(SQLException sq)
		{ //Begin catch
			sq.printStackTrace();
		} //End catch
		finally
		{ //Begin finally
			
		} //End finally
	} //End updateNameListName
	
	/**
	 * Updates the author of a book in the name_list table.
	 * @param desiredAuthor a String, the desired author name.
	 */
	public void updateNameListAuthor(String desiredAuthor)
	{ //Begin updateNameListAuthor
		//Database URL
		Debug.println(url);
		
		//Get the needed information for updating the table
		//First need the ISBN, to tell SQL which entry to update
		int ISBN = theBook.getISBN();

		try
		{ //Begin try
			//Debug line
			Debug.println("Updating record in the table...");
			//Build a Prepared Statement. These are really cool, they help prevent SQL injection by
			//Pre-loading a statement. Each question mark needs to be made something, and count up 
			//numerically left to right from 1. 
			//?1=the name of the book, ?2=ISBN
			PreparedStatement statement = conn.prepareStatement
											("UPDATE name_list"
												+" SET author=?"
												+" WHERE isbn=?");
			
			//Set the fields in the prepared statement
			statement.setString(1, desiredAuthor);
			statement.setInt(2, ISBN);
	    
			//Execute the SQL statement
			statement.executeUpdate();
			//Tell the user that the insert was completed.
			System.out.println("Updated record in the name_list table.");
		} //End try
		catch(SQLException sq)
		{ //Begin catch
			sq.printStackTrace();
		} //End catch
	} //End updateNameListAuthor

	
	
	//Updaters for the book_info table.
	
	/**
	 * Updates the owner of a book in the book_info table.
	 * @param desiredOwner a String, the desired owner name.
	 */
	public void updateBookInfoOwner(String desiredOwner)
	{ //Begin updateBookInfoOwner
		//Database URL
		Debug.println(url);
		
		//Get the needed information for updating the table
		//First need the ISBN, to tell SQL which entry to update
		int ISBN = theBook.getISBN();

		try
		{ //Begin try
			//Debug line
			Debug.println("Updating record in the table...");
			//Build a Prepared Statement. These are really cool, they help prevent SQL injection by
			//Pre-loading a statement. Each question mark needs to be made something, and count up 
			//numerically left to right from 1. 
			//?1=the name of the book, ?2=ISBN
			PreparedStatement statement = conn.prepareStatement
											("UPDATE nook_info"
												+" SET owner=?"
												+" WHERE isbn=?");
			
			//Set the fields in the prepared statement
			statement.setString(1, desiredOwner);
			statement.setInt(2, ISBN);
	    
			//Execute the SQL statement
			statement.executeUpdate();
			//Tell the user that the insert was completed.
			System.out.println("Updated record in the name_list table.");
		} //End try
		catch(SQLException sq)
		{ //Begin catch
			sq.printStackTrace();
		} //End catch
	} //End updateBookInfoOwner



} //End class