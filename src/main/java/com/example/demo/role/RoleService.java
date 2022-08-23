package com.example.demo.role;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    RoleEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<RoleEntity> allRole();

    RoleEntity findByName(String roleName);



}
