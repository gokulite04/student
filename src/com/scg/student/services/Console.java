package com.scg.student.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Console {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static String name;
	public static int readInt() throws NumberFormatException, IOException{
		n = Integer.parseInt(reader.readLine());
		return n;
	}
	public static String readstring() throws NumberFormatException,IOException{
	  name=reader.readLine();	
	  return name;
	}
}
