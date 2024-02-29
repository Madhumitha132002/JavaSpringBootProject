package com.SpringBootProject.StudentDetails.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public int addStudentDetails(StudentModel studentModel) {
        int result = studentDetailsRepo.addStudentDetails(studentModel);
         return result;
    }

    public int deleteStudentDetails(int studentId) {
        int result = studentDetailsRepo.deleteStudentDetails(studentId);
        return result;
    }

    public int updateStudentDetails(StudentModel studentModel) {
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
