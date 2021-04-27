package com.scg.student.services;

import java.sql.SQLException;

public interface StudentService {
	
    void readAll();
	void update(int id, int age);
	void searchByName(String name);
	void insert(int id, String name, int age) throws UserInvalidInput, SQLException;
	void delete(int d);
	void readById(int id);
	void pagination(int pageNum);
    void setCommiting();
    void move(int id);
}
