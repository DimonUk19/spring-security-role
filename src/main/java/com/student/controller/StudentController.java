package com.student.controller;

import com.student.exception.ResourceNotFoundException;
import com.student.model.Group;
import com.student.model.Performance;
import com.student.model.Student;
import com.student.security.auth.RegisterRequest;
import com.student.serviceImpl.GroupServiceImpl;
import com.student.serviceImpl.PerformanceServiceImpl;
import com.student.serviceImpl.StudentServiceImpl;
import com.student.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.student.service.StudentService;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@Autowired
	private GroupServiceImpl groupServiceImpl;

	@Autowired
	private PerformanceServiceImpl performanceServiceImpl;


	@GetMapping(value="/personalAccount")
	public ModelAndView personalAccount() {
		ModelAndView modelAndView = new ModelAndView("personalAccount"); // Имя представления без расширения .html
		return modelAndView;
	}

	@GetMapping(value="/studentForm")
	public ModelAndView studentForm() {
		ModelAndView modelAndView = new ModelAndView("student/studentFormAndGroup"); // Имя представления без расширения .html
		return modelAndView;
	}

	@PostMapping(value="/studentForm")
	//public void studentAdd(Student student) {
	public ResponseEntity<Student> studentAdd(/*@RequestParam(value = "firstName") String firstName,
											  @RequestParam(value = "lastName") String lastName,*/
												Student student,
											  @RequestParam(value = "groupString") String groupString) throws ResourceNotFoundException {

		student.setGroup(groupServiceImpl.findByNameOfGroup(groupString)
				.orElseThrow(() -> new ResourceNotFoundException("Group not found for this name: " + groupString)));

		return new ResponseEntity<>(studentServiceImpl.saveStudent(student),HttpStatus.CREATED);

	}

	@GetMapping(value="/findStudent")
	public ModelAndView findStudent(Model model) throws ResourceNotFoundException {
		model.addAttribute("studentList", Optional.of(studentServiceImpl.findAll())
				.orElseThrow(() -> new ResourceNotFoundException("Students not found!")));
		ModelAndView modelAndView = new ModelAndView("student/studentListForPerformance");
		return modelAndView;
	}

	@RequestMapping(value="/findStudent/{id}",  method = RequestMethod.GET)
	public ModelAndView findStudent(@PathVariable("id") long id, Model model) throws ResourceNotFoundException {

		model.addAttribute("student", studentServiceImpl.findById(id));

		ModelAndView modelAndView = new ModelAndView("performance/addPerformance");//"StudentPerformance"); // Имя представления без расширения .html
		return modelAndView;
	}


	@GetMapping("/deleteStudentList")
	public ModelAndView listStudentForDelete(Model model) throws ResourceNotFoundException {
		model.addAttribute("studentList", Optional.of(studentServiceImpl.findAll())
				.orElseThrow(() -> new ResourceNotFoundException("Students not found!")));
		ModelAndView modelAndView = new ModelAndView("student/studentListForDelete");
		return modelAndView;
	}

	@RequestMapping(value = "deleteStudent/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id) {

		studentServiceImpl.deleteStudentById(id);

		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@GetMapping(value="/getStudents")
	public ModelAndView getStudents(Model model) throws ResourceNotFoundException {

/*		studentServiceImpl.findAll()
				.stream()
				.findAny()
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResourceNotFoundException("Students not found!"));*/

		model.addAttribute("studentList", Optional.of(studentServiceImpl.findAll())
				.orElseThrow(() -> new ResourceNotFoundException("Students not found!")));

		ModelAndView modelAndView = new ModelAndView("student/studentList"); // Имя представления без расширения .html
		return modelAndView;
	}

	@GetMapping(value="/studentListForUpdateStudent")
	public ModelAndView studentListForUpdate(Model model) throws ResourceNotFoundException {
		model.addAttribute("studentList", Optional.of(studentServiceImpl.findAll())
				.orElseThrow(() -> new ResourceNotFoundException("Students not found!")));
		ModelAndView modelAndView = new ModelAndView("student/studentListForUpdate");
		return modelAndView;
	}

	@RequestMapping(value = "/updateStudent/{id}", method = RequestMethod.GET)
	public ModelAndView updateStudent(@PathVariable("id") long id, Model model) throws ResourceNotFoundException {
		model.addAttribute("student", studentServiceImpl.findById(id));
		ModelAndView modelAndView = new ModelAndView("student/studentFormUpdate"); // Имя представления без расширения .html
		return modelAndView;
	}

	@PostMapping(value="/updateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, Student student) throws ResourceNotFoundException {

		return new ResponseEntity<> (studentServiceImpl.updateStudent(id, student), HttpStatus.CREATED);

	}







	@RequestMapping(value="/success")
	public ModelAndView success() {
		ModelAndView modelAndView = new ModelAndView("auth/init"); // Имя представления без расширения .html
		return modelAndView;
	}
}
