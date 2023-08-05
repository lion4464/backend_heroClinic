package com.example.demo.salary_type;

import com.example.demo.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class SalaryTypeServiceImpl implements SalaryTypeService{

    private final SalaryTypeRepository salaryTypeRepository;


    private static final Logger logger = LogManager.getLogger();

    public SalaryTypeServiceImpl(SalaryTypeRepository salaryTypeRepository) {
        this.salaryTypeRepository = salaryTypeRepository;
    }

    @Override
    public SalaryTypeEntity saveRole(SalaryTypeRequest request) {
        logger.info("Saving new SalaryType {} to db",request.getName());
        SalaryTypeEntity department = new SalaryTypeEntity();
        department.setName(request.getName());
        salaryTypeRepository.save(department);
        return department;
    }

    @Override
    public SalaryTypeEntity get(UUID id) throws DataNotFoundException {
        Optional<SalaryTypeEntity> res = salaryTypeRepository.findById(id);
        if (res.isEmpty())
            throw new DataNotFoundException("SalaryType is not found :(");
        return res.get();
    }

    @Override
    public String delete(UUID id) {
        salaryTypeRepository.deleteById(id);
        return "Successfully removed";
    }

    @Override
    public List<SalaryTypeEntity> all() {
        return salaryTypeRepository.findAll();
    }

    @Override
    public SalaryTypeEntity update(SalaryTypeRequest obj) throws DataNotFoundException {
        logger.info("Updated SalaryType {} ",obj.getName());
        Optional<SalaryTypeEntity> entity=salaryTypeRepository.findById(obj.getId());
        if (entity.isEmpty())
            throw new DataNotFoundException(obj.getId()+" isn't not found");
        SalaryTypeEntity salaryTypeEntity = entity.get();
        salaryTypeEntity.setName(obj.getName());
        return salaryTypeRepository.save(salaryTypeEntity);
    }
}
