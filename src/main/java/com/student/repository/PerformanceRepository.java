package com.student.repository;

import com.student.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    Optional<Performance> findByStudent_id(long id);

    void deleteByStudentId(Long aLong);
}
