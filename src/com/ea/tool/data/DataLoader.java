package com.ea.tool.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataLoader {

	private static ResultSet rs;
    private static Connection con = null;
    private static Statement stmt = null;
	public static ResultSet GetConnection(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}

        rs=null;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +  
         "databaseName=AdventureWorks2014;integratedSecurity=true;");//user=Chika;password=pika");
			// Create and execute an SQL statement that returns some data.  
	         String SQL = "SELECT ProductID,Name,ProductNumber,Color,SafetyStockLevel, ListPrice,Size FROM Production.Product";  
	         stmt = con.createStatement();  
	         rs = stmt.executeQuery(SQL);  
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return rs;
	}

	public static void closeConnection() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
