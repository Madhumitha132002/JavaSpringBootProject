package com.SpringBootProject.StudentDetails.Repository;

import java.util.List;

import com.SpringBootProject.StudentDetails.Model.StudentModel;

public interface StudentDAO {
	
	int addStudentDetails(StudentModel studentmodel);
	int deleteStudentDetails(int Student_id);
	int updateStudentDetails(StudentModel studentModel);
	StudentModel findById(int student_id);
	static List<StudentModel> getAllStudentDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}