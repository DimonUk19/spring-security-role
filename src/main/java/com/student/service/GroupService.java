package com.student.service;

import com.student.model.Group;

import java.util.Optional;

public interface GroupService {

    Group groupAdd(Group group);

    Optional <Group> findByNameOfGroup(String NameOfGroup);

}
