package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

/**
 * The PMDatabase class provides methods to interact with the SQLite database used in the project management system.
 */
public class PMDatabase 
{
	
	private static Connection conn;
	
	/**
     * Establishes a connection to the SQLite database.
     * 
     * @return the database connection
     */
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
	
	/**
     * Creates necessary tables in the database if they do not exist.
     */
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
	
	 /**
     * Retrieves data from the Person table and populates the given DefaultTableModel.
     * 
     * @param modelPerson the DefaultTableModel to populate with data from the Person table
     */
	public static void fetchFromPerson(DefaultTableModel modelPerson) 
	{
		connect();
		
		try 
		{
			modelPerson.setRowCount(0);
			PreparedStatement pStmt = conn.prepareStatement("SELECT personID, firstname, lastname, sex, email, phone, fax, username, password FROM Person");
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
						rs.getString("username"),
						rs.getString("password")
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
	
	/**
     * Retrieves data from the Project table and populates the given DefaultTableModel.
     * Also retrieves collaborators associated with each project and adds them to the table.
     * 
     * @param modelProject the DefaultTableModel to populate with data from the Project table
     */
	public static void fetchFromProjects(DefaultTableModel modelProject) 
	{
		connect();
		
		try 
		{
			modelProject.setRowCount(0);
			PreparedStatement pStmt = conn.prepareStatement("SELECT projectID, acronym, title, startdate, enddate, description FROM Project");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) 
			{
				
				int projectID = rs.getInt("projectID");
				String acronym = rs.getString("acronym");
				String title = rs.getString("title");
				String startdate = rs.getString("startdate");
				String enddate = rs.getString("enddate");
				String description =rs.getString("description");
				
				PreparedStatement pStmt2 = conn.prepareStatement("SELECT DISTINCT p.lastname FROM PERSON p JOIN Person_Project pp ON p.personID = pp.personID WHERE pp.projectID = ?");
				pStmt2.setInt(1, projectID);
				ResultSet rsCollab = pStmt2.executeQuery();
				StringBuilder collaborators = new StringBuilder();
				while(rsCollab.next()) 
				{
					collaborators.append(rsCollab.getString("lastname")).append(", ");
				}
				if(collaborators.length() > 0) 
				{
					collaborators.setLength(collaborators.length() - 2);
				}
				Object[] row = 
				{
					projectID,
					acronym,
					title,
					startdate,
					enddate,
					description,
					collaborators.toString()
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
	
	  /**
     * Closes the database connection.
     */
	public static void closeConn() 
	{
		try 
		{
			conn.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
