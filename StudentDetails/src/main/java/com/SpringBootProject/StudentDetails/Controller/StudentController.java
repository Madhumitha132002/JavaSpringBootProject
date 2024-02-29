package com.SpringBootProject.StudentDetails.Controller;

import com.SpringBootProject.StudentDetails.Exception.StudentDetailsNotFoundException;
import com.SpringBootProject.StudentDetails.Model.StudentModel;
import com.SpringBootProject.StudentDetails.Repository.StudentDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Indicate a particular class servers RESTFUL web servers
@RestController
//Specifies all the request mappings in controller using this base path
@RequestMapping("/students")
public class StudentController {
	
	public static final Logger logInfo = LoggerFactory.getLogger(StudentController.class) ;
	
	  @Autowired
    private final StudentDAO studentRepository;

  
    public StudentController(StudentDAO studentRepository) {
        this.studentRepository = studentRepository;
    }
    //To get the Student Details
    @PostMapping("/add")
    
    
    public void addStudent(@RequestBody StudentModel studentModel) {
        
    	studentRepository.addStudentDetails(studentModel);
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
        if (isUpdated!=1) {
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
    	
    	  logInfo.info("Student info logging was Enabled");
    	  logInfo.debug("Student info debug was enabled");
        List<StudentModel> students = studentRepository.getAllStudentDetails();
        if (!students.isEmpty()) {
            return ResponseEntity.ok().body(students); // Return list of students if not empty
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if list is empty
        }
    }
}
