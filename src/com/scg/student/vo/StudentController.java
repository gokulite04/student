package com.scg.student.vo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import com.scg.student.services.*;
import com.scg.student.services.Validator;

@SuppressWarnings("unused")
public class StudentController {

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		String c = "y";
		StudentServiceImplement s=new StudentServiceImplement();
		while(c.equalsIgnoreCase("y")) {
			System.out.println("1.Insert student ");
			System.out.println("2.Delete student by id");
			System.out.println("3.Print all student information");
			System.out.println("4.Update student information");
			System.out.println("5.Search by name");
			System.out.println("6.Read by id");
			System.out.println("7.pagination");
			System.out.println("8.Set commit:");
			System.out.println("9.Move");
			int option=in.nextInt();
			int id;
			String name = null;
			int age;
			switch (option) {
			case 1:
				System.out.println("Enter Id, Name, Age");
				id =Console.readInt();
				name=Console.readstring();
				age=Console.readInt();
				s.insert(id,name,age);
				break;
				
			case 2:
				System.out.println("Enter Student  Id : ");
				id=Console.readInt();
				s.delete(id);
				break;
				
			case 3:
				s.readAll();
				break;
				
			case 4:
				System.out.println("Enter student id and age: ");
				id = Console.readInt();
				age = Console.readInt();
				s.update(id,age);
				break;
				
			case 5:
				System.out.println("Enter student name : ");
				name=Console.readstring();
				s.searchByName(name);
				break;
				
			case 6:
				System.out.println("Enter student Id : ");
				id = Console.readInt();
				s.readById(id);
				break;
			case 7:
				System.out.println("Enter page number : ");
				int pagenum= Console.readInt();
				s.pagination(pagenum);
      			break;	
			case 8:
				
				s.setCommiting();
				break;
			case 9:
				System.out.println("Enter id:");
				id= Console.readInt();
				s.move(id);
				break;
			default:
				System.out.println("Enter valid option");
				break;
			}
			System.out.println("Do you want to continue?[Y/N]");
			c=in.next();
		}
		in.close();

	}

}


