package com.SpringBootProject.StudentDetails.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SpringBootProject.StudentDetails.Exception.StudentDetailsNotFoundException;
import com.SpringBootProject.StudentDetails.Model.StudentModel;
import com.SpringBootProject.StudentDetails.Repository.StudentDAO;

@Service
public class StudentDetailsServiceImpl {
    
    private StudentDAO studentDetailsRepo;

    @Autowired
    public void setStudentDetailsRepo(StudentDAO studentDetailsRepo) {
        this.studentDetailsRepo = studentDetailsRepo;
    }

    public ResponseEntity<String> addStudentDetails(StudentModel studentModel) {
    	 if (isEmpty(studentModel.getName()) ||
    	            isEmpty(studentModel.getRegisterNo()) ||
    	            isEmpty(studentModel.getGender()) ||
    	            isEmpty(studentModel.getPhoneNumber()) ||
    	            isEmpty(studentModel.getCurrentStatus()) ||
    	            isEmpty(studentModel.getEmailId()) ||
    	            isEmpty(studentModel.getCourse()) ||
    	            isEmpty(studentModel.getBatch())) {
    	          int result=0;
    	        }
 
    	// If any required field is empty, return bad request status
	     int result = studentDetailsRepo.addStudentDetails(studentModel);
        
	     if (result!=1){
	            // If any required field is empty, return bad request status
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All input fields are required");
	        }  
	        // If all required fields are present, proceed to add the student
	      
	        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
	   
	    }
    
    // Helper method to check if a value is empty (null or empty string)
    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public int deleteStudentDetails(int studentId) {
        int result = studentDetailsRepo.deleteStudentDetails(studentId);
        return result;
    }

    public int updateStudentDetails(StudentModel studentModel) {
    	if (isEmpty(studentModel.getName()) ||
	            isEmpty(studentModel.getRegisterNo()) ||
	            isEmpty(studentModel.getGender()) ||
	            isEmpty(studentModel.getPhoneNumber()) ||
	            isEmpty(studentModel.getCurrentStatus()) ||
	            isEmpty(studentModel.getEmailId()) ||
	            isEmpty(studentModel.getCourse()) ||
	            isEmpty(studentModel.getBatch())) {
	         return 0;
	        }
    	
        int result = studentDetailsRepo.updateStudentDetails(studentModel);
        return result;
    }
   
    public StudentModel findById(int studentId) throws StudentDetailsNotFoundException {
        StudentModel student = studentDetailsRepo.findById(studentId);
        if (student == null) {
            throw new StudentDetailsNotFoundException("Student with ID " + studentId + " not found");
        }
        return student;
    }

    public List<StudentModel> getAllStudentDetails() {
        return studentDetailsRepo.getAllStudentDetails();
    }


   
}
