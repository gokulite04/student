package com.scg.jdbcdemo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class Jdbc {

	public static void main(String[] args) {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root");  
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from student where age=23");  
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			}  
} 
