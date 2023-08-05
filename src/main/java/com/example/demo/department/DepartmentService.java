package com.example.demo.department;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;


public interface DepartmentService{
    DepartmentEntity saveRole(UserEntity user,DepartmentRequest request);
    DepartmentEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<DepartmentEntity> all(UserEntity user);
    DepartmentEntity update(DepartmentRequest obj) throws DataNotFoundException;

}
