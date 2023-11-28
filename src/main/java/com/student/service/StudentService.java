package com.student.service;

import java.sql.SQLException;
import java.util.List;

import com.student.exception.ResourceNotFoundException;
import com.student.model.Student;

public interface StudentService {

	List<Student> findAll();

	Student findById(long id) throws ResourceNotFoundException;

}
