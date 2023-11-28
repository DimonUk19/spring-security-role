package com.student.serviceImpl;



import java.util.List;

import com.student.security.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.model.Users;
import com.student.repository.UserRepository;
import com.student.service.UserService;

@Service
//@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Users signUp(RegisterRequest request) {
		request.setRole("user");
		return userRepository.save(new Users(request.getEmail(),request.getUsername(), request.getPassword(), request.getRole()));
	}

	@Override
	public List<Users> findAll() {
		return userRepository.findAll();
	}

}
