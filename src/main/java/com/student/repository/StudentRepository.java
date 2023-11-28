package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(long id);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    //Optional<Student> save(Student student);

    //String findByLastName(String lastName);

}
