package com.scg.student.dao;

import java.util.ArrayList;
import java.util.List;
import com.scg.student.services.*;

import java.sql.*;

public class StudentDAO {
// List<Student> studentList=new ArrayList<Student>();
	Connection con;

//private Statement st=null;
//private ResultSet rs=null;	
	public int insert(Student s) throws SQLException {
		int count = 0;
		try {

			// Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = MySql.getConnection();
			con.setAutoCommit(false);
			PreparedStatement stmt = con.prepareStatement("insert into student values(?,?,?)");
			stmt.setInt(1, s.getId());
			stmt.setString(2, s.getName());
			stmt.setInt(3, s.getAge());
			count = stmt.executeUpdate();
			System.out.println("commit or rollback");
			String answer = Console.readstring();
			if (answer.equals("commit")) {
				con.commit();
				System.out.println("Insertion  completed successfully");
			}

			if (answer.equals("rollback")) {

				con.rollback();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(count);
		return count;
	}
		
		
//     	con= MySql.getConnection();
//		st= ((Connection) con).createStatement();
//		rs=st.executeQuery("select * from student");
//		rs.next();
//		while(rs.next())  
//		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//     	((Connection) con).close();  
	
		
		//studentList.add(s);

	public int delete(int id) {
		int count=0;
		try {
			con = MySql.getConnection();
			PreparedStatement stmt = con.prepareStatement("delete from student where id=?");
			stmt.setInt(1, id);
			count = stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return count;

	}

	public List<Student> readAll() {
		List<Student> studentList=new ArrayList<Student>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				Student newstudent = new Student(id, name, age);
				studentList.add(newstudent);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return studentList;
	}

	public void update(int id,int age) {
		try {
			
			con = MySql.getConnection();

			PreparedStatement stmt=con.prepareStatement("update student set age=? where id=?");  
		    stmt.setInt(1,age);
		    stmt.setInt(2,id);
		    int count=stmt.executeUpdate();
			con.close();  
			}catch(Exception e){ 
			System.out.println(e);}  
		
	}

	public List<Student> search(String name) {
		List<Student> studentList=new ArrayList<Student>();
		try {
			con = MySql.getConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student where name like '%" + name + "%'");
			while (rs.next()) {
				int id = rs.getInt("id");
				String studentname = rs.getString("name");
				int age = rs.getInt("age");
				Student newstudent = new Student(id, studentname, age);
				studentList.add(newstudent);
			}
			con.close();	
		} catch (Exception e) {
			System.out.println(e);
		}
		return studentList;

	}

	public Student readById(int id) {
		Student newstudent=null;
		try {
			con = MySql.getConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student where id=" + id);
			while (rs.next()) {
				int studentId = rs.getInt("id");
				String studentName = rs.getString("name");
				int studentAge = rs.getInt("age");
				 newstudent = new Student(studentId, studentName, studentAge);
				   
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return newstudent;
	}

	public List<Student> getPage(int pageNumber,int pageSize) {
		List<Student> studentList=new ArrayList<Student>();
		try {
			con = MySql.getConnection();		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from student limit "+pageNumber+","+pageSize);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			Student newstudent = new Student(id, name, age);
			studentList.add(newstudent);
		}
		con.close();  
		 return studentList; 
		}catch(Exception e){ System.out.println(e);}
		return studentList;
		}

	public int move(int id) throws SQLException {
		// List<Student> studentList=new ArrayList<Student>();
		Student newstudent = null;
		int count = 0;
		try {
			con = MySql.getConnection();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student where id=" + id);
			while (rs.next()) {
				int studentId = rs.getInt("id");
				String studentName = rs.getString("name");
				int studentAge = rs.getInt("age");
				newstudent = new Student(studentId, studentName, studentAge);
			}
	        stmt.close();
	        con.commit();
			PreparedStatement stm = con.prepareStatement("delete from student where id=?");
			stm.setInt(1, id);
			count = stm.executeUpdate();
			PreparedStatement st = con.prepareStatement("insert into passout values(?,?,?)");

			st.setInt(1, newstudent.getId());
			st.setString(2, newstudent.getName());
			st.setInt(3, newstudent.getAge());
			count = st.executeUpdate();
			con.commit();
			con.close();
			stm.close();

			return count;
		} catch (Exception e) {
			try { 
				con.rollback();
				con.close();

			} catch (Exception e1) {
				System.out.println(e1);
				count=0;
				return count;
			}
			System.out.println(e);

			return count;

		}
	}
	 
	
	public int  arraysize()
    {
		List<Student> studentList=new ArrayList<Student>();
		try {
			con = MySql.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				Student newstudent = new Student(id, name, age);
				studentList.add(newstudent);
			}
			//con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return studentList.size();
    }



	public void setCommiting() throws SQLException {
		con = MySql.getConnection();
		con.commit();
				
	}
}
