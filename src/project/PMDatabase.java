package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PMDatabase {
	
	public static void connect() {  
        Connection conn = null;  
        try {  
        	
            String url = "jdbc:sqlite:C:\\Users\\Mariee\\eclipse-workspace\\Projektmanagement\\sqlite\\PMDatabase.sqlite";  
              
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");
            
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            try {  
                if (conn != null) {  
                    conn.close();  
                }  
            } catch (SQLException ex) {  
                System.out.println(ex.getMessage());  
            }  
        }  
    }
	
	public static void main(String[] args) {
		connect();
	
	}

}
