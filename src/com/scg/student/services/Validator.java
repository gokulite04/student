package com.scg.student.services;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scg.student.dao.StudentDAO;
import com.scg.student.vo.StudentController;



@SuppressWarnings("unused")
public class Validator {
	private StudentDAO dao=new StudentDAO();
	public  void validateId(int id) throws UserInvalidInput{
		
			if(id<1) {
				throw new UserInvalidInput("Please enter a valid Id");
			}
			
//			for(Student st:dao.studentList) {
//				if(st.getId()==id) {
//					throw new UserInvalidInput("Id already exist");
				//}
			//}
		
		
    }
		public static void validateName(String name) throws UserInvalidInput {
				Pattern p = Pattern.compile(("^[a-zA-Z\\s]*$"));
			    Matcher matcher = p.matcher(name);
			    if(!matcher.matches()){
			    	throw new UserInvalidInput("please enter a valid name");
			    }
				
} 

		public void  validateAge(int age) throws UserInvalidInput {
			
				if(age<5||age>100) {
					throw new UserInvalidInput("please enter a valid Age");
		
		         }
		
        }
		public  void validatepage(int pageNum,int arraySize,int pageSize) throws UserInvalidInput{
			
			if(pageNum<(arraySize/pageSize)){
				throw new UserInvalidInput("Please enter a valid pagenumber");
			}
		}
		public void validate(Student s) throws Exception {
			
			try {
				
				//validate id
				if(s.getId()<1) {
					throw new UserInvalidInput("Invalid Id");
				}
				
					
				
				
				//validate name
				Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
			    Matcher matcher = pattern.matcher(s.getName());
			    if(!matcher.matches()){
			    	throw new UserInvalidInput("Invalid name");
			    }
			    
			    //validate age
			    if(s.getAge()<5||s.getAge()>100) {
					throw new UserInvalidInput("Invalid Age");
				}
			    
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				StudentController.main(null);
			}
			
		}


}