package com.scg.student.dao;
import java.sql.*;

public class MySql {
private static  Connection con;

//static 
public static  Connection getConnection() throws SQLException{
	 
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root");  
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}  
	return con;
}
}
