package com.example.demo.salary_type;

import com.example.demo.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface SalaryTypeService{
    SalaryTypeEntity saveRole(SalaryTypeRequest request);
    SalaryTypeEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<SalaryTypeEntity> all();
    SalaryTypeEntity update(SalaryTypeRequest obj) throws DataNotFoundException;

}
