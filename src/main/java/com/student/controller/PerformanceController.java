package com.student.controller;

import com.student.exception.ResourceNotFoundException;
import com.student.model.Performance;
import com.student.serviceImpl.PerformanceServiceImpl;
import com.student.serviceImpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PerformanceController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Autowired
    private PerformanceServiceImpl performanceServiceImpl;

    @PostMapping(value="/addPerformance/{id}")
    public ResponseEntity<Performance> addPerformance(@PathVariable("id") long id, Performance performance) throws ResourceNotFoundException {
/*								 @RequestParam(value = "systemAnalysis") int systemAnalysis,
								 @RequestParam(value = "applicationProgramming") int applicationProgramming,
								 @RequestParam(value = "organisationDB") int organisationDB) {*/
        performance.setStudent(studentServiceImpl.findById(id));
        return new ResponseEntity<> (performanceServiceImpl.addPerformance(performance), HttpStatus.CREATED);
    }

    @RequestMapping(value = "studentPerformance/{id}", method = RequestMethod.GET)
    public ModelAndView studentPerformance(@PathVariable("id") long id, Model model) throws ResourceNotFoundException {
        model.addAttribute("student", studentServiceImpl.findById(id));
        model.addAttribute("performance", performanceServiceImpl.findByStudent_id(id));
        ModelAndView modelAndView = new ModelAndView("performance/studentPerformance"); // Имя представления без расширения .html
        return modelAndView;
    }

    @PostMapping(value="/addStudentPerformance")
    public ResponseEntity <Performance> addStudentPerformance(Performance performance,
                                                              @RequestParam(value = "id") long id) throws ResourceNotFoundException {
        performance.setStudent(studentServiceImpl.findById(id));
        return new ResponseEntity<>(performanceServiceImpl.addPerformance(performance), HttpStatus.OK);
    }

    @GetMapping(value="/studentListForUpdate")
    public ModelAndView studentListForUpdatePerformance() {
        ModelAndView modelAndView = new ModelAndView("student/studentListForUpdatePerformance")
                .addObject("studentList", studentServiceImpl.findAll()); // Имя представления без расширения .html
        return modelAndView;
    }

    @GetMapping(value="/studentUpdatePerformance")
    public ModelAndView updatePerformanceForm() {
        ModelAndView modelAndView = new ModelAndView("student/studentListForUpdatePerformance")
                .addObject("studentList", studentServiceImpl.findAll()); // Имя представления без расширения .html
        return modelAndView;
    }

    @RequestMapping(value = "/updatePerformance/{id}", method = RequestMethod.GET)
    public ModelAndView updatePerformanceForm(@PathVariable("id") long id, Model model) throws ResourceNotFoundException {
        model.addAttribute("student", studentServiceImpl.findById(id));
        ModelAndView modelAndView = new ModelAndView("performance/updatePerformance"); // Имя представления без расширения .html
        return modelAndView;
    }

    @PostMapping(value="/updatePerformance/{id}")
    public ResponseEntity<Performance> updatePerformance(@PathVariable("id") long id, Performance performance) throws ResourceNotFoundException {

        return new ResponseEntity<> (performanceServiceImpl.updatePerformance(id, performance), HttpStatus.CREATED);

/*        return performanceServiceImpl.findByStudent_id(id)
                .map(perf -> {
                    perf.setSystemAnalysis(performance.getSystemAnalysis());
                    perf.setApplicationProgramming(performance.getApplicationProgramming());
                    perf.setOrganisationDB(performance.getOrganisationDB());
                    return ResponseEntity.ok().body(performanceServiceImpl.updatePerformance(perf));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found!"));*/

    }
}
