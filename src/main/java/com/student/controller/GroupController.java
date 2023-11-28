package com.student.controller;

import com.student.model.Group;
import com.student.serviceImpl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GroupController {

    @Autowired
    private GroupServiceImpl groupServiceImpl;

    @GetMapping(value="/addGroup")
    public ModelAndView addGroup() {
        ModelAndView modelAndView = new ModelAndView("group/groupForm"); // Имя представления без расширения .html
        return modelAndView;
    }

    @PostMapping(value="/addGroup")
    public ResponseEntity<Group> addGroup(Group group) {
        return new ResponseEntity<>(groupServiceImpl.groupAdd(group), HttpStatus.OK);
    }
}
