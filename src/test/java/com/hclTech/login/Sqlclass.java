package com.hclTech.login;





import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
//import java.sql.DriverManager;
 
import java.sql.Connection;
 
public class Sqlclass {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Data";
			String user = "postgres";
			String password = "1234";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to postgresql");
			//String sql = "UPDATE Employee SET, firstname, lastname, salary FROM Employee";
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT employeeid, firstname, lastname, salary FROM Employee");
			while(rs.next()) {
				System.out.println(rs.getInt("employeeid")+" | " + rs.getString("firstname")+" | " +rs.getString("lastname")+" | " +rs.getDouble("salary"));
				
			}
			con.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
 
	}
 
}
 
 
 
