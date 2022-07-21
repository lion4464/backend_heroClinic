package com.example.demo.patients;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface PatientService {
    PatientEntity update(PatientEntity obj);
    String delete(UUID id);
    PatientEntity getId(UUID id) throws DataNotFoundException;
    PatientEntity insert(UserEntity user,PatientEntity obj);
    Page<PatientEntity> all(Specification<PatientEntity> spec, Pageable page);
}
