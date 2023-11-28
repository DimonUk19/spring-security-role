package com.student.serviceImpl;

import com.student.model.Group;
import com.student.repository.GroupRepository;
import com.student.service.GroupService;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group groupAdd(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Optional <Group> findByNameOfGroup(String nameOfGroup) {
        return groupRepository.findByNameOfGroup(nameOfGroup);
    }

}
