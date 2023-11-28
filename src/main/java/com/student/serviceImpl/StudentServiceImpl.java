package com.student.serviceImpl;

import java.util.List;

import com.student.exception.ResourceNotFoundException;
import com.student.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.model.Student;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private PerformanceRepository performanceRepository;
	
	@Override
	public List <Student> findAll() {
		return studentRepository.findAll();
	}

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public Student findById(long id) throws ResourceNotFoundException {
		return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Students not found!"));
	}

	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}

	@Transactional
	public void deleteStudentById(long id) {
		performanceRepository.deleteByStudentId(id);
		studentRepository.deleteById(id);
	}

	public Student updateStudent(long id,Student student) throws ResourceNotFoundException {
		return studentRepository.findById(id)
				.map(st -> {
					st.setFirstName(student.getFirstName());
					st.setLastName(student.getLastName());
					return studentRepository.save(st);
				})
				.orElseThrow(() -> new ResourceNotFoundException("Student not found!"));
	}


/*	@Override
	public void makeList() {
		
		Student student=new Student();
		student.setFirstName("john doe");
		student.setStandard("10");
		student.setLastName("11");
		studentRepository.save(student);
		
		student=new Student();
		student.setFirstName("manu mathew");
		student.setStandard("11");
		student.setLastName("16");
		studentRepository.save(student);
		
		student=new Student();
		student.setFirstName("anu mathew");
		student.setStandard("10");
		student.setLastName("8");
		studentRepository.save(student);
		
		student=new Student();
		student.setFirstName("sonu ms");
		student.setStandard("11");
		student.setLastName("41");
		studentRepository.save(student);
		
		student=new Student();
		student.setFirstName("vishnu vd");
		student.setStandard("10");
		student.setLastName("50");
		studentRepository.save(student);
			
		
		
	}*/

}
