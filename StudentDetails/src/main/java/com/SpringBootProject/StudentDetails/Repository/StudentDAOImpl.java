package com.SpringBootProject.StudentDetails.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.SpringBootProject.StudentDetails.Model.StudentModel;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addStudentDetails(StudentModel studentModel) {
        String insertQuery = "INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
         int result=jdbcTemplate.update(insertQuery, studentModel.getStudentId(),studentModel.getName(), studentModel.getRegisterNo(), studentModel.getGender(),
                studentModel.getAge(), studentModel.getPhoneNumber(), studentModel.getCurrentStatus(),
                studentModel.getEmailId(), studentModel.getCourse(), studentModel.getBatch(), studentModel.getFees());
		return result;
    }

    @Override
    public int deleteStudentDetails(int studentId) {
        String deleteQuery = "DELETE FROM Student WHERE Student_id = ?";
        int result = jdbcTemplate.update(deleteQuery, studentId);
		return result;
    }

    @Override
    public int updateStudentDetails(StudentModel studentModel) {
        StringBuilder updateQuery = new StringBuilder("UPDATE Student SET ");
        List<Object> queryParams = new ArrayList<>();

        // Check each parameter and update if not null
        if (studentModel.getName() != null) {
            updateQuery.append("Name=?, ");
            queryParams.add(studentModel.getName());
        }
        if (studentModel.getRegisterNo() != null) {
            updateQuery.append("Register_No=?, ");
            queryParams.add(studentModel.getRegisterNo());
        }
        if (studentModel.getGender() != null) {
            updateQuery.append("Gender=?, ");
            queryParams.add(studentModel.getGender());
        }
        if (studentModel.getAge() != 0) {
            updateQuery.append("Age=?, ");
            queryParams.add(studentModel.getAge());
        }
        if (studentModel.getPhoneNumber() != null) {
            updateQuery.append("PhoneNumber=?, ");
            queryParams.add(studentModel.getPhoneNumber());
        }
        if (studentModel.getCurrentStatus() != null) {
            updateQuery.append("Current_Status=?, ");
            queryParams.add(studentModel.getCurrentStatus());
        }
        if (studentModel.getEmailId() != null) {
            updateQuery.append("Email_Id=?, ");
            queryParams.add(studentModel.getEmailId());
        }
        if (studentModel.getCourse() != null) {
            updateQuery.append("Course=?, ");
            queryParams.add(studentModel.getCourse());
        }
        if (studentModel.getBatch() != null) {
            updateQuery.append("Batch=?, ");
            queryParams.add(studentModel.getBatch());
        }
        if (studentModel.getFees() != 0) {
            updateQuery.append("Fees=?, ");
            queryParams.add(studentModel.getFees());
        }

        // Remove the last comma and space
        updateQuery.delete(updateQuery.length() - 2, updateQuery.length());

        // Add WHERE condition for Student_id
        updateQuery.append(" WHERE Student_id=?");
        queryParams.add(studentModel.getStudentId());

        // Execute the update query
        int rowsAffected = jdbcTemplate.update(updateQuery.toString(), queryParams.toArray());

        // Return the number of rows affected
        return rowsAffected;
    }


    @Override
    public StudentModel findById(int studentId) {
        String selectQuery = "SELECT * FROM Student WHERE Student_id = ?";
        try {
            return jdbcTemplate.queryForObject(selectQuery, new BeanPropertyRowMapper<>(StudentModel.class), studentId);
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where no student is found with the given ID
            System.out.println("Student not found with ID: " + studentId);
            return null;
        }
    }


    @Override
    public List<StudentModel> getAllStudentDetails() {
        String selectQuery = "SELECT * FROM Student";
        return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<>(StudentModel.class));
    }

}

