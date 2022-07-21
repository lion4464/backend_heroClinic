package com.example.demo.role;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleEntity saveRole(RoleRequest request, UserEntity user);
    RoleEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<RoleEntity> allRole(UserEntity user);

    RoleEntity findByName(String roleName);


    RoleEntity updateRole(RoleRequest obj);
}
