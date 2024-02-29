package com.SpringBootProject.StudentDetails.Controller;

import com.SpringBootProject.StudentDetails.Exception.StudentDetailsNotFoundException;
import com.SpringBootProject.StudentDetails.Model.StudentModel;
import com.SpringBootProject.StudentDetails.Repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Indicate a particular class servers RESTFUL web servers
@RestController
//Specifies all the request mappings in controller using this base path
@RequestMapping("/students")
public class StudentController {
	
	  @Autowired
    private final StudentDAO studentRepository;

  
    public StudentController(StudentDAO studentRepository) {
        this.studentRepository = studentRepository;
    }
    //To get the Student Details
    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody StudentModel studentModel) {
        // Check if any of the required fields are empty
        if (isEmpty(studentModel.getName()) ||
            isEmpty(studentModel.getRegisterNo()) ||
            isEmpty(studentModel.getGender()) ||
            isEmpty(studentModel.getPhoneNumber()) ||
            isEmpty(studentModel.getCurrentStatus()) ||
            isEmpty(studentModel.getEmailId()) ||
            isEmpty(studentModel.getCourse()) ||
            isEmpty(studentModel.getBatch())) {
            
            // If any required field is empty, return bad request status
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All input fields are required");
        }
        
        // If all required fields are present, proceed to add the student
        studentRepository.addStudentDetails(studentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }

    // Helper method to check if a value is empty (null or empty string)
    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentId) {
        int isdeleted=studentRepository.deleteStudentDetails(studentId);
        if(isdeleted!=0) {
        	 return ResponseEntity.ok().body("Student Details deleted successfully");
        }
        else {
        	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("StudentDetails is not deleted");
        }
       
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(@RequestBody StudentModel studentModel) {
        int isUpdated = studentRepository.updateStudentDetails(studentModel);
//        System.out.print(studentModel);
        if (isUpdated!=0) {
            return ResponseEntity.ok().body("Student updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("StudentDetails is not found or update failed");
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable int studentId) {
        try {
            StudentModel student = studentRepository.findById(studentId);
            return ResponseEntity.ok().body(student);
        } catch (StudentDetailsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentModel>> getAllStudents() {
        List<StudentModel> students = studentRepository.getAllStudentDetails();
        if (!students.isEmpty()) {
            return ResponseEntity.ok().body(students); // Return list of students if not empty
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if list is empty
        }
    }
}