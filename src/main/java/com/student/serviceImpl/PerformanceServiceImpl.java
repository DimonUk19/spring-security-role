package com.student.serviceImpl;

import com.student.exception.ResourceNotFoundException;
import com.student.model.Performance;
import com.student.repository.PerformanceRepository;
import com.student.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public Performance addPerformance(Performance performance) {
        return performanceRepository.save(performance);
    }

    public Performance findByStudent_id(long id) throws ResourceNotFoundException {
        return performanceRepository.findByStudent_id(id)
                .orElseThrow(() -> new ResourceNotFoundException("Performance not found!"));
    }

    public void deletePerformance(Performance performance) {
        performanceRepository.delete(performance);
    }

    public void deletePerformanceByStudentId(long id) {
        performanceRepository.deleteByStudentId(id);
    }

    public Performance updatePerformance(long id, Performance performance) throws ResourceNotFoundException {
        return performanceRepository.findByStudent_id(id)
                .map(perf -> {
                    perf.setSystemAnalysis(performance.getSystemAnalysis());
                    perf.setApplicationProgramming(performance.getApplicationProgramming());
                    perf.setOrganisationDB(performance.getOrganisationDB());
                    return performanceRepository.save(perf);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found!"));
    }
}
