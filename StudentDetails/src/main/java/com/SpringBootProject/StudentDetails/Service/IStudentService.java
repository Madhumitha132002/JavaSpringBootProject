package com.SpringBootProject.StudentDetails.Service;

import java.util.List;

import com.SpringBootProject.StudentDetails.Exception.StudentDetailsNotFoundException;
import com.SpringBootProject.StudentDetails.Model.StudentModel;

public interface IStudentService {
	

	void addStudentDetails(StudentModel studentmodel);
	void deleteStudentDetails(int Student_id);
	void updateStudentDetails(StudentModel studentModel);
	StudentModel findById(int student_id) throws StudentDetailsNotFoundException;
	List<StudentModel> getAllStudentDetails();
	
	
	

}
