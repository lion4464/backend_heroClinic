package com.example.demo.department;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;


    private static final Logger logger = LogManager.getLogger();

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentEntity saveRole(UserEntity user, DepartmentRequest request) {
        logger.info("Saving new department {} to db",request.getName());
        DepartmentEntity department = new DepartmentEntity();
        department.setCompany(user.getCompany());
        department.setName(request.getName());
        departmentRepository.save(department);
        return department;
    }

    @Override
    public DepartmentEntity get(UUID id) throws DataNotFoundException {
        Optional<DepartmentEntity> res = departmentRepository.findById(id);
        if (res.isEmpty())
            throw new DataNotFoundException("Department is not found :(");
        return res.get();
    }

    @Override
    public String delete(UUID id) {
        departmentRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public List<DepartmentEntity> all(UserEntity user) {
        return departmentRepository.findAllByCompanyIdAndDeleted(user.getCompanyId(),false);
    }

    @Override
    public DepartmentEntity update(DepartmentRequest obj) {
        logger.info("Updated department {} ",obj.getName());
        Optional<DepartmentEntity> entity=departmentRepository.findById(obj.getId());
        if (entity.isEmpty())
            throw new DataNotFoundException(obj.getId()+" isn't not found");
          DepartmentEntity departmentEntity = entity.get();
          departmentEntity.setName(obj.getName());
        return departmentRepository.save(departmentEntity);
    }
}
