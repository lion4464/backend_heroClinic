package com.example.demo.patients;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.JpaGenericService;
import com.example.demo.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface PatientService   extends JpaGenericService<PatientEntity, UUID> {
    PatientEntity insert(UserEntity user,PatientEntity obj);
    Page<PatientEntity> all(Specification<PatientEntity> spec, Pageable page);
}
