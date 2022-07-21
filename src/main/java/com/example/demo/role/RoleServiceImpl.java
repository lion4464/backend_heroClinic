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

    public RoleEntity saveRole(RoleRequest request, UserEntity user){
        logger.info("Saving new role {} to db",request.getName());
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(request.getName());
        roleEntity.setCompany(user.getCompany());
        roleRepository.save(roleEntity);
        return roleEntity;
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

    public List<RoleEntity> allRole(UserEntity user){
        return roleRepository.findAllByCompanyId(user.getCompanyId());
    }

    @Override
    public RoleEntity findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public RoleEntity updateRole(RoleRequest obj){
        Optional<RoleEntity> optional=roleRepository.findById(obj.getId());
        if (optional.isEmpty())
            throw new DataNotFoundException(obj.getId()+" isn't not found");
            RoleEntity entity = optional.get();
            entity.setName(obj.getName());
        return roleRepository.save(entity);
    }
}
