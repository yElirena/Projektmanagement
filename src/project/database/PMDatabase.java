package project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * The PMDatabase class provides methods to interact with the SQLite database used in the project management system.
 */
public class PMDatabase 
{
	private static PreparedStatement stmt;	
	private static Connection conn;
	private static int ppID;
	
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
			
			stmt.execute("CREATE TABLE IF NOT EXISTS Person_Project (ppID INTEGER PRIMARY KEY AUTOINCREMENT, personID INTEGER, projectID INTEGER, FOREIGN KEY(personID) REFERENCES Person(personID) ON DELETE CASCADE, FOREIGN KEY(projectID) REFERENCES Project(projectID) ON DELETE CASCADE)");
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
	public static void fetchFromPerson(DefaultTableModel model) 
	{
		connect();
		
		try 
		{
			model.setRowCount(0);
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM Person");
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
				model.addRow(row);
			}
			rs.close();
			pStmt.close();
			closeConn();
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
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM Project");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) 
			{
				
				int projectID = rs.getInt("projectID");
				String acronym = rs.getString("acronym");
				String title = rs.getString("title");
				String startdate = rs.getString("startdate");
				String enddate = rs.getString("enddate");
				String description =rs.getString("description");
				
				PreparedStatement pStmt2 = conn.prepareStatement("SELECT DISTINCT p.firstname, p.lastname FROM PERSON p JOIN Person_Project pp ON p.personID = pp.personID WHERE pp.projectID = ?");
				pStmt2.setInt(1, projectID);
				ResultSet rsCollab = pStmt2.executeQuery();
				StringBuilder collaborators = new StringBuilder();
				while(rsCollab.next()) 
				{
					collaborators.append(rsCollab.getString("firstname")).append(" ");
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
				
				pStmt2.close();
				modelProject.addRow(row);
			}
			rs.close();
			pStmt.close();
			closeConn();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}		
	}
	
