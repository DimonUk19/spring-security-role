package com.student.repository;

import com.student.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
     Optional<Group> findByNameOfGroup(String nameOfGroup);
}
