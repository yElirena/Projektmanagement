package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PMDatabase 
{
	
	private static Connection conn;
	
	public static void connect() 
	{  
        try 
        {  
        	conn = null;
            String url = "jdbc:sqlite:PMDatabase.sqlite";  
              
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");
            
              
        } 
        catch (SQLException e) 
        {  
            System.out.println(e.getMessage());  
		}  
    }
	public static void createTables() 
	{
		connect();
		
		try 
		{
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS Project (projectID INTEGER PRIMARY KEY AUTOINCREMENT, acronym TEXT, title TEXT, description TEXT, startingDate TEXT, endDate TEXT)");
			stmt.close();
			
			stmt.execute("CREATE TABLE IF NOT EXISTS Person (personID INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, sex TEXT, email TEXT, phone TEXT, fax TEXT, username TEXT, password TEXT)");
			stmt.close();
			
			stmt.execute("CREATE TABLE IF NOT EXISTS Person_Project (personID INTEGER, projectID INTEGER, FOREIGN KEY(personID) REFERENCES Person(personID) ON UPDATE CASCADE, FOREIGN KEY(projectID) REFERENCES Project(projectID) ON UPDATE CASCADE)");
			stmt.close();
			System.out.println("Tables have been created successfully.");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}		
	}
	public static void insertIntoTables()
	{
		connect();
		
		try 
		{
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Project (acronym, title, description, startingDate, endDate) VALUES ('ABC', 'Alphabet', 'Description', '2024-06-12', '2024-06-15')");
			pStmt.execute();
			pStmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	public static void fetchFromTables() 
	{
		connect();
		
		try 
		{
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM Project");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				String str = String.format("ID:  %d, Acronym: %s, Title: %s, Description: %s, Starting Date: %s, End Date: %s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				System.out.println(str);
			}
			rs.close();
			pStmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		connect();
		createTables();
		//insertIntoTables();
		fetchFromTables();
	
	}

}
