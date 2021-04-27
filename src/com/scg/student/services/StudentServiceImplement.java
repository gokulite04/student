package com.scg.student.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.scg.student.services.Student;
import com.scg.student.dao.*;
import com.scg.student.services.Validator;
public class StudentServiceImplement implements StudentService {

	int count;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Validator t = new Validator();

	private StudentDAO dao = new StudentDAO();

	@Override
	public void insert(int id, String name, int age) throws SQLException {

		Student s = new Student(id, name, age);
		try {
			t.validate(s);
		} catch (Exception e) {

			System.out.println(e);
			return;
		}
		count = dao.insert(s);
		return;
	}

	@Override
	public void delete(int id) {
		int count;
		try {
			t.validateId(id);
		} catch (UserInvalidInput e) {
			System.out.println(e);
			return;
		}
		count = dao.delete(id);
		System.out.println("Deleted successfully" + count + "items");
		return;
	}

	@Override
	public void readAll() {
		for (Student student : dao.readAll()) {
			String str = String.format("%-10d%-10s%-10d",student.getId(),student.getName(),student.getAge());
			System.out.println(str);
		}

	}

	@Override
	public void update(int id, int age) {

		try {
			t.validateId(id);
		} catch (UserInvalidInput e) {
			System.out.println(e);
		}
		try {
			t.validateAge(age);
		} catch (UserInvalidInput e) {

			System.out.println(e);
			return;
		}
		dao.update(id, age);
	}

	@SuppressWarnings("static-access")
	@Override
	public void searchByName(String name) {
		try {
			t.validateName(name);
		} catch (UserInvalidInput e) {
			System.out.println(e);
		}

		for (Student student : dao.search(name)) {
			String str = String.format("%-10d%-10s%-10d",student.getId(),student.getName(),student.getAge());
			System.out.println(str);
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void readById(int id) {
		try {
			t.validateId(id);
		} catch (UserInvalidInput e) {
			System.out.println(e);
			return;
		}

		Student student=dao.readById(id);
		if(student==null) {
			System.out.println("No id find");
		}
		else {
			String str = String.format("%-10d%-10s%-10d",student.getId(),student.getName(),student.getAge());
			System.out.println(str);
		}
		return;
	}

	@Override
	public void pagination(int pageNum) {
		int pageSize = 2;
		pageNum = pageNum * pageSize - 1;
		for (Student student : dao.getPage(pageNum, pageSize)) {
			String str = String.format("%-10d%-10s%-10d",student.getId(),student.getName(),student.getAge());
			System.out.println(str);
		}

	}

	public void setCommiting() {
		try {
			dao.setCommiting();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void move(int id) {
		int count=0;
	   try {
		count=dao.move(id);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}
		if(count==1)
		{
			System.out.println("commit");
		}
		else {
			System.out.println("roll back");
		}
	}

}
