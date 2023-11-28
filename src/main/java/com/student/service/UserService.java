package com.student.service;

import java.util.List;

import com.student.model.Users;
import com.student.security.auth.RegisterRequest;

public interface UserService {

	Users signUp(RegisterRequest request);
	List<Users> findAll();
}
