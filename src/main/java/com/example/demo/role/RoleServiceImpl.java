package com.example.demo.role;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.patients.PatientEntity;
import com.example.demo.user.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    private static final Logger logger = LogManager.getLogger();

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



    public RoleEntity get(UUID id) throws DataNotFoundException {
        Optional<RoleEntity> res = roleRepository.findById(id);
        if (res.isEmpty())
            throw new DataNotFoundException("Role is not found :(");
        return res.get();
    }
    public String delete(UUID id) {
        roleRepository.deleteById(id);
        return "Successfully removed";
    }

    public List<RoleEntity> allRole(){
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }


}
