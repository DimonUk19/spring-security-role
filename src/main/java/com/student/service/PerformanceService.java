package com.student.service;

import com.student.exception.ResourceNotFoundException;
import com.student.model.Performance;

import java.util.Optional;

public interface PerformanceService {

    Performance addPerformance(Performance performance);

    Performance findByStudent_id(long id) throws ResourceNotFoundException;
}
