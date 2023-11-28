package com.student.controller;

import com.student.security.auth.RegisterRequest;
import com.student.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestController
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @RequestMapping(value="/registration")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("auth/registration"); // Имя представления без расширения .html
        return modelAndView;
    }

    @PostMapping(value="/registration")
    public void signUp(RegisterRequest request ) throws SQLException {

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setRole("user");
        userServiceImpl.signUp(request);
    }

    @RequestMapping(value="/")
    public ResponseEntity<ModelAndView> indexPage() {
        return ResponseEntity.ok(new ModelAndView("init"));
    }

    @RequestMapping("/login")
    public ModelAndView getLogin(@RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView("auth/login")
                .addObject("error", error != null)
                .addObject("logout", logout != null); // Имя представления без расширения .html
        return modelAndView;
    }
}
