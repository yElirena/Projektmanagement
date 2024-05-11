package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class PMDatabase 
{
	
	private static Connection conn;
	
	public static Connection connect() 
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
        return conn;
    }
	public static void createTables() 
	{
		connect();
		
		try 
		{
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS Project (projectID INTEGER PRIMARY KEY AUTOINCREMENT, acronym TEXT, title TEXT, startdate DATE, enddate DATE, description TEXT)");
			stmt.close();
			
			stmt.execute("CREATE TABLE IF NOT EXISTS Person (personID INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, sex TEXT, email TEXT, phone TEXT, fax TEXT, username TEXT, password TEXT)");
			stmt.close();
			
			stmt.execute("CREATE TABLE IF NOT EXISTS Person_Project (personID INTEGER, projectID INTEGER, FOREIGN KEY(personID) REFERENCES Person(personID) ON DELETE CASCADE, FOREIGN KEY(projectID) REFERENCES Project(projectID) ON DELETE CASCADE)");
			stmt.close();
			System.out.println("Tables have been created successfully.");
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}		
	}
	public static void fetchFromPerson(DefaultTableModel modelPerson) 
	{
		connect();
		
		try 
		{
			modelPerson.setRowCount(0);
			PreparedStatement pStmt = conn.prepareStatement("SELECT personID, firstname, lastname, sex, email, phone, fax, username FROM Person");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Object[] row = {
						rs.getInt("personID"),
						rs.getString("firstname"),
						rs.getString("lastname"),
						rs.getString("sex"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("fax"),
						rs.getString("username")
				};
				modelPerson.addRow(row);
			}
			rs.close();
			pStmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public static void fetchFromProjects(DefaultTableModel modelProject) 
	{
		connect();
		
		try 
		{
			modelProject.setRowCount(0);
			PreparedStatement pStmt = conn.prepareStatement("SELECT projectID, acronym, title, startdate, enddate, description FROM Project");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Object[] row = {
						rs.getInt("projectID"),
						rs.getString("acronym"),
						rs.getString("title"),
						rs.getString("startdate"),
						rs.getString("endDate"),
						rs.getString("description")
				};
				modelProject.addRow(row);
			}
			rs.close();
			pStmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}		
	}
	
	public static void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		//connect();
		//createTables();
	}

}