	public static void insertIntoPerson(String fname, String lname, String sex, String email, String phone, String fax, String user, String pw) 
	{
		connect();
		
		try 
		{
			stmt = conn.prepareStatement("INSERT INTO Person (firstname, lastname, sex, email, phone, fax, username, password) VALUES (?,?,?,?,?,?,?,?)");
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setString(3, sex);
			stmt.setString(4, email);
			stmt.setString(5, phone);
			stmt.setString(6, fax);
			stmt.setString(7, user);
			stmt.setString(8, pw);
			stmt.execute();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		closeConn();
	}
	public static void insertIntoProject(String acr, String title, String start, String end, String desc) 
	{
		connect();
		try 
		{
			stmt = conn.prepareStatement("INSERT INTO Project (acronym, title, startdate, enddate, description) VALUES (?,?,?,?,?)");
			stmt.setString(1, acr);
			stmt.setString(2, title);
			stmt.setString(3, start);
			stmt.setString(4,  end);
			stmt.setString(5, desc);
			stmt.execute();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		closeConn();		
	} 
	public static void insertIntoCollab(int projectID, String collabs) 
	{
		
		try 
		{
			String[] nameArray = collabs.split("[,\\s]+");
			PreparedStatement stmtPersonID = conn.prepareStatement("SELECT personID FROM Person WHERE firstname = ? AND lastname = ?");
			PreparedStatement stmtInsert = conn.prepareStatement("INSERT INTO Person_Project (personID, projectID) VALUES (?,?)");
			for (int i = 0; i < nameArray.length; i += 2) {
                if (i + 1 >= nameArray.length) {
                    JOptionPane.showMessageDialog(null, "Invalid name format: " + nameArray[i]);
                    continue;
                }

                String firstname = nameArray[i];
                String lastname = nameArray[i + 1];
                System.out.println(firstname + " " + lastname);
				stmtPersonID.setString(1, firstname);
				stmtPersonID.setString(2, lastname);
				ResultSet rsPersonId = stmtPersonID.executeQuery();
				if(rsPersonId.next()) 
				{
					int personID = rsPersonId.getInt("personID");
					stmtInsert.setInt(1, personID);
					stmtInsert.setInt(2,  projectID);
					stmtInsert.executeUpdate();
				}
				else 
				{
					JOptionPane.showMessageDialog(null, firstname + " " + lastname + " does not exist in the database.");
				}
				rsPersonId.close();
			}
			stmtPersonID.close();
			stmtInsert.close();
			closeConn();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public static void insertAll(String fname, String lname, String sex, String email, String phone, String fax, String user, String pw, String acr, String title, String start, String end, String desc) 
	{
		Thread tPerson = new Thread(() -> 
		{
			insertIntoPerson(fname, lname, sex, email, phone, fax, user, pw);
		});
		Thread tProject = new Thread(() -> 
		{
			insertIntoProject(acr, title, start, end, desc);
		});
		
		tPerson.start();
		tProject.start();
		
		try 
		{
			tPerson.join();
			tProject.join();
		}
		catch(InterruptedException e) 
		{
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	public static void updatePerson(String fname, String lname, String sex, String email, String phone, String fax, String user, String pw, int personID)
	{
		connect();
		
		try 
		{
			stmt = conn.prepareStatement("UPDATE Person SET firstname = ?, lastname = ?, sex = ?, email = ?, phone = ?, fax = ?, username = ?, password = ? WHERE personID = ?");
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setString(3, sex);
			stmt.setString(4, email);
			stmt.setString(5, phone);
			stmt.setString(6, fax);
			stmt.setString(7, user);
			stmt.setString(8, pw);
			stmt.setInt(9, personID);
			stmt.executeUpdate();
			
			closeConn();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void updateProject(String acr, String title, String start, String end, String desc,  int projectID) 
	{
		try 
		{
			stmt = conn.prepareStatement("UPDATE Project SET acronym = ?, title = ?, startdate = ?, enddate = ?, description = ? WHERE projectID = ?");
			stmt.setString(1, acr);
			stmt.setString(2, title);
			stmt.setString(3, start);
			stmt.setString(4, end);
			stmt.setString(5, desc);
			stmt.setInt(6, projectID);
			stmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
	        try 
	        {
	            if (conn != null) 
	            {
	                conn.close();
	            }
	        }
	        catch (SQLException e) 
	        {
            e.printStackTrace();
	        }
		}
		
	}
    public static void updateCollabs(int projectID, String collabs) throws SQLException 
    {
    	if(collabs != null) 
    	{
    		String[] nameArray = collabs.split("[,\\s]+");
    		for(int i = 0; i < nameArray.length; i+=2) 
    		{
    			if(i+ 1 >= nameArray.length) 
    			{
    				JOptionPane.showMessageDialog(null, "Invalid name.");
    				continue;
    			}
    			String firstname = nameArray[i];
    			String lastname = nameArray[+ +1];
    			int personID = getPersonID(firstname, lastname);
    			if(personID != 0 && !doesPPDBEntryExist(personID, projectID)) 
    			{
    				insertIntoCollab(projectID, collabs);
    			}
    				
    		}
    	}
    }

    public static int getPersonID(String firstname, String lastname) throws SQLException 
    {
    	connect();
    	int personID = 0;
    	stmt = conn.prepareStatement("SELECT personID FROM Person WHERE firstname = ? AND lastname = ?");
    	stmt.setString(1, firstname);
    	stmt.setString(2, lastname);
    	ResultSet rs = stmt.executeQuery();
    	if(rs.next()) 
    	{
    		personID = rs.getInt("personID");
    	}
    	else 
    	{
    		JOptionPane.showMessageDialog(null, firstname + lastname + " does not exist");
    	}
    	closeConn();
    	return personID;
    }
	
    public static boolean doesPPDBEntryExist(int personID, int projectID) throws SQLException 
    {
    	boolean exists = false;
    	connect();
    	stmt = conn.prepareStatement("SELECT COUNT(*) FROM Person_Project WHERE personID = ? AND projectID = ?");
    	stmt.setInt(1, personID);
    	stmt.setInt(2, projectID);
    	ResultSet rs = stmt.executeQuery();
    	if(rs.next()) 
    	{
    		exists = rs.getInt(1) > 0;
    	}
    	return exists;
    }
    /**
     * Closes the database connection.
     */
	public static void closeConn() 
	{
		try 
		{
			if(stmt != null) 
			{
				stmt.close();
				
			}
			conn.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
