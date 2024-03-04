package com.SpringBootProject.StudentDetails.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.SpringBootProject.StudentDetails.Model.StudentModel;
import com.SpringBootProject.StudentDetails.Repository.StudentDAOImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    
	@InjectMocks
    private StudentDetailsServiceImpl studentservice;
    
    @Mock
    private StudentDAOImpl studentrepository;
    //Add Student Details
    @Test
    @DisplayName("Should add the records to the database")
    void addStudentDetails() {
        // Create a sample student model
        StudentModel student = new StudentModel();
        student.setName("Gugan");
        student.setRegisterNo("675");
        student.setGender("Male");
        student.setAge(23);
        student.setPhoneNumber("8098161001");
        student.setCurrentStatus("Undergoing");
        student.setEmailId("Gugan@gmail.com");
        student.setCourse("MCA");
        student.setBatch("set89");
        student.setFees(19000);
     
        // Mock the behavior of studentrepository
        when(studentrepository.addStudentDetails(any(StudentModel.class))).thenReturn(1);
        
        // Invoke the method under test
        ResponseEntity<String> responseEntity = studentservice.addStudentDetails(student);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Student inserted successfully", responseEntity.getBody());
    }
    //Delete Student Details
    @Test
    @DisplayName("Should delete the records to the database")
    void deleteStudentDetails() {
        // Mocking the behavior of studentrepository
        when(studentrepository.deleteStudentDetails(any(int.class))).thenReturn(1);
        
        // Invoking the method under test
        ResponseEntity<String> responseEntity = studentservice.deleteStudentDetails(25);
        
        // Asserting the response
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Student Details deleted successfully", responseEntity.getBody());
    }
    //Update Student Details
    @Test
    @DisplayName("Should update the records to the database")
    void updateStudentDetails() {
        // Create a sample student model
        StudentModel student = new StudentModel();
        student.setName("Gugan");
        student.setRegisterNo("675");
        student.setGender("Male");
        student.setAge(23);
        student.setPhoneNumber("8098161001");
        student.setCurrentStatus("Completed");
        student.setEmailId("Gugan@gmail.com");
        student.setCourse("MCA");
        student.setBatch("set89");
        student.setFees(29000);
     
        // Mock the behavior of studentrepository
        when(studentrepository.updateStudentDetails(any(StudentModel.class))).thenReturn(1);
        
        // Invoke the method under test
        ResponseEntity<String> responseEntity = studentservice.updateStudentDetails(student);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Student Updates successfully", responseEntity.getBody());
    }
    @Test
    @DisplayName("It Fetch all the records from the datatable")
   
    void getAllStudentDetails() {
        // Stubbing the behavior of studentRepository
        List<StudentModel> studentList = new ArrayList<>();
        // Add mock student data to the list
        StudentModel student1 = new StudentModel(/* initialize with data */);
        StudentModel student2 = new StudentModel(/* initialize with data */);
        studentList.add(student1);
        studentList.add(student2);
        
        when(studentrepository.getAllStudentDetails()).thenReturn(studentList);
        
        // Invoking the method under test
        ResponseEntity<List<StudentModel>> responseEntity = studentservice.getAllStudentDetails();
        
        // Assertions
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().size()); // Assuming you expect 2 students in the list
    }
 
}
